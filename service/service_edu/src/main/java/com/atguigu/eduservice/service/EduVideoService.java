package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author ljn
 * @since 2021-12-01
 */
public interface EduVideoService extends IService<EduVideo> {

    void deleteVideo(String id);
}
