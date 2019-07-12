package com.xuanc.chg.service;

import com.alibaba.fastjson.JSON;
import com.xuanc.chg.dao.PerformanceMapper;
import com.xuanc.chg.dao.PlanMapper;
import com.xuanc.chg.model.Performance;
import com.xuanc.chg.model.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName    chg-PlanService
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/24 下午7:22
 * @version     1.0
 */
@Service
public class PlanService {

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private PerformanceMapper performanceMapper;

    /**
     * 数据绑定
     */
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //注册自定义的编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 添加演出计划
     */
    public boolean addPlan(Plan plan) throws DataAccessException {
        Performance performance = performanceMapper.getPerformanceDetail(plan.getPerformanceId());
        int count = 0;
        if (performance != null) {
            count = performance.getPerformanceCol() * performance.getPerformanceRow();
        }

        // TODO 判断时间段是否含有交集

        planMapper.addPlan(plan, count);
        return true;
    }

    /**
     * 删除演出计划
     */
    public String delPlan(int id) {

        if (planMapper.isExist(id) != 1) {
            return "无效的演出计划";
        }

        // 检查是否有用户预定了演出计划
        // if (true) {
        //     planMapper.delPlan(id);
        //     return "删除成功";
        // }

        if (planMapper.delPlan(id) == 1) {
            return "删除成功";
        } else {
            return "该演出计划尚未完成，无法删除";
        }
    }

    /**
     * 获取所有的演出计划
     */
    public Map<Plan, Performance> getAllPlan() {
        Map<Plan, Performance> planPerformanceMap = new LinkedHashMap<Plan, Performance>();
        List<Plan> plans = planMapper.getAllPlan();

        System.out.println("DEBUG:: " + JSON.toJSONString(plans));
        for (Plan plan : plans) {
            System.out.println("DEBUG:: plan -> " + JSON.toJSONString(plan) + ", " + plan.getPerformanceId());
            planPerformanceMap.put(plan,
                    performanceMapper.getPerformanceDetail(plan.getPerformanceId() == null ? -1 : plan.getPerformanceId()));
        }

        System.out.println("DEBUG:: Map => " + JSON.toJSON(planPerformanceMap));

        return planPerformanceMap;
    }

    /**
     * 获取可用的的演出计划
     */
    public Map<Plan, Performance> getAvaPlans() {
        Map<Plan, Performance> planPerformanceMap = new LinkedHashMap<Plan, Performance>();
        List<Plan> plans = planMapper.getAvaPlans();

        for (Plan plan : plans) {
            planPerformanceMap.put(plan,
                    performanceMapper.getPerformanceDetail(plan.getPerformanceId() == null ? -1 : plan.getPerformanceId()));
        }

        System.out.println("DEBUG:: Map => " + JSON.toJSON(planPerformanceMap));

        return planPerformanceMap;
    }

}
