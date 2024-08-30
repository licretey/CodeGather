package com.sourceLearn.a05.component;

import org.springframework.stereotype.Component;

@Component
public class Bean3 {
    public Bean3(){
        System.out.println("我被Spring管理啦！");
    }
}