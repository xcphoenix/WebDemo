package com.lims.controller;

import com.alibaba.fastjson.JSONObject;
import com.lims.model.Result;
import com.lims.model.Token;
import com.lims.model.User;
import com.lims.service.TokenService;
import com.lims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * ClassName    LIMS-LoginControl
 * Description
 *
 * @author xuanc
 * @version 1.0
 * @date 2019/6/11 下午1:14
 */
@RestController
public class LoginControl {

    private UserService userService;
    private TokenService tokenService;

    @Autowired
    public LoginControl(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "/login_p")
    public Result failLogin() {
        return new Result(403, "你还没有登录或没有权限访问此页面", null, false);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody JSONObject json, HttpServletResponse response) {
        Long userId = json.getLong("username");
        String password = json.getString("password");

        if (userService.checkUserPasswd(userId, password)) {
            String role = userService.getUserRole(userId);

            // 生成 token
            Token token = new Token();
            token.setUserId(userId);
            token.setUserRole(role);
            token.setTokenId(UUID.randomUUID().toString());

            tokenService.createToken(token);

            return new Result(200, "登录成功", token, false);
        } else {
            return new Result(401, "登录失败", null, false);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result logout(HttpServletRequest httpServletRequest) {
        String tokenId = httpServletRequest.getHeader("Authorization");
        tokenService.deleteToken(tokenId);
        return new Result(200, "注销成功", null, true);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result registerUser(@RequestBody User user) {
        if (userService.addTeacher(user) == 1) {
            return new Result(200, "注册成功", user.getUserId(), true);
        } else {
            return new Result(200, "注册失败", null, false);
        }
    }

}
