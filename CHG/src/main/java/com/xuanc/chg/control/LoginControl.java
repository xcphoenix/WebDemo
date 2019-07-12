package com.xuanc.chg.control;

import com.xuanc.chg.model.User;
import com.xuanc.chg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * ClassName    chg-LoginControl
 * Description
 *
 * @author xuanc
 * @version 1.0
 * @date 2019/6/22 下午1:03
 */
@Controller
@RequestMapping("/user")
public class LoginControl {

    private UserService userService;

    @Autowired
    public LoginControl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(value = "user") User user, HttpSession session, Model model) {
        if (userService.checkCanLogin(user.getUserName(), user.getUserPasswd())) {
            session.setAttribute("user", user.getUserName());
            session.setAttribute("role", userService.getUserRole(user.getUserName()));
            System.out.println("DEBUG:: 登录成功");

            if ("admin".equals(userService.getUserRole(user.getUserName()))) {
                return "redirect:/admin";
            }
            return "redirect:/index";
        } else {
            model.addAttribute("msg", "登录失败！用户名或密码错误。");
            System.out.println("DEBUG:: 登录失败");
            return "login";
        }
    }

    @PostMapping("/register")
    public String register(@ModelAttribute(value = "user") User user, Model model, HttpSession session) {
        try {
            if (userService.registerUser(user)) {
                System.out.println("DEBUG:: 注册成功");
                session.setAttribute("user", user.getUserName());
                session.setAttribute("role", "user");
                return "redirect:/index";
            } else {
                System.out.println("DEBUG:: 注册失败, 服务器异常");
                model.addAttribute("msg", "注册失败, 服务器异常");
                return "register";
            }
        } catch (DataAccessException dae) {
            System.out.println("DEBUG:: 注册失败，用户名重复");
            dae.printStackTrace();
            model.addAttribute("msg", "注册失败, 用户名已被占用");
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("role");
        return "redirect:/index";
    }

}
