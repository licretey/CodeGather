package com.orvel.viewer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.io.Files;
import com.orvel.constant.Config;
import com.orvel.repository.ReadResult;
import com.orvel.repository.StorageData;
import com.orvel.utils.FileUtils;
import com.orvel.utils.LogUtils;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Selecter {

    private StorageData storageData;
    private ObjectMapper objectMapper = new ObjectMapper();

    public Selecter() {
        this.storageData = this.readStorageData();
    }

    public StorageData getStorageData() {
        return storageData;
    }

    public void setStorageData(StorageData storageData) {
        this.storageData = storageData;
    }


    // 手动选择路径前先读取历史记录位置
    public ReadResult selectDir() {
        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
        if (this.storageData != null && storageData.getLastSelectDir() != null) {
            homeDirectory = new File(storageData.getLastSelectDir());
        }
        // 创建文件选择器
        JFileChooser fileChooser = new JFileChooser(homeDirectory);
        fileChooser.setPreferredSize(new Dimension(1200, 800));
        fileChooser.setDialogTitle("Please Select Directory");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // 显示文件选择器弹窗
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            // 获取用户选择的目录路径
            File selectedDirectory = fileChooser.getSelectedFile();
            String directoryPath = selectedDirectory.getAbsolutePath();
            // 记录历史选择
            this.storageData.setLastSelectDir(directoryPath);
            LogUtils.info("Selected Directory: " + directoryPath);
            this.writeToStorage();
            return new ReadResult(true, directoryPath);
        }
        return new ReadResult(false, "No directory selected.");
    }


    /**
     * 读取本地存储的JSON文件
     *
     * @return
     */
    private StorageData readStorageData() {
        // 根据main程序位置确定配置文件位置
        FileUtils.checkAndCreateFolder(Config.CONFIG_DIR);
        File file = new File(Config.STORAGE_FILE);
        if(!file.exists()) return new StorageData();
        try {
            List<String> lines = Files.readLines(file, Charsets.UTF_8);
            String jsonContent = Joiner.on("").join(lines);
            return objectMapper.readValue(jsonContent, StorageData.class);
        } catch (IOException e) {
            LogUtils.error("读取运行时配置信息异常{}", e);
            return new StorageData();
        }
    }


    /**
     * 存储运行时信息到程序执行目录中的configs文件下
     */
    private void writeToStorage() {
        if (this.storageData == null) return;

        try {
            // 检查目标文件是否存在，如果存在则删除旧文件
            File file = new File(Config.STORAGE_FILE);
            if (file.exists()) {
                file.delete();
            }

            // 将JSON字符串写入文件
            objectMapper.writeValue(new File(Config.STORAGE_FILE), this.storageData);
            LogUtils.info("对象已成功序列化到文件。");
        } catch (IOException e) {
            LogUtils.error("记录运行时信息异常{}", e);
        }

    }

}
