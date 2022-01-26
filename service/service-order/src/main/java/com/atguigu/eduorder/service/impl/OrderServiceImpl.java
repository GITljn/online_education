package com.atguigu.eduorder.service.impl;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.eduorder.client.CourseClient;
import com.atguigu.eduorder.client.UcenterClient;
import com.atguigu.eduorder.entity.Order;
import com.atguigu.eduorder.mapper.OrderMapper;
import com.atguigu.eduorder.service.OrderService;
import com.atguigu.eduorder.utils.OrderNoUtil;
import com.atguigu.servicebase.vo.OrderCourseInfoVo;
import com.atguigu.servicebase.vo.OrderMemberInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author ljn
 * @since 2022-01-05
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private CourseClient courseClient;
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String insertOrder(String courseId, HttpServletRequest request) {
        Order order = new Order();
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        String orderNo = OrderNoUtil.getOrderNo();
        OrderCourseInfoVo orderCourseInfoVo = courseClient.selectOrderCourseInfo(courseId);
        OrderMemberInfoVo orderMemberInfoVo = ucenterClient.selectOrderMemberInfo(memberId);

        order.setOrderNo(orderNo);
        order.setCourseId(courseId);
        order.setCourseTitle(orderCourseInfoVo.getTitle());
        order.setCourseCover(orderCourseInfoVo.getCover());
        order.setTeacherName(orderCourseInfoVo.getTeacherName());
        order.setTotalFee(orderCourseInfoVo.getPrice());
        order.setMemberId(memberId);
        order.setNickname(orderMemberInfoVo.getNickname());
        order.setMobile(orderMemberInfoVo.getMobile());
        order.setPayType(1);
        order.setStatus(0);
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
