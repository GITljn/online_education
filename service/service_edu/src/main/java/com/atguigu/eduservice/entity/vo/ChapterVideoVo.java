package com.atguigu.eduservice.entity.vo;

import lombok.Data;

@Data
public class ChapterVideoVo {
    private String chapterId;
    private String chapterTitle;
    private String videoId;
    private String videoTitle;
    private String videoSourceId;
    private Integer chapterSort;
    private Integer videoSort;
}
