package com.lims.dao;

import com.lims.model.Order;
import com.lims.model.UserOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * ClassName    LIMS-OrderMapper
 * Description
 *
 * @author xuanc
 * @version 1.0
 * @date 2019/6/16 下午2:16
 */
@Repository
public interface OrderMapper {

    /**
     * 添加预定
     */
    int orderSchedule(@Param("order") Order order);

    /**
     * 检查实验室是否被预定
     */
    int checkIsOrdered(@Param("order") Order order);

    /**
     * 检查实验室是否可选
     */
    int labCanChecked(int id);

    /**
     * 获取实验室被预定的信息
     */
    List<Order> getLabOrders(@Param("labId") int labId);

    /**
     * 更新订单状态
     */
    void updateOrderStatus();

    /**
     * 获取用户预定情况
     * @param userId 用户 id
     * @return ..
     */
    List<UserOrder> getUserOrderList(@Param("userId") long userId);

    /**
     * 删除预定
     * @param scheduleId ..
     * @return
     */
    int deleteOrder(@Param("scheduleId") int scheduleId, @Param("userId") long userId);

}
