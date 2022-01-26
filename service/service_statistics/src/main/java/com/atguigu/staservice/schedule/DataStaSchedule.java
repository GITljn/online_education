package com.atguigu.staservice.schedule;

import com.atguigu.staservice.service.StatisticsDailyService;
import com.atguigu.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataStaSchedule {
    @Autowired
    private StatisticsDailyService dailyService;
    @Scheduled(cron = "0 0 1 * * ? ")
    public void dataSta() {
        dailyService.insertOneDayData(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
