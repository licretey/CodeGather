package com.orvel;

import com.orvel.parser.ParseUtils;
import com.orvel.repository.ReadResult;
import com.orvel.viewer.Selecter;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        // 创建GUI窗口
        JFrame frame = new JFrame("PDF's Directory Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ReadResult readResult = new Selecter().selectDir();
        if(readResult.getStatus()){
            ParseUtils.readFile(readResult.getDir());
        }

        frame.dispose();
    }

}