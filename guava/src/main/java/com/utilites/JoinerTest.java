package com.utilites;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class JoinerTest {

    private final List<String> strList = Arrays.asList(
            "google","guava","java","scala","kafka"
    );

    private final List<String> strList2 = Arrays.asList(
            "google","guava","java","scala",null
    );


    @Test
    public void testJoinOnJoin(){
        String join = Joiner.on("#").join(strList);
        System.out.println(join);
    }
}
