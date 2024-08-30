package com.sourceLearn.a05.component;

import org.springframework.stereotype.Component;

@Component
public class Bean2 {
    public Bean2(){
        System.out.println("我被Spring管理啦！");
    }
}