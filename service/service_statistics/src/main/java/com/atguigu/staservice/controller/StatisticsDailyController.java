package com.atguigu.staservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.staservice.entity.StatisticsDaily;
import com.atguigu.staservice.entity.vo.SelectDataVo;
import com.atguigu.staservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author ljn
 * @since 2022-01-06
 */
@RestController
//@CrossOrigin
@RequestMapping("/staservice/sta")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService dailyService;

    @PostMapping("/insertOneDayData/{day}")
    public R insertOneDayData(@PathVariable String day) {
        dailyService.insertOneDayData(day);
        return R.ok();
    }

    @PostMapping("/selectDataByDate")
    public R selectDataByDate(@RequestBody SelectDataVo selectDataVo) {
        String type = selectDataVo.getType();
        String begin = selectDataVo.getBegin();
        String end = selectDataVo.getEnd();
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("date_calculated", begin, end);
        queryWrapper.select("date_calculated", type);
        queryWrapper.orderByAsc("date_calculated");
        List<StatisticsDaily> list = dailyService.list(queryWrapper);
        List<String> xData = new ArrayList<>();
        List<Integer> yData = new ArrayList<>();
        for (StatisticsDaily daily : list) {
            xData.add(daily.getDateCalculated());
            switch (type) {
                case "register_num":
                    yData.add(daily.getRegisterNum());
                    break;
                case "login_num":
                    yData.add(daily.getLoginNum());
                    break;
                case "video_view_num":
                    yData.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    yData.add(daily.getCourseNum());
                    break;
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("xData", xData);
        map.put("yData", yData);
        return R.ok().data(map);
    }
}

