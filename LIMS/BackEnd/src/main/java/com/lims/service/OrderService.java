package com.lims.service;

import com.lims.model.Order;
import com.lims.model.UserOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName    LIMS-OrderService
 * Description
 *
 * @author xuanc
 * @version 1.0
 * @date 2019/6/16 下午2:49
 */
public interface OrderService {

    /**
     * 预定实验室 ...
     * @param order 订单
     * @return ...
     */
    int orderLab(Order order);

    /**
     * 检查实验室可否被预定
     */
    boolean checkIsUsed(Order order);

    /**
     * 检查实验室是否可用
     */
    int labCanChecked(int id);

    /**
     * 获取实验室可以使用的 时间点
     */
    List<Order> getLabOrders(int id);

    /**
     * 获取用户预定情况
     * @param userId 用户 id
     * @return ..
     */
    List<UserOrder> getUserOrderList(long userId);

    /**
     * 删除订单
     * @param scheduleId ..
     * @return ..
     */
    int deleteOrder(int scheduleId, long userId);

}
