package com.lims.dao;

import com.lims.model.Token;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * ClassName    LIMS-TokenMapper
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/14 下午12:03
 * @version     1.0
 */
@Repository
public interface TokenMapper {

    /**
     * 生成 token
     * 将 token 存储到 Token tokenId 中
     */
    void createToken(@Param("token") Token token);

    /**
     * 删除过期 token
     * @param tokenId tokenId
     */
    void deleteToken(@Param("tokenId") String tokenId);

    /**
     * 获取 token
     * @param tokenId token id
     * @return 存储 token 的时间
     */
    Token getToken(@Param("tokenId") String tokenId);
}
