package com.lims.service;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName    LIMS-AuthService
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/14 下午7:21
 * @version     1.0
 */ 
public interface AuthService {

    Long getCurrentUser(HttpServletRequest request);

}
