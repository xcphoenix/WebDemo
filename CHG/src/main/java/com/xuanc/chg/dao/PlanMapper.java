package com.xuanc.chg.dao;

import com.xuanc.chg.model.Plan;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName    chg-PlanMapper
 * Description
 *
 * @author xuanc
 * @version 1.0
 * @date 2019/6/24 下午6:49
 */
@Repository
public interface PlanMapper {

    /**
     * 添加演出计划
     */
    int addPlan(@Param("plan") Plan plan, @Param("count") int count) throws DataAccessException;

    /**
     * 删除演出计划
     */
    int delPlan(@Param("planId") int id);

    /**
     * 查询在performanceId演出厅的演出计划数量
     */
    int searchOnPerId(@Param("perId") int perId);

    /**
     * 查询演出计划是否存在
     */
    int isExist(@Param("id") int id);

    /**
     * 获取所有的演出计划
     */
    List<Plan> getAllPlan();

    /**
     * 获取可用的演出计划
     */
    List<Plan> getAvaPlans();

    /**
     * 获取 plan 座位布局
     */
    String getPlanLayout(@Param("id") int id);

    /**
     * 更新 plan 座位布局
     */
    int updatePlanLayout(@Param("planId") int planId, @Param("layout") String layout);

    /**
     * 根据 id 获取 plan 信息
     */
    Plan getPlanDetail(@Param("id") int id);

}
