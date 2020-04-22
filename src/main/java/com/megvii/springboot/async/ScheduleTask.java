package com.megvii.springboot.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
//@EnableScheduling
public class ScheduleTask {

//    @Scheduled(cron = "0/2 * * * * ?")
    public void task() throws InterruptedException {
        log.info("执行定时任务1");
        Thread.sleep(60000);
    }

//    @Scheduled(cron = "0/2 * * * * ?")
    public void task2() throws InterruptedException {
        log.info("执行定时任务2");
        Thread.sleep(5000);
    }

}
