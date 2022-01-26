package com.atguigu.eduorder.client;

import com.atguigu.servicebase.vo.OrderCourseInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-edu")
public interface CourseClient {
    @GetMapping("/eduservice/coursefront/selectOrderCourseInfo/{courseId}")
    OrderCourseInfoVo selectOrderCourseInfo(@PathVariable("courseId") String courseId);
}
