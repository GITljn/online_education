package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.stereotype.Component;

@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public R deleteVideo(String id) {
        return R.error().message("删除视频失败, 进入熔断器...");
    }
}
