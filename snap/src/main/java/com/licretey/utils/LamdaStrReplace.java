package com.licretey.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LamdaStrReplace {
    public static void main(String[] args) {
        Set<String> unDeleted = new HashSet<>();
        ArrayList<String> unDeletedStrs = new ArrayList<>(unDeleted);
        String stauts = "";
        if(unDeletedStrs.size()>0){
            StringBuilder finalStauts = new StringBuilder();
            unDeletedStrs.forEach(str -> {
                finalStauts.append(str).append(",");
            });
            finalStauts.replace(finalStauts.length()-1, finalStauts.length(),"");
            stauts = finalStauts.toString();
        }
    }
}
