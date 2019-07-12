package com.lims.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lims.model.Result;
import com.lims.model.User;
import com.lims.service.AuthService;
import com.lims.service.TokenService;
import com.lims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName    LIMS-UserControl
 * Description  用户管理
 *
 * @author xuanc
 * @version 1.0
 * @date 2019/6/11 下午3:49
 */
@RequestMapping("/users")
@RestController
public class UserControl {

    private UserService userService;
    private AuthService authService;

    @Autowired
    public UserControl(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    /**
     * 管理员添加用户
     * return 新创建用户的 id
     */
    @RequestMapping(value = "/admin/", method = RequestMethod.POST)
    public Result addUser(@RequestBody User user) {
        System.out.println(JSON.toJSON(user));
        if (userService.addTeacher(user) == 1) {
            return new Result(200, "添加成功", user.getUserId(), true);
        } else {
            return new Result(200, "添加失败！", null, false);
        }
    }

    /**
     * 管理员删除用户
     */
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
    public Result delTeacher(@PathVariable("id") int id) {
        try {
            int rs = userService.delTeacher(id);
            if (rs == 1) {
                return new Result(200, "删除成功", null, true);
            } else {
                return new Result(200, "删除失败，用户不存在或没有权限", null, false);
            }
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            return new Result(200, "删除失败，用户预定了实验室", null, false);
        }
    }

    /**
     * 管理员获取用户信息 （基本信息）
     */
    @RequestMapping(value = "/admin/detail/{id}", method = RequestMethod.GET)
    public Result getDetailTeacher(@PathVariable("id") Long id, HttpServletRequest request) {
        Long currentUserId = authService.getCurrentUser(request);
        User teacher = userService.getDetailTeacher(id, true, currentUserId);
        if (teacher != null) {
            return new Result(200, "查询成功", teacher, true);
        } else {
            return new Result(200, "查询失败，用户不存在", null, false);
        }
    }

    /**
     * 管理员获取所有普通用户信息
     */
    @RequestMapping(value = "/admin/all", method = RequestMethod.GET)
    public Result gerAllUsersMsg() {
        List<User> list = userService.getAllUserDetail();
        return new Result(200, "获取成功", list, true);
    }

    /**
     * 登录用户更新个人信息（基本信息）
     */
    @RequestMapping(value = "/detail", method = RequestMethod.PUT)
    public Result updateTeacher(@RequestBody User teacher, HttpServletRequest request) {
        teacher.setUserId(authService.getCurrentUser(request));
        if (userService.updateTeacher(teacher) == 1) {
            return new Result(200, "更新成功", null, true);
        } else {
            return new Result(200, "更新失败，服务器错误", null, false);
        }
    }

    /**
     * 登录用户获取个人信息
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Result getDetail(HttpServletRequest request) {
        Long currentUserId = authService.getCurrentUser(request);
        User teacher = userService.getDetailTeacher(0L, false, currentUserId);
        if (teacher != null) {
            return new Result(200, "查询成功", teacher, true);
        } else {
            return new Result(200, "查询失败，服务器异常", null, false);
        }
    }

    /**
     * 修改头像
     * TODO 异常未捕获成功
     */
    @RequestMapping(value = "/data/avatar", method = RequestMethod.POST)
    public Result updateUserAvatar(MultipartFile file, HttpServletRequest request) {
        Long currentUserId = authService.getCurrentUser(request);
        try {
            String avatarUrl = userService.updateUserAvatar(file, currentUserId);
            return new Result(200, "上传成功", avatarUrl, true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(200, "上传失败", null, false);
        }
    }

    /**
     * 重置密码
     *
     * @param jsonObject ..
     * @return ..
     */
    @RequestMapping(value = "/data/passwd", method = RequestMethod.PUT)
    public Result resetUserPasswd(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        System.out.println("DEBUG:: 修改密码....");

        String oldPasswd = jsonObject.getString("oldPasswd");
        String newPasswd = jsonObject.getString("newPasswd");
        Long userId = authService.getCurrentUser(request);

        if (oldPasswd == null || newPasswd == null || newPasswd.length() == 0) {
            return new Result(200, "参数错误", null, false);
        }

        if (userService.checkUserPasswd(userId, oldPasswd)) {
            if (userService.resetUserPasswd(userId, newPasswd) == 1) {
                return new Result(200, "密码修改成功", null, true);
            } else {
                return new Result(200, "服务器异常", null, false);
            }
        } else {
            return new Result(200, "密码错误或服务器异常", null, false);
        }
    }

}
