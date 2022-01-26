package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.chapter.VideoVo;
import com.atguigu.eduservice.entity.vo.ChapterVideoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author ljn
 * @since 2021-12-01
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideo(String courseId) {
        List<ChapterVo> chapterVoList = new ArrayList<>();
        List<ChapterVideoVo> chapterVideoVoList = baseMapper.getChapterVideo(courseId);
        Map<String, ChapterVo> chapterVoMap = new HashMap<>();
        // 创建VideoVo对象
        for (ChapterVideoVo chapterVideoVo : chapterVideoVoList) {
            VideoVo videoVo = new VideoVo();
            videoVo.setId(chapterVideoVo.getVideoId());
            videoVo.setTitle(chapterVideoVo.getVideoTitle());
            videoVo.setSort(chapterVideoVo.getVideoSort());
            videoVo.setVideoSourceId(chapterVideoVo.getVideoSourceId());
            String chapterId = chapterVideoVo.getChapterId();
            ChapterVo chapterVo = chapterVoMap.get(chapterId);
            if (chapterVo == null) {
                chapterVo = new ChapterVo();
            }
            chapterVo.setId(chapterVideoVo.getChapterId());
            chapterVo.setTitle(chapterVideoVo.getChapterTitle());
            chapterVo.setSort(chapterVideoVo.getChapterSort());
            chapterVo.getChildren().add(videoVo);
            chapterVoMap.put(chapterId, chapterVo);
        }

        for (String s : chapterVoMap.keySet()) {
            Collections.sort(chapterVoMap.get(s).getChildren(), new Comparator<VideoVo>() {
                @Override
                public int compare(VideoVo o1, VideoVo o2) {
                    return o1.getSort() - o2.getSort();
                }
            });
            chapterVoList.add(chapterVoMap.get(s));
        }

        Collections.sort(chapterVoList, new Comparator<ChapterVo>() {
            @Override
            public int compare(ChapterVo o1, ChapterVo o2) {
                return o1.getSort() - o2.getSort();
            }
        });

        return chapterVoList;
    }

    @Override
    public void deleteChapter(String id) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", id);
        if (videoService.count(queryWrapper) > 0) {
            throw new GuliException(20001, "请先删除所有小结");
        } else {
            baseMapper.deleteById(id);
        }
    }
}
