package com.lims.service.impl;

import com.alibaba.fastjson.JSON;
import com.lims.dao.TokenMapper;
import com.lims.model.Token;
import com.lims.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName    LIMS-TokenServiceImpl
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/14 下午1:28
 * @version     1.0
 */
@Service
public class TokenServiceImpl implements TokenService {

    private TokenMapper tokenMapper;

    @Autowired
    public TokenServiceImpl(TokenMapper tokenMapper) {
        this.tokenMapper = tokenMapper;
    }

    @Override
    public void createToken(Token token) {
        System.out.println("DEBUG:: => " + JSON.toJSONString(token));
        tokenMapper.createToken(token);
    }

    @Override
    public void deleteToken(String tokenId) {
        tokenMapper.deleteToken(tokenId);
    }

    @Override
    public Token getToken(String tokenId) {
        return tokenMapper.getToken(tokenId);
    }
}
