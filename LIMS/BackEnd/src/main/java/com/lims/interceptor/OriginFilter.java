package com.lims.interceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName    LIMS-OriginFilter
 * Description
 *
 * @author xuanc
 * @version 1.0
 * @date 2019/6/14 上午10:27
 */
public class OriginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("INFO:: 过滤器开始处理...");

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String origin = servletRequest.getRemoteHost() + ":" + servletRequest.getRemotePort();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Authentication, Authorization");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE,PUT");
        response.setHeader("Access-Control-Max-Age","3600");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {

    }

}
