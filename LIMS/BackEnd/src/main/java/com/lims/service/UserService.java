package com.lims.service;

import com.lims.model.LabsMessage;
import com.lims.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * ClassName    LIMS-UserService
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/12 上午7:42
 * @version     1.0
 */ 
public interface UserService {

    /**
     * 添加老师
     * @param user teacher object
     * @return success or fail
     */
    int addTeacher(User user);

    /**
     * 删除老师
     * @param id 老师 id
     * @return ..
     */
    int delTeacher (int id) throws DataAccessException;

    /**
     * 更新老师信息
     * @param teacher ..
     * @return ..
     */
    int updateTeacher(User teacher);

    /**
     * 查询用户信息
     * @param id 用户 id
     * @param isAdmin 是否处于管理状态
     * @return ..
     */
    User getDetailTeacher(Long id, boolean isAdmin, Long currentUserId);

    /**
     * 更新用户头像
     */
    String updateUserAvatar(MultipartFile file, Long currentUserId) throws MultipartException, IOException;

    /**
     * 获取所有用户的信息
     */
    List<User> getAllUserDetail();

    Boolean checkUserPasswd(Long id, String passwd);

    int resetUserPasswd(Long userId, String newPasswd);

    String getUserRole(Long id);
}
