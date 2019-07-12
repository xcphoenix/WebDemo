package com.xuanc.chg.control;

import com.alibaba.fastjson.JSON;
import com.xuanc.chg.model.User;
import com.xuanc.chg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * ClassName    chg-UserControl
 * Description
 *
 * @author xuanc
 * @version 1.0
 * @date 2019/6/22 下午9:06
 */
@Controller
@RequestMapping("/user")
public class UserControl {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String userProfile(Model model, HttpSession session) {
        String username = (String) session.getAttribute("user");
        User user = userService.getProfile(username);
        model.addAttribute("profile", user);
        model.addAttribute("user", new User());
        System.out.println("DEBUG:: profile => " + JSON.toJSON(user));

        return "user/profile";
    }

    @PostMapping("/update/profile")
    public String updateProfile(@ModelAttribute("user") User user, Model model,
                                HttpSession session) {
        String username = (String) session.getAttribute("user");
        int userId = userService.getUserId(username);
        user.setUserId(userId);
        String msg;

        try {
            if (userService.updateUserProfile(user)) {
                session.setAttribute("user", user.getUserName());
                msg = "更新成功";
            } else {
                msg = "信息没有发生变化";
            }
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            msg = "用户名已被占用";
        }
        model.addAttribute("msg", msg);
        username = (String) session.getAttribute("user");
        User userUpdate = userService.getProfile(username);
        model.addAttribute("profile", userUpdate);
        model.addAttribute("user", new User());

        System.out.println("DEBUG:: " + JSON.toJSON(model) + JSON.toJSON(msg));

        return "user/profile";
    }

    @GetMapping("/security")
    public String userSecurity() {
        return "user/security";
    }

    @PostMapping("/update/passwd")
    public String updatePasswd(Model model, HttpSession session, HttpServletRequest request) {
        String newPasswd = request.getParameter("newPassword");
        String oldPasswd = request.getParameter("oldPassword");
        String username = (String) session.getAttribute("user");
        String msg = null;

        System.out.println("DEBUG:: 更新密码请求.. old = " + oldPasswd + ", new = " + newPasswd);

        if (userService.checkCanLogin(username, oldPasswd)) {
            userService.updateUserPasswd(username, newPasswd);
            msg = "更新成功";
        } else {
            msg = "密码错误！";
        }

        model.addAttribute("msg", msg);
        return "user/security";
    }

    @PostMapping("/admin/reset/passwd")
    public String resetPasswd(HttpServletRequest request, Model model) {
        String msg = null;
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String passwd = request.getParameter("resetPasswd");
            msg = userService.resetPasswd(userId, passwd);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            msg = "格式错误";
        }

        model.addAttribute("msg", msg);
        return "admin/reset";
    }

    @GetMapping("/admin/del/{id}")
    public String detUser(@PathVariable("id") int id, RedirectAttributesModelMap model) {
        String msg = null;
        try {
            if (userService.delUser(id) == 1) {
                msg = "删除成功";
            } else {
                msg = "删除失败！用户不存在!";
            }
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            msg = "删除用户失败！用户已有历史订单。";
        }
        model.addFlashAttribute("msg", msg);

        System.out.println("DEBUG:: 【管理员】- 删除操作 => 用户id: " + id + ", msg: " + msg);

        return "redirect:/admin";
    }

}
