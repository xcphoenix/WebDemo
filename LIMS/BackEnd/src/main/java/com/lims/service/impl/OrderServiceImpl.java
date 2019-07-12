package com.lims.service.impl;

import com.lims.dao.OrderMapper;
import com.lims.model.Order;
import com.lims.model.UserOrder;
import com.lims.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName    LIMS-OrderServiceImpl
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/16 下午3:00
 * @version     1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    private OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public int orderLab(Order order) {
        return orderMapper.orderSchedule(order);
    }

    @Override
    public boolean checkIsUsed(Order order) {
        return orderMapper.checkIsOrdered(order) != 0;
    }

    @Override
    public int labCanChecked(int id) {
        return orderMapper.labCanChecked(id);
    }

    @Override
    public List<Order> getLabOrders(int id) {
        return orderMapper.getLabOrders(id);
    }

    @Override
    public List<UserOrder> getUserOrderList(long userId) {
        orderMapper.updateOrderStatus();
        return orderMapper.getUserOrderList(userId);
    }

    @Override
    public int deleteOrder(int scheduleId, long userId) {
        return orderMapper.deleteOrder(scheduleId, userId);
    }
}
