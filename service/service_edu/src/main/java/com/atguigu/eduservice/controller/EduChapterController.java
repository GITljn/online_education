package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.chapter.VideoVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/eduservice/chapter")
//@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    // 获取章节和小结
    @GetMapping("/getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> chapterAndVideo = chapterService.getChapterVideo(courseId);
        return R.ok().data("chapterAndVideo", chapterAndVideo);
    }

    // 添加章节
    @PostMapping("/insertChapter")
    public R insertChapter(@RequestBody EduChapter chapter) {
        chapterService.save(chapter);
        return R.ok();
    }

    // 删除章节
    @DeleteMapping("deleteChapter/{id}")
    public R deleteChapter(@PathVariable String id) {
        chapterService.deleteChapter(id);
        return R.ok();
    }

    // 查询章节
    @GetMapping("selectChapter/{id}")
    public R selectChapter(@PathVariable String id) {
        EduChapter chapter = chapterService.getById(id);
        return R.ok().data("chapter", chapter);
    }

    // 修改章节
    @PutMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter chapter) {
        boolean update = chapterService.updateById(chapter);
        if (update == true) {
            return R.ok();
        } else {
            throw new GuliException(20001, "修改章节信息异常");
        }
    }
}

