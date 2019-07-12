package com.lims.service;

import com.lims.model.Laboratory;
import com.lims.model.LabsMessage;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * ClassName    LIMS-LabService
 * Description
 *
 * @author xuanc
 * @version 1.0
 * @date 2019/6/12 下午12:34
 */
public interface LabService {

    /**
     * 添加实验室
     * @param laboratory ..
     * @return ..
     */
    int addLab(Laboratory laboratory) throws DataAccessException;

    /**
     * 删除实验室
     * @param id 实验室 id
     * @return ..
     */
    int delLab(int id) throws DataAccessException;

    /**
     * 修改实验室信息
     * @param laboratory ..
     * @return ..
     */
    int updateLab(Laboratory laboratory) throws DataAccessException ;

    /**
     * 获取所有实验室的基础信息
     * @return list
     */
    List<LabsMessage> getAllBaseDetail(int number);

    Laboratory getLabDetail(int id);

}
