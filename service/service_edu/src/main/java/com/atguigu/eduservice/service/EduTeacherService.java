package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author ljn
 * @since 2021-11-25
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> pageList(long current, long size);
}
