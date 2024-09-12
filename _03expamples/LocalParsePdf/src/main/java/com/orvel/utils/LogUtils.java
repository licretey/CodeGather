package com.orvel.utils;


import com.orvel.constant.Config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtils {
    public static void debug(String msg, Object... args){
        print(msg, Config.DEBUG, args);
    }

    public static void info(String msg, Object... args){
        print(msg, Config.INFO, args);
    }

    public static void warn(String msg, Object... args){
        print(msg, Config.WARN, args);
    }

    public static void error(String msg, Object... args){
        print(msg, Config.ERROR, args);
    }


    private static void print(String msg, String level, Object... args){
        if(args != null && args.length > 0){
            msg = String.format(msg.replace("{}", "%s"), args);
        }
        switch (level) {
            case Config.DEBUG:
                msg = "[DEBUG] " + msg;
                break;
            case Config.INFO:
                msg = "[INFO] " + msg;
                break;
            case Config.WARN:
                msg = "[WARN] " + msg;
        }

        String threadName = Thread.currentThread().getName();
        write2log(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                +" Thread:{"+threadName+"}"+
                " "+msg+"\n");
    }

    private static void write2log(String msg){
        // 创建文件夹
        FileUtils.checkAndCreateFolder(Config.LOG_DIR);
        File file = FileUtils.checkAndCreateFile(Config.LOG_DIR + File.separator + "parse_info.log");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(msg);
            writer.close();
        } catch (IOException e) {
            System.out.println("error !!!\n"+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
