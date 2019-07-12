package com.lims.interceptor;

import com.alibaba.fastjson.JSON;
import com.lims.model.Result;
import com.lims.model.Token;
import com.lims.service.TokenService;
import com.lims.service.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Struct;
import java.sql.Timestamp;
import java.util.Date;

/**
 * ClassName    LIMS-RoleInterceptor
 * Description
 *
 * @author xuanc
 * @version 1.0
 * @date 2019/6/13 下午8:23
 */
public class RoleInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        final String admin = "admin";
        final Long timeLimit = 3 * 60L * 60L;

        Date now = new Date();
        Long userId = null;
        String userRole = null;

        httpServletResponse.setContentType("application/json;charset=utf-8");

        TokenService tokenService = WebApplicationContextUtils.
                getRequiredWebApplicationContext(httpServletRequest.getServletContext()).
                getBean(TokenServiceImpl.class);

        // 获取请求路径
        String url = httpServletRequest.getServletPath();
        // 获取 token
        String tokenId = httpServletRequest.getHeader("authentication");

        if ("/login".equals(url) || "/register".equals(url)) {
            return true;
        }

        // 如果 token 不存在
        if (tokenId == null) {
            System.out.println("DEBUG:: 没有发现 token");

            httpServletResponse.getWriter().write(JSON.toJSONString(new Result(403, "你还没有登录", null, false)));
            return false;
        }

        Token token = tokenService.getToken(tokenId);

        if (token == null) {
            System.out.println("DEBUG:: token 无效");

            httpServletResponse.getWriter().write(JSON.toJSONString(new Result(403, "你还没有登录", null, false)));
            return false;
        }

        System.out.println("DEBUG:: 拦截器启动～");
        System.out.println("DEBUG:: token ==> " + JSON.toJSONString(token));
        System.out.println("DEBUG:: now ==> " + now + ", " + token.getTokenTime());
        System.out.println("DEBUG:: token 过期状态 ==> " + (now.getTime() - (token.getTokenTime().getTime())) / 1000);

        // 如果 token 过期
        if (now.getTime() - (token.getTokenTime().getTime()) > timeLimit * 1000) {
            System.out.println("DEBUG:: token 已过期");

            tokenService.deleteToken(token.getTokenId());
            httpServletResponse.getWriter().write(JSON.toJSONString(new Result(403, "登录已过期", null, false)));
            return false;
        }

        if (token.getUserId() == null || token.getUserRole() == null) {
            httpServletResponse.getWriter().write(JSON.toJSONString(new Result(403, "你还没有登录", null, false)));
        } else if (url.contains(admin)) {
            if (admin.equals(token.getUserRole())) {
                return true;
            } else {
                httpServletResponse.getWriter().write(JSON.toJSONString(new Result(403, "你没有权限访问此页面", null, false)));
            }
        } else {
            return true;
        }

        return false;
    }
}
