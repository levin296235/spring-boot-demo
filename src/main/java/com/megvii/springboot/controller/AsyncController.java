package com.megvii.springboot.controller;

import com.megvii.springboot.async.AsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
@Slf4j
@RestController
public class AsyncController {

    @Autowired
    private AsyncTask asyncTask;

    @RequestMapping(value = "/async",method = RequestMethod.GET)
    public String task() throws InterruptedException, ExecutionException {
        Long time = System.currentTimeMillis();
        Future<String> task1 = asyncTask.doTask1();
        Future<String> task2 = asyncTask.doTask2();

        while(true) {
            if(task1.isDone() && task2.isDone()) {
                log.info("Task1 result: {}", task1.get());
                log.info("Task2 result: {}", task2.get());
                break;
            }
            Thread.sleep(1000);
        }
        log.info("耗时:{} ms",System.currentTimeMillis()-time);
        return "success-async11";
    }
}
