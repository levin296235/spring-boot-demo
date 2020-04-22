package com.megvii.springboot.async;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
@Slf4j
@Component
public class ScheduleTaskTimer {

    private final static Map<String,Task> taskMap = new HashMap<>();

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    /**
     * 创建定时任务
     */
    public String start(String id) {
        if (StringUtils.isNotEmpty(id) &&!taskMap.containsKey(id)){
            Task task = new Task(id);
            ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(task.getRunnable(),
                    triggerContext -> new CronTrigger(task.getCron())
                            .nextExecutionTime(triggerContext)
            );
            task.setScheduledFuture(future);
            taskMap.put(task.getTaskId(), task);
        }
        return "success start......";
    }

    /**
     * 更新定时任务的cron
     */
    public String update(String cron,String id) {
        if (taskMap.containsKey(id)) {
            Task task = taskMap.get(id);
            ScheduledFuture<?> future = null;
            try {
                future = threadPoolTaskScheduler.schedule(task.getRunnable(),
                        triggerContext -> new CronTrigger(cron).nextExecutionTime(triggerContext)
                );
            } catch (Exception e) {
                return "error";
            }
            task.getScheduledFuture().cancel(true);
            task.setScheduledFuture(future);
        }
        return "success update......";
    }

    /**
     * 删除定时任务
     */
    public String stop(String id) {
        if (taskMap.containsKey(id)) {
            Task task = taskMap.get(id);
            task.getScheduledFuture().cancel(true);
            taskMap.remove(id);
        }
        return "success stop......";
    }

    @Slf4j
    @Data
    static class Task {
        private String taskId;
        private Runnable runnable;
        private ScheduledFuture scheduledFuture;
        private String cron;

        public Task(String taskId) {
            this.taskId = taskId;
            runnable = ()->{
                log.info("任务taskId:" + taskId);
            }
            ;
            cron = "0/5 * * * * ?";
        }
    }
}
