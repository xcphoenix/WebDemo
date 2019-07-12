package com.lims.controller;

import com.alibaba.fastjson.JSON;
import com.lims.model.Laboratory;
import com.lims.model.LabsMessage;
import com.lims.model.Result;
import com.lims.service.LabService;
import com.lims.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName    LIMS-LabControl
 * Description  实验室管理
 *
 * @author      xuanc
 * @date        2019/6/11 下午8:35
 * @version     1.0
 */
@PropertySource(value = "classpath:other.properties")
@RestController
@RequestMapping("/laboratories")
public class LabControl {

    private LabService labService;

    @Value("${order.day.offset}")
    private int day;
    @Value("${order.day.number}")
    private int number;

    @Autowired
    public LabControl(LabService labService) {
        this.labService = labService;
    }

    /**
     * 创建实验室
     */
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public Result addLab(@RequestBody Laboratory laboratory) {
        System.out.println(JSON.toJSON(laboratory));

        try {
            labService.addLab(laboratory);
        } catch (DataAccessException da) {
            if (da instanceof DuplicateKeyException) {
                da.printStackTrace();
                return new Result(200, "实验室地点已被占用", null, false);
            } else {
                da.printStackTrace();
                return new Result(200, "服务器异常", null, false);
            }
        }
        return new Result(200, "添加成功", laboratory.getLabId(), true);
    }

    /**
     * @param id 实验室 id
     * @return ...
     */
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
    public Result delLab(@PathVariable("id") int id) {
        try {
            if (labService.delLab(id) == 1) {
                return new Result(200, "删除成功", null, true);
            } else {
                return new Result(200, "服务器异常", null, false);
            }
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            return new Result(200, "删除失败，该实验室已被预定", null, false);
        }

    }

    /**
     * 更新实验室
     */
    @RequestMapping(value = "/admin/", method = RequestMethod.PUT)
    public Result updateLab(@RequestBody Laboratory laboratory) {
        try {
            labService.updateLab(laboratory);
        } catch (DataAccessException da) {
            if (da instanceof DuplicateKeyException) {
                da.printStackTrace();
                return new Result(200, "实验室地点已被占用", null, false);
            } else {
                da.printStackTrace();
                return new Result(200, "服务器异常", null, false);
            }
        }
        return new Result(200, "更新成功", null, true);
    }

    /**
     * 获取所有的实验室
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public Result getAllBaseDetails() {
        List labList = labService.getAllBaseDetail(number * day);
        if (labList != null) {
            return new Result(200, "获取成功", labList, true);
        } else {
            return new Result(200, "数据为空", null, false);
        }
    }

    /**
     * 查询实验室信息
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public Result getLabDetail(@PathVariable("id") int id) {
        Laboratory laboratory = labService.getLabDetail(id);
        if (laboratory == null) {
            return new Result(200, "实验室不存在", null, false);
        } else {
            return new Result(200, "查询成功", laboratory, true);
        }
    }

}
