package com.lims.dao;

import com.lims.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName    LIMS-UserMapper
 * Description  
 * TODO         url, 邮箱、用户名、手机号加上正则判断
 * @author      xuanc
 * @date        2019/6/12 上午7:44
 * @version     1.0
 */
@Repository
public interface UserMapper {

    /**
     * 添加老师
     * @param teacher ..
     * @return 改变的行数
     */
    int addTeacher(@Param("teacher") User teacher);

    /**
     * 删除老师
     * @param id ..
     * @return ..
     */
    int deleteTeacher(@Param("id") int id) throws DataAccessException;

    /**
     * 修改老师基本信息（不会更新头像 url 和 密码）
     * @param teacher ..
     * @return ..
     */
    int changeTeacher(@Param("teacher") User teacher);

    /**
     * 根据教师 id 获取教师信息
     * @param id ..
     * @return ..
     */
    User getDetailTeacher(@Param("id") Long id);

    /**
     * 更新用户头像
     * @param url 头像 url
     * @param id 用户 id
     * @return ..
     */
    int updateUserAvatar(@Param("url") String url, @Param("id") Long id);

    /**
     * 校验密码
     * @param userId 用户 id
     * @param passwd 用户密码
     * @return ..
     */
    int checkUserPasswd(@Param("userId") long userId, @Param("passwd") String passwd);

    /**
     * 重置用户密码
     * @param newPasswd 新密码
     * @return ..
     */
    int resetUserPasswd(@Param("userId") long userId, @Param("newPasswd") String newPasswd);

    /**
     * 获取所有教师的信息
     * @return list
     */
    List<User> getAllTeachers();

    String getUserRole(@Param("userId") Long id);
}
