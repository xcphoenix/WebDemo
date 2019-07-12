package com.xuanc.chg.dao;

import com.xuanc.chg.model.Ticket;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName    chg-TickerMapper
 * Description
 *
 * @author xuanc
 * @version 1.0
 * @date 2019/6/25 上午12:17
 */
@Repository
public interface TicketMapper {

    /**
     * 添加票
     */
    int addTicket(@Param("ticket") Ticket ticket);

    /**
     * 获取用户所有票的信息
     */
    List<Ticket> getAllTicketsByUser(@Param("userId") int userId);

    /**
     * 获取指定演出计划的数量
     */
    int getNumberAsPlan(@Param("planId") int planId);

}
