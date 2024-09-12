package com.orvel.constant;

import java.io.File;

public interface Config {

    public static final String ERROR = "error";
    public static final String INFO = "info";
    public static final String DEBUG = "debug";
    public static final String WARN = "warn";

    // 程序运行目录
    public static final String WORKING_DIR = System.getProperty("user.dir");
    // 日志文件 自动创建
    public static final String LOG_DIR = WORKING_DIR + File.separator+ "logs";
    // 配置目录
    public static final String CONFIG_DIR = WORKING_DIR + File.separator+ "configs";
    // 模板配置文件 运行时信息+发票模板配置
    public static final String STORAGE_FILE = CONFIG_DIR+ File.separator + "storage.json";
    // 获取机器的线程数量
    public static final int THREAD_SIZE = Runtime.getRuntime().availableProcessors();
}
