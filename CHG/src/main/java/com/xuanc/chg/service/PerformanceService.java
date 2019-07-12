package com.xuanc.chg.service;

import com.xuanc.chg.dao.PerformanceMapper;
import com.xuanc.chg.model.Performance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName    chg-Performance
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/23 下午8:26
 * @version     1.0
 */
@Service
public class PerformanceService {

    @Autowired
    private PerformanceMapper performanceMapper;

    @Autowired
    private PlanService planService;

    /**
     * 添加演出厅
     */
    public void addPerformance(Performance performance) throws DataAccessException {
        performanceMapper.addPerformance(performance);
    }

    /**
     * 获取所有的演出厅信息
     */
    public List<Performance> getAllPerformance() {
        return performanceMapper.getPerformancesDetail();
    }

    /**
     * 获取指定的演出厅信息
     */
    public Performance getPerformance(int id) {
        return performanceMapper.getPerformanceDetail(id);
    }

    /**
     * 删除演出厅
     * --------
     */
    public String delPerformance(int id) {

        // if 有未完成的演出计划 return "该演出厅尚有未完的演出计划，无法删除"
        if (performanceMapper.delPerformance(id) == 1) {
            return "删除成功";
        } else {
            return "删除失败, 演出厅不存在";
        }
    }

    /**
     * 更新演出厅
     */
    public boolean updatePerformance(Performance performance) {

        // TODO 已有该演出厅的演出计划，禁止更新 return "该演出厅尚有未完成的演出计划，无法更新"

        return performanceMapper.updatePerformance(performance) == 1;

    }

}
