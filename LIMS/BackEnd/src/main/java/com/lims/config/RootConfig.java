package com.lims.config;

import org.springframework.context.annotation.*;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

/**
 * ClassName    backEnd-RootConfig
 * Description  Spring IOC 配置
 *
 * @author xuanc
 * @version 1.0
 * @date 2019/5/31 下午10:37
 */
@Configuration
@ComponentScan("com.lims.*")
@ImportResource(locations = "classpath:spring-mybatis.xml")
public class RootConfig {

    /**
     * multi 解析器
     */
    @Bean(name = "multipartResolver")
    public MultipartResolver initMultipartResolver() {
        return new StandardServletMultipartResolver();
    }

}
