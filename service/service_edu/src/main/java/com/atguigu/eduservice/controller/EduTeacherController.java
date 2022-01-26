package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author ljn
 * @since 2021-11-25
 */
@RestController
@RequestMapping("/eduservice/teacher")
//@CrossOrigin
public class EduTeacherController {
    // teacherService会自动调用mapper
    @Autowired
    private EduTeacherService teacherService;

    // 1查询所有教师数据
    @GetMapping("/getAllTeacher")
    public R getAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items", list);
    }

    // 2逻辑删除讲师
    @DeleteMapping("/logicDeleteTeacher/{id}")
    public R logicDeleteTeacher(@PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 3讲师列表分页查询
    @GetMapping("/getTeacherSplitPage/{current}/{size}")
    public R getTeacherSplitPage(@PathVariable long current, @PathVariable long size) {
        Page<EduTeacher> page = new Page<>(current, size);
        teacherService.page(page, null);
//        Map<String, Object> map = new HashMap<>();
//        map.put("total", page.getTotal());
//        map.put("rows", page.getRecords());
//        return R.ok().data(map);
        return R.ok().data("total", page.getTotal()).data("rows", page.getRecords());
    }

    // 4讲师列表带条件分页查询
    @PostMapping("/getTeacherSplitPageByCondition/{current}/{size}")
    public R getTeacherSplitPageByCondition(@PathVariable long current,
                                            @PathVariable long size,
                                            @RequestBody(required = false) TeacherQuery teacherQuery) {
//        int a = 10 / 0;
        Page<EduTeacher> page = new Page<>(current, size);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        // 判断是否为空或者是否为空串
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }

        wrapper.orderByDesc("gmt_create");

        teacherService.page(page, wrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("rows", page.getRecords());
        return R.ok().data(map);
    }

    // 5添加讲师
    @PostMapping("/insertTeacher")
    public R insertTeacher(@RequestBody EduTeacher teacher) {
        boolean flag = teacherService.save(teacher);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 6通过id查询讲师
    @GetMapping("/getTeacherById/{id}")
    public R getTeacherById(@PathVariable String id) {
        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }

    // 6讲师修改
    @PutMapping("/updateTeacherById")
    public R updateTeacherById(@RequestBody EduTeacher teacher) {
        boolean flag = teacherService.updateById(teacher);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

