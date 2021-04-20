package com.friday.report.config;



import com.friday.common.utils.TimeUtils;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j
@Component
@EnableScheduling
public class WorkingSchedule {

//    @Autowired
//    UserWorkHoursService userWorkHoursService;
//    @Scheduled(cron = "0 0 23 ? * MON-FRI")
//    public void getUnfilledPersons(){
//        log.info("开始记录");
//        String dateString = TimeUtils.getCurrentDateString("yy yyMMdd");
//        List<UserWorkHours> todayUnfilled = null;
//        try {
//            todayUnfilled = userWorkHoursService.getTodayUnfilled(dateString);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        log.info(todayUnfilled);
//        log.info("记录结束");
//    }
}
