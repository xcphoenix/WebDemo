package com.xuanc.chg.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName    chg-RoleInterceptor
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/22 下午3:28
 * @version     1.0
 */ 
public class RoleInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
         * 权限验证...
         */

        String url = request.getServletPath();
        String userName = (String) request.getSession().getAttribute("user");
        String userRole = (String) request.getSession().getAttribute("role");

        System.out.println("DEBUG:: 权限验证... url => " + url);

        if (url.contains("login") || url.contains("register") || url.contains("logout")) {
            return true;
        }

        if (url.contains("admin") && !"admin".equals(userRole)) {
            response.sendRedirect("/chg/login");
            return false;
        }

        if ((url.contains("profile") || url.contains("order") || url.contains("user")) && userName == null) {
            response.sendRedirect("/chg/login");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("DEBUG:: ModelAndView 属性添加...");

        String username = (String)request.getSession().getAttribute("user");
        if (username != null) {
            modelAndView.addObject("loginUser", username);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
