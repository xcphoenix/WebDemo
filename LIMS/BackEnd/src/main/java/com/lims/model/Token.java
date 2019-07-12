package com.lims.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * ClassName    LIMS-Token
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/14 上午11:48
 * @version     1.0
 */ 
public class Token {

    private String tokenId;
    private Long userId;
    private String userRole;
    private Timestamp tokenTime;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Timestamp getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(Timestamp tokenTime) {
        this.tokenTime = tokenTime;
    }
}
