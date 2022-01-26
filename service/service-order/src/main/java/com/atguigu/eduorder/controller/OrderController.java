package com.atguigu.eduorder.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.eduorder.entity.Order;
import com.atguigu.eduorder.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author ljn
 * @since 2022-01-05
 */
@RestController
@RequestMapping("/eduorder/order")
//@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/insertOrder/{courseId}")
    public R insertOrder(@PathVariable String courseId, HttpServletRequest request) {
        String orderNo = orderService.insertOrder(courseId, request);
        return R.ok().data("orderNo", orderNo);
    }

    @GetMapping("/selectOrderByOrderNo/{orderNo}")
    public R selectOrderByOrderNo(@PathVariable String orderNo) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        Order order = orderService.getOne(queryWrapper);
        return R.ok().data("order", order);
    }

    @GetMapping("/selectIsBuyCourse/{courseId}")
    public R selectIsBuyCourse(@PathVariable String courseId, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (memberId == null) {
            return R.error().code(28004);
        }
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.eq("member_id", memberId);
        Order order = orderService.getOne(queryWrapper);
        boolean flag = false;
        if (order != null && order.getStatus() == 1) {
            flag = true;
        }
        return R.ok().data("flag", flag);
    }
}

