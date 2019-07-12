package com.xuanc.chg.dao;

import com.xuanc.chg.model.Performance;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName    chg-PerformanceMapper
 * Description
 *
 * @author xuanc
 * @version 1.0
 * @date 2019/6/23 下午8:26
 */
@Repository
public interface PerformanceMapper {

    /**
     * 添加演出厅
     */
    int addPerformance(@Param("performance")Performance performance) throws DataAccessException;

    /**
     * 删除演出厅
     */
    int delPerformance(@Param("id") int id);

    /**
     * 获取所有的演出厅信息
     */
    List<Performance> getPerformancesDetail();

    /**
     * 获取指定演出厅的信息
     */
    Performance getPerformanceDetail(@Param("id") int id);

    /**
     * 更新演出厅信息
     * -----------
     * TODO 已被预定的演出厅无法更新
     */
    int updatePerformance(@Param("performance") Performance performance) throws DataAccessException;

}
