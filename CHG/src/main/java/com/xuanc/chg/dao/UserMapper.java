package com.xuanc.chg.dao;

import com.xuanc.chg.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName    chg-UserMapper
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/22 下午1:06
 * @version     1.0
 */
@Repository
public interface UserMapper {

    /**
     * 判断是否可以登录
     */
    int canLogin(@Param("name") String name, @Param("passwd") String passwd);

    /**
     * 获取用户角色
     */
    String getUserRole(@Param("name") String name);

    /**
     * 添加用户
     */
    int addUser(@Param("user") User user) throws DataAccessException;

    /**
     * 获取所有用户
     */
    List<User> getAllUser();

    /**
     *　获取当前用户信息
     */
    User getProfile(@Param("username") String username);

    /**
     * 获取用户 id
     */
    int getUserIdByName(@Param("username") String username);

    /**
     * 更新用户信息
     */
    int updateProfile(@Param("user") User user) throws DataAccessException;

    /**
     * 更新密码
     */
    int updateUserPasswd(@Param("username") String username, @Param("passwd") String passwd);

    /**
     * 判断用户是否存在
     */
    int isExist(@Param("id") int id);

    /**
     * 重置用户密码
     */
    void resetPasswd(@Param("userId") int userId, @Param("passwd") String passwd);

    /**
     * 删除用户
     */
    int deleteUser(@Param("id") int id) throws DataAccessException;

}
