package com.lims.controller;

import com.alibaba.fastjson.JSON;
import com.lims.model.*;
import com.lims.service.AuthService;
import com.lims.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName    LIMS-Order
 * Description  预定实验室
 * TODO         >> 公告相关
 * @author      xuanc
 * @date        2019/6/14 下午10:05
 * @version     1.0
 */
@RestController
@RequestMapping("/schedule")
@PropertySource(value = "classpath:other.properties")
public class OrderControl {

    @Value("${order.day.offset}")
    private int offsetDay;
    @Value("${order.day.number}")
    private int numberDay;

    private OrderService orderService;
    private AuthService authService;

    @Autowired
    public OrderControl(OrderService orderService, AuthService authService) {
        this.orderService = orderService;
        this.authService = authService;
    }

    /**
     * 预定实验室
     * @param order 预定的时间
     *              {
     *                   "date": ...,
     *                   "stage": ...
     *              }
     * @return ..
     *          实验室不存在
     *          错误的预定时间
     *          用户不存在 -> 服务器异常
     *          实验室已被预定
     *          预定实验室成功
     */
    @RequestMapping(value = "/order/", method = RequestMethod.POST)
    public Result orderLab(@RequestBody Order order, HttpServletRequest request) {
        order.setoUserId(authService.getCurrentUser(request));

        if (orderService.checkIsUsed(order)) {
            return new Result(200, "预定失败,实验室已被预定", order.getoLabId(), false);
        }

        if (orderService.orderLab(order) != 1) {
            return new Result(200, "服务器异常", order.getoLabId(), false);
        } else {
            return new Result(200, "预定成功", order.getoLabId(), true);
        }
    }

    /**
     * 返回实验室可以被预定的时间点
     */
    @RequestMapping(value = "/order/available/{id}", method = RequestMethod.GET)
    public Result getAvailableTime(@PathVariable("id") int id) {
        List<Order> orderList = orderService.getLabOrders(id);
        LabOrderDetail labOrderDetail = new LabOrderDetail();
        labOrderDetail.setId(id);
        labOrderDetail.setOrder(new ArrayList<>());

        System.out.println("DEBUG:: orderList = " + JSON.toJSONString(orderList));

        if (orderList == null) {
            return new Result(200, "该实验室已被预定完毕，暂不可用", null, false);
        }
        for (int i = 0; i < offsetDay; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < numberDay; j++) {
                Order order = new Order();
                order.setDate(i + 1);
                order.setStage(j + 1);
                if (!orderList.contains(order)) {
                    list.add(j + 1);
                }
            }
            if (list.size() != 0) {
                OrderTime orderTime = new OrderTime();
                orderTime.setDate(i + 1);
                orderTime.setStage(list);
                labOrderDetail.getOrder().add(orderTime);
            }
        }
        System.out.println("DEBUG:: " + JSON.toJSONString(labOrderDetail));

        return new Result(200, "查询成功", labOrderDetail, true);
    }


    /**
     * 返回用户预定的（有效）实验室
     * @return list
     *        查询成功
     *        用户不存在
     */
    @RequestMapping(value = "/order/user", method = RequestMethod.GET)
    public Result getUserOrders(HttpServletRequest request) {
        Long userId = authService.getCurrentUser(request);
        List orderList = orderService.getUserOrderList(userId);
        return new Result(200, "查找成功", orderList, true);
    }

    /**
     * 取消实验室预定单
     * @param id 预定 id
     * @return ..
     */
    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    public Result deleteOrder(@PathVariable("id") int id, HttpServletRequest request) {
        Long userId = authService.getCurrentUser(request);
        if (orderService.deleteOrder(id, userId) == 1) {
            return new Result(200, "删除成功", null, true);
        } else {
            return new Result(200, "删除失败，无效预定", null, false);
        }
    }



    // /**
    //  * 禁用实验室预定时间点
    //  * @param id 实验室 id
    //  * @param jsonObject
    //  *        [
    //  *          {
    //  *              "date": ...,
    //  *              "stage": ...
    //  *          },
    //  *          {
    //  *
    //  *          }
    //  *          ...
    //  *        ]
    //  */
    // @RequestMapping(value = "/admin/order/lab/{id}", method = RequestMethod.POST)
    // public Result disableLabOrder(@PathVariable("id") int id, @RequestBody JSONObject jsonObject) {
    //     return null;
    // }
    //
    // /**
    //  * 返回指定实验室的（有效）预定情况
    //  * @param id　实验室 id
    //  * @return
    //  *          查看成功
    //  *          实验室不存在
    //  */
    // @RequestMapping(value = "/order/lab/{id}", method = RequestMethod.GET)
    // public Result getLabOrderDetail(@PathVariable("id") int id) {
    //     return null;
    // }
    //
    // /**
    //  * 返回所有实验室的所有（有效）预定情况
    //  * @return ...
    //  */
    // @RequestMapping(value = "/order/labs", method = RequestMethod.GET)
    // public Result getAllOrders() {
    //     return null;
    // }
}
