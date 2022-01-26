package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.subject.FirstSubject;
import com.atguigu.eduservice.entity.subject.SecondSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author ljn
 * @since 2021-11-30
 */
@RestController
@RequestMapping("/eduservice/subject")
//@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    // 添加课程分类，无法指定具体的路径，可以通过上传文件的方式添加
    @PostMapping("/insertSubject")
    public R insertSubject(MultipartFile file) {
        subjectService.insertSubject(file, subjectService);
        return R.ok();
    }

    // 课程分类列表
    @GetMapping("/getAllSubject")
    public R getAllSubject() {
        List<FirstSubject> subjectList = subjectService.getAllSubject();
        return R.ok().data("list", subjectList);
    }
}

