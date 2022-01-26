package com.atguigu.eduorder.service;

import com.atguigu.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author ljn
 * @since 2022-01-05
 */
public interface OrderService extends IService<Order> {

    String insertOrder(String courseId, HttpServletRequest request);
}
