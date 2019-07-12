package com.lims.service;

import com.lims.model.Token;

/**
 * ClassName    LIMS-TokenService
 * Description
 *
 * @author xuanc
 * @version 1.0
 * @date 2019/6/14 下午1:28
 */
public interface TokenService {

    /**
     * 创建 token
     * @param token 创建 token
     */
    void createToken(Token token);

    /**
     * 删除 过期的token
     * @param tokenId tokenId
     */
    void deleteToken(String tokenId);

    /**
     * 获取 token 的创建时间
     * @param tokenId tokenId
     * @return ..
     */
    Token getToken(String tokenId);

}
