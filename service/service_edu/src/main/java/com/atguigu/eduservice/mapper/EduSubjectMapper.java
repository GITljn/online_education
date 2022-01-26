package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.vo.SubjectQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author ljn
 * @since 2021-11-30
 */
public interface EduSubjectMapper extends BaseMapper<EduSubject> {
    List<SubjectQuery> getAllSubject();
}
