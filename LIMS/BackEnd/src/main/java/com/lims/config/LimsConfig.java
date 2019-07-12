package com.lims.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * Description  Web init config
 * @author      xuanc
 * @date        2019/5/31 下午10:21
 * @version     1.0
 */
@PropertySource(value = "classpath:fileSaved.properties")
public class LimsConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Value("${avatar.server.path}")
    private String fileSavedPath;

    /**
     * Spring IOC 容器配置
     * @return 返回 Spring 的 Java 配置文件数组
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    /**
     * DispatcherServlet 的 URL 映射关系
     * @return 返回 Java 配置文件数组
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    /**
     * 拦截所有请求
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    /**
     * @param registration 动态加载配置
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // 文件上传路径
        String filepath = "/usr/local/tomcat8/webapps/pictures/static/avatars";
        // 10MB
        Long singleMax = (long)(10 * Math.pow(2, 20));
        // 100MB
        Long totalMax = (long) (100 * Math.pow(2, 20));
        // 配置 MultipartResolver，限制请求，但个文件5MB，总文件 10MB
        registration.setMultipartConfig(new MultipartConfigElement(filepath, singleMax, totalMax, 0));
    }
}
