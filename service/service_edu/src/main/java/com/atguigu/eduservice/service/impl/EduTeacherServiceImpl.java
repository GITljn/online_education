package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author ljn
 * @since 2021-11-25
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public Map<String, Object> pageList(long current, long size) {
        Page<EduTeacher> page = new Page<>(current, size);
        baseMapper.selectPage(page, null);
        List<EduTeacher> records = page.getRecords();
        long current1 = page.getCurrent();
        long size1 = page.getSize();
        long total = page.getTotal();
        long pages = page.getPages();
        boolean hasPrevious = page.hasPrevious();
        boolean hasNext = page.hasNext();
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current1);
        map.put("pages", pages);
        map.put("size", size1);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }
}
