package com.megvii.springboot.controller;

import com.megvii.springboot.async.ScheduleTaskTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleTaskTimer scheduleTaskTimer;

    @RequestMapping("/startTask/{id}")
    public String startTask(@PathVariable("id") String id){
        scheduleTaskTimer.start(id);
        return "startTask....";
    }

    @RequestMapping("/updateCron/{id}")
    public String updateCron(String cron, @PathVariable("id") String id){
        scheduleTaskTimer.start(id);
        return "updateCron....";
    }

    @RequestMapping("/stopTask/{id}")
    public String stopTask(@PathVariable("id") String id) {
        scheduleTaskTimer.stop(id);
        return "stopTask....";
    }
}
