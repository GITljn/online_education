package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.frontvo.CourseWebVo;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author ljn
 * @since 2021-12-01
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CourseInfoVo getCourseInfoById(String id);

    CoursePublishVo coursePublish(String id);

    CourseWebVo selectInfoWebById(String courseId);
}
