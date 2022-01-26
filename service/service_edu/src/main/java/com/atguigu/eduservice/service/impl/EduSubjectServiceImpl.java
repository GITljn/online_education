package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.SubjectExcelListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.FirstSubject;
import com.atguigu.eduservice.entity.subject.SecondSubject;
import com.atguigu.eduservice.entity.vo.SubjectQuery;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author ljn
 * @since 2021-11-30
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void insertSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            EasyExcel.read(file.getInputStream(), SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<FirstSubject> getAllSubject() {
        List<SubjectQuery> subjectQueryList = baseMapper.getAllSubject();
        Map<String, FirstSubject> resultMap = new HashMap<>();
        for (SubjectQuery subjectQuery : subjectQueryList) {
            FirstSubject firstSubject = new FirstSubject();
            firstSubject.setId(subjectQuery.getFirstId());
            firstSubject.setTitle(subjectQuery.getFirstTitle());
            resultMap.put(subjectQuery.getFirstId(), firstSubject);
        }
        for (SubjectQuery subjectQuery : subjectQueryList) {
            SecondSubject secondSubject = new SecondSubject();
            secondSubject.setId(subjectQuery.getSecondId());
            secondSubject.setTitle(subjectQuery.getSecondTitle());
            resultMap.get(subjectQuery.getFirstId()).getChildren().add(secondSubject);
        }
        List<FirstSubject> resultList = new ArrayList<>();
        for (String s : resultMap.keySet()) {
            resultList.add(resultMap.get(s));
        }
        return resultList;
    }
}
