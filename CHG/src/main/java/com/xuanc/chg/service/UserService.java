package com.xuanc.chg.service;

import com.xuanc.chg.dao.UserMapper;
import com.xuanc.chg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName    chg-UserService
 * Description
 *
 * @author xuanc
 * @version 1.0
 * @date 2019/6/22 下午1:06
 */
@Service
public class UserService {

    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 校验用户名和密码
     */
    public boolean checkCanLogin(String username, String password) {
        return userMapper.canLogin(username, password) == 1;
    }

    /**
     * 获得用户角色
     */
    public String getUserRole(String username) {
        return userMapper.getUserRole(username);
    }

    /**
     * 用户注册
     */
    public boolean registerUser(User user) throws DataAccessException {
        return userMapper.addUser(user) == 1;
    }

    /**
     * 获取所有用户的信息
     */
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    /**
     * 获取当前用户信息
     */
    public User getProfile(String username) {
        return userMapper.getProfile(username);
    }

    /**
     * 获取当前用户 id
     */
    public int getUserId(String username) {
        return userMapper.getUserIdByName(username);
    }

    /**
     * 更新个人信息
     */
    public boolean updateUserProfile(User user) throws DataAccessException {
        return userMapper.updateProfile(user) == 1;
    }

    /**
     * 更新密码
     */
    public boolean updateUserPasswd(String username, String passwd) {
        return userMapper.updateUserPasswd(username, passwd) == 1;
    }

    /**
     * 重置用户密码
     */
    public String resetPasswd(int userId, String passwd) {
        if (userMapper.isExist(userId) != 1) {
            return "用户不存在";
        }
        userMapper.resetPasswd(userId, passwd);
        return "重置成功";
    }

    /**
     * 删除用户
     */
    public int delUser(int userId) throws DataAccessException {
        return userMapper.deleteUser(userId);
    }

}
