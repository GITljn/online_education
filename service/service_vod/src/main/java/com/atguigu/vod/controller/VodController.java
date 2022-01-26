package com.atguigu.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.commonutils.R;
import com.atguigu.servicebase.exception.GuliException;
import com.atguigu.vod.service.VodService;
import com.atguigu.vod.utils.InitVodClient;
import com.atguigu.vod.utils.InitVodProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduvod/video")
//@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService;

    @PostMapping("/uploadVideo")
    public R uploadVideo(MultipartFile file) {
        String videoId = vodService.uploadVideo(file);
        return R.ok().data("videoId", videoId);
    }

    @DeleteMapping("/deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id) {
        DefaultAcsClient client = InitVodClient.initVodClient(InitVodProperties.KEYID, InitVodProperties.KEYSECRET);
        try {
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            client.getAcsResponse(request);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20001, "删除失败");
        }
    }

    @GetMapping("/getVideoPlayAuth/{videoId}")
    public R getVideoPlayAuth(@PathVariable String videoId) {
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(InitVodProperties.KEYID, InitVodProperties.KEYSECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(videoId);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            System.out.println("视频凭证："+response.getPlayAuth());
            return R.ok().message("获取凭证成功").data("playAuth", response.getPlayAuth());
        } catch (Exception e) {
            new GuliException(20001, "获取凭证失败");
        }
        return R.error();
    }
}
