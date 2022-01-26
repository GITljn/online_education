package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.vo.ChapterVideoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author ljn
 * @since 2021-12-01
 */
public interface EduChapterMapper extends BaseMapper<EduChapter> {

    List<ChapterVideoVo> getChapterVideo(String courseId);
}
