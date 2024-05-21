package org.licretey;

import org.apache.commons.lang3.StringUtils;

public class Test02 {
    public static void main(String[] args) {
        String a = "yang-jian-ping-aa";
        String[] split = StringUtils.split(a, "-");
        System.out.println(split.length);
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }

        // String s = StringUtils.join(split[0,split.length-1],"-");
        // System.out.println(s);
        System.out.println(
                StringUtils.lastIndexOf(a, "-"));
        System.out.println(a.substring(0,StringUtils.lastIndexOf(a,"-")));
    }
}
