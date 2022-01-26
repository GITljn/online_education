package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.entity.vo.CourseQuery;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author ljn
 * @since 2021-12-01
 */
@RestController
@RequestMapping("/eduservice/course")
//@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduCourseDescriptionService descriptionService;
    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private EduVideoService videoService;

    @PostMapping("/insertCourseInfo")
    public R insertCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String courseId = courseService.insertCourseInfo(courseInfoVo);
        return R.ok().data("courseId", courseId);
    }

    @GetMapping("/getCourseInfoById/{id}")
    public R getCourseInfoById(@PathVariable String id) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfoById(id);

        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    @GetMapping("/getCoursePublish/{id}")
    public R getCoursePublish(@PathVariable String id) {
        CoursePublishVo coursePublish = courseService.getCoursePublish(id);
        return R.ok().data("coursePublish", coursePublish);
    }

    @PutMapping("/publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse course = courseService.getById(id);
        course.setStatus("Normal");
        courseService.updateById(course);
        return R.ok();
    }

    @PostMapping("/getCourseByCondition/{current}/{size}")
    public R getCourseByCondition(@RequestBody CourseQuery courseQuery,
                                  @PathVariable Integer current,
                                  @PathVariable Integer size) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        Integer minPrice = courseQuery.getMinPrice();
        Integer maxPrice = courseQuery.getMaxPrice();
        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(status)) {
            queryWrapper.eq("status", status);
        }
        if (!StringUtils.isEmpty(minPrice)) {
            queryWrapper.ge("price", minPrice);
        }
        if (!StringUtils.isEmpty(maxPrice)) {
            queryWrapper.le("price", maxPrice);
        }
        Page<EduCourse> coursePage = new Page<>(current, size);
        courseService.page(coursePage, queryWrapper);
        return R.ok().data("courseList", coursePage.getRecords()).data("total", coursePage.getTotal());
    }

    @DeleteMapping("/deleteCourse/{id}")
    public R deleteCourse(@PathVariable String id) {
        courseService.removeById(id);
        descriptionService.removeById(id);
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", id);
        chapterService.remove(chapterQueryWrapper);
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", id);
        List<EduVideo> eduVideos = videoService.list(videoQueryWrapper);
        for (EduVideo eduVideo : eduVideos) {
            videoService.deleteVideo(eduVideo.getId());
        }
        return R.ok();
    }
}

