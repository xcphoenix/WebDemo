package com.lims.service.impl;

import com.lims.model.Token;
import com.lims.service.AuthService;
import com.lims.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName    LIMS-AuthServiceImpl
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/14 下午7:22
 * @version     1.0
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private TokenService tokenService;

    @Override
    public Long getCurrentUser(HttpServletRequest request) {
        String tokenId = request.getHeader("authentication");
        Token token = tokenService.getToken(tokenId);
        return token.getUserId();
    }
}
