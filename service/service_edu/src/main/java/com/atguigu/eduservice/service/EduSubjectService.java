package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.subject.FirstSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author ljn
 * @since 2021-11-30
 */
public interface EduSubjectService extends IService<EduSubject> {

    void insertSubject(MultipartFile file, EduSubjectService subjectService);

    List<FirstSubject> getAllSubject();
}
