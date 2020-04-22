package com.megvii.springboot.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class LogSaveEvent  extends ApplicationEvent {
    public LogPojo logPojo;
    public LogSaveEvent(Object source,LogPojo logPojo) {
        super(source);
        this.logPojo = logPojo;
    }
    public LogSaveEvent (Object source) {
        super(source);
    }
}

