package com.xuanc.chg.service;

import com.xuanc.chg.dao.PlanMapper;
import com.xuanc.chg.dao.TicketMapper;
import com.xuanc.chg.model.Performance;
import com.xuanc.chg.model.Plan;
import com.xuanc.chg.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName    chg-TicketService
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/25 上午1:02
 * @version     1.0
 */
@Service
public class TicketService {

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private TicketMapper ticketMapper;

    public String orderPlan(int userId, int planId) {

        String layout = planMapper.getPlanLayout(planId);
        if (layout == null) {
            return "预定失败";
        }
        StringBuilder layoutBuilder = new StringBuilder(layout);
        Ticket ticket = new Ticket();
        ticket.setUserId(userId);
        ticket.setPlanId(planId);
        Plan tmpPlan = planMapper.getPlanDetail(planId);
        if (tmpPlan == null || tmpPlan.getPerformanceId() == null) {
            return  "无效的演出计划";
        }
        Performance tmpPerformance = performanceService.getPerformance(tmpPlan.getPerformanceId());
        if (performanceService == null) {
            return "无效的演出计划";
        }

        ticket.setPlayPrice(tmpPlan.getPlayPrice());
        ticket.setPlanTime(tmpPlan.getPlanTime());
        ticket.setPerformanceName(tmpPerformance.getPerformanceName());
        ticket.setPerformanceArea(tmpPerformance.getPerformanceArea());

        int seatRow = tmpPerformance.getPerformanceRow();
        int seatCol = tmpPerformance.getPerformanceCol();

        for (int i = 0; i < layoutBuilder.length(); i++) {
            if (layoutBuilder.charAt(i) == '1') {
                layoutBuilder.setCharAt(i, '0');
                planMapper.updatePlanLayout(planId, layoutBuilder.toString());
                ticket.setSeatRow((i+1) / seatRow + 1);
                ticket.setSeatCol((i+1) % seatCol == 0 ? seatCol : (i+1) % seatCol);
                ticketMapper.addTicket(ticket);
                return "预定成功!";
            }
        }

        return "预定失败,票已售空!";
    }

    public List<Ticket> getOrderedTickets(int userId) {
        return ticketMapper.getAllTicketsByUser(userId);
    }

}
