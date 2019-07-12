package com.lims.service.impl;

import com.lims.dao.UserMapper;
import com.lims.model.User;
import com.lims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * ClassName    LIMS-UserServiceImpl
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/12 上午7:43
 * @version     1.0
 */
@Service
@PropertySource(value = "classpath:fileSaved.properties")
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Value("${avatar.url.prefix}")
    private String urlPrefix;
    @Value("${avatar.dir.prefix}")
    private String dirPrefix;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int addTeacher(User user) {
        return userMapper.addTeacher(user);
    }

    @Override
    public int delTeacher(int id) throws DataAccessException {
        return userMapper.deleteTeacher(id);
    }

    @Override
    public int updateTeacher(User teacher) {
        return userMapper.changeTeacher(teacher);
    }

    @Override
    public User getDetailTeacher(Long id, boolean isAdmin, Long currentUserId) {
        if (isAdmin) {
            return userMapper.getDetailTeacher(id);
        } else {
            return userMapper.getDetailTeacher(currentUserId);
        }
    }

    @Override
    public String updateUserAvatar(MultipartFile file, Long currentUserId) throws MultipartException, IOException {

        String fileOriginPath = file.getOriginalFilename();
        String fileName = String.valueOf(currentUserId) + "-" +
                UUID.randomUUID() + fileOriginPath.substring(fileOriginPath.lastIndexOf("."));
        File dest = new File(fileName);
        file.transferTo(dest);

        // DEBUG
        System.out.println("DEBUG:: fileName = " + fileName + ", Path = " + dest.getAbsolutePath() + "\n");

        String avatarUrl = urlPrefix + dirPrefix + fileName;
        userMapper.updateUserAvatar(avatarUrl, currentUserId);

        return avatarUrl;
    }

    @Override
    public Boolean checkUserPasswd(Long id, String passwd) {
        return userMapper.checkUserPasswd(id, passwd) == 1;
    }

    @Override
    public int resetUserPasswd(Long userId, String newPasswd) {
        return userMapper.resetUserPasswd(userId, newPasswd);
    }

    @Override
    public String getUserRole(Long id) {
        return userMapper.getUserRole(id);
    }

    @Override
    public List<User> getAllUserDetail() {
        return userMapper.getAllTeachers();
    }
}
