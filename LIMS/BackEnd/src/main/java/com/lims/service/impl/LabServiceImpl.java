package com.lims.service.impl;

import com.alibaba.fastjson.JSON;
import com.lims.dao.LabMapper;
import com.lims.dao.OrderMapper;
import com.lims.dao.UserMapper;
import com.lims.model.Laboratory;
import com.lims.model.LabsMessage;
import com.lims.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName    LIMS-LabServiceImpl
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/12 下午12:35
 * @version     1.0
 */
@Service
public class LabServiceImpl implements LabService {

    private LabMapper labMapper;
    private OrderMapper orderMapper;

    @Autowired
    public LabServiceImpl(LabMapper labMapper, OrderMapper orderMapper) {
        this.labMapper = labMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public int addLab(Laboratory laboratory) throws DataAccessException {
        return labMapper.addLab(laboratory);
    }

    @Override
    public int delLab(int id) throws DataAccessException {
        return labMapper.delLab(id);
    }

    @Override
    public int updateLab(Laboratory laboratory) throws DataAccessException {
        return labMapper.updateLab(laboratory);
    }

    @Override
    public List<LabsMessage> getAllBaseDetail(int number) {
        List<LabsMessage> labsMessagesList = labMapper.getAllBaseDetail();
        for (LabsMessage labsMessage : labsMessagesList) {
            List<Laboratory> laboratoryList = JSON.parseArray(labsMessage.getLaboratoryList(), Laboratory.class);

            System.out.println("DEBUG:: List<Laboratory> laboratoryList ==> " + JSON.toJSONString(laboratoryList));

            for (int i = 0; i < laboratoryList.size(); i++) {
                if (orderMapper.labCanChecked(laboratoryList.get(i).getLabId()) < number) {
                    laboratoryList.get(i).setXz("可选");
                } else {
                    laboratoryList.get(i).setXz("不可选");
                }
            }

            labsMessage.setLaboratoryList(JSON.toJSONString(laboratoryList));

            System.out.println("DEBUG:: List<Laboratory> laboratoryList ==> " + JSON.toJSONString(laboratoryList));
        }

        return labsMessagesList;
    }

    @Override
    public Laboratory getLabDetail(int id) {
        return labMapper.getLabDetail(id);
    }
}
