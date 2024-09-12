package com.orvel.utils;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUtils {
    // 判断文件夹是否存在，不存在自动创建
    public static File checkAndCreateFolder(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            new File(folderPath).mkdirs();
        }
        return folder;
    }

    // 判断文件是否存在，不存在自动创建 filePath包含路径
    public static File checkAndCreateFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                new File(filePath).createNewFile();
            } catch (IOException e) {
                LogUtils.error("文件创建异常{}", e);
            }
        }
        return file;
    }

    //pdf内容提取
    public static String pdfStreamToText(File file){
        StringBuilder sb = new StringBuilder();
        try {
            PDDocument document = Loader.loadPDF(file);
            // Create a Splitter object
            Splitter splitter = new Splitter();
            List<PDDocument> Pages = splitter.split(document);

            PDFTextStripper stripper = new PDFTextStripper();
            for (PDDocument page : Pages) {
                //将pdf中的内容转换为文本
                String text = stripper.getText(page);
                //分订单号
                sb.append(text);
            }
            document.close();
        } catch (Exception e){
            LogUtils.error("解析文件异常{}", e);
        }
        return sb.toString();
    }
}
