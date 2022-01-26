package com.atguigu.eduservice;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    private EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
//        System.out.println(headMap);
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new GuliException(20001, "数据为空");
        }

        // 添加一级分类
        EduSubject firstSubject = existSubject(subjectData.getFirstSubjectName(), "0");
        if (firstSubject == null) {
            firstSubject = new EduSubject();
            firstSubject.setTitle(subjectData.getFirstSubjectName());
            firstSubject.setParentId("0");
            subjectService.save(firstSubject);
        }

        // 添加二级分类
        String secondSubjectName = subjectData.getSecondSubjectName();
        String parentId = firstSubject.getId();
        EduSubject secondSubject = existSubject(secondSubjectName, parentId);
        if (secondSubject == null) {
            secondSubject = new EduSubject();
            secondSubject.setTitle(secondSubjectName);
            secondSubject.setParentId(parentId);
            subjectService.save(secondSubject);
        }
    }

    // 判断数据库中是否已有该分类
    private EduSubject existSubject(String subjectName, String parentId) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", subjectName);
        queryWrapper.eq("parent_id", parentId);
        EduSubject eduSubject = subjectService.getOne(queryWrapper);
        return eduSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
