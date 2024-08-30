package com.sourceLearn.a01.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Description: User registered
 * @Date: 2024/8/5 15:31
 */
public class UserRegisteredEvent extends ApplicationEvent {

    // source 事件源
    public UserRegisteredEvent(Object source) {
        super(source);
    }
}
