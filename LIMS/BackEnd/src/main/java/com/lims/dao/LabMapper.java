package com.lims.dao;

import com.lims.model.Laboratory;
import com.lims.model.LabsMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName    LIMS-LabMapper
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/12 下午12:05
 * @version     1.0
 */
@Repository
public interface LabMapper {

    /**
     * 添加实验室
     * @param laboratory 实验室
     * @return ...
     */
    int addLab(@Param("lab") Laboratory laboratory) throws DataAccessException;

    /**
     * 删除实验室
     * @param id　实验室 id
     * @return ..
     */
    int delLab(@Param("id") int id);

    /**
     * 修改实验室设备信息
     * @param laboratory ..
     * @return ..
     */
    int updateLab(@Param("lab") Laboratory laboratory) throws DataAccessException;

    /**
     * 获取实验室设备基本信息
     * @return list
     */
    List<LabsMessage> getAllBaseDetail();

    /**
     * 获取指定实验室信息
     * @return Laboratory message
     */
    Laboratory getLabDetail(@Param("id") int id);

}
