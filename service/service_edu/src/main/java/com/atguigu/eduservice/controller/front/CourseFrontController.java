package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.frontvo.CourseFrontVo;
import com.atguigu.eduservice.entity.frontvo.CourseWebVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.servicebase.vo.OrderCourseInfoVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduChapterService chapterService;

    @PostMapping("/{current}/{size}")
    public R pageList(@PathVariable long current,
                      @PathVariable long size,
                      @RequestBody CourseFrontVo courseFrontVo) {
        Page<EduCourse> page = new Page<>(current, size);
        Map<String, Object> map = courseService.pageList(page, courseFrontVo);

        return R.ok().data(map);
    }

    @GetMapping("/{courseId}")
    public R getById(@PathVariable String courseId) {
        CourseWebVo courseWebVo = courseService.selectInfoWebById(courseId);
        List<ChapterVo> chapterVideo = chapterService.getChapterVideo(courseId);
        return R.ok().data("course", courseWebVo).data("chapterVoList", chapterVideo);
    }

    @GetMapping("/selectOrderCourseInfo/{courseId}")
    public OrderCourseInfoVo selectOrderCourseInfo(@PathVariable String courseId) {
        CourseWebVo courseWebVo = courseService.selectInfoWebById(courseId);
        OrderCourseInfoVo orderCourseInfoVo = new OrderCourseInfoVo();
        BeanUtils.copyProperties(courseWebVo, orderCourseInfoVo);
        return  orderCourseInfoVo;
    }
}
