package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author ljn
 * @since 2021-12-01
 */
@RestController
@RequestMapping("/eduservice/video")
//@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;
    @Autowired
    private VodClient vodClient;

    @PostMapping("insertVideo")
    public R insertVideo(@RequestBody EduVideo video) {
        videoService.save(video);
        return R.ok();
    }

    @DeleteMapping("deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id) {
        videoService.deleteVideo(id);
        return R.ok();
    }

    @PutMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo video) {
        videoService.updateById(video);
        return R.ok();
    }

    @GetMapping("selectVideo/{id}")
    public R selectVideo(@PathVariable String id) {
        EduVideo video = videoService.getById(id);
        return R.ok().data("video", video);
    }
}

