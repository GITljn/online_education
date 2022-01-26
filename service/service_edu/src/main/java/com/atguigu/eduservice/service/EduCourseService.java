package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.frontvo.CourseFrontVo;
import com.atguigu.eduservice.entity.frontvo.CourseWebVo;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ljn
 * @since 2021-12-01
 */
public interface EduCourseService extends IService<EduCourse> {

    String insertCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfoById(String id);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getCoursePublish(String id);

    Map<String, Object> pageList(Page<EduCourse> page, CourseFrontVo courseFrontVo);

    CourseWebVo selectInfoWebById(String courseId);
}
