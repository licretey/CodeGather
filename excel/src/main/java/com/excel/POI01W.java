package com.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

/**
 * 写入操作
 *      03版本
 *      07版本
 * 步骤:
 *      创建工作簿workbook
 *      创建工作表sheet
 *      创建行row
 *      创建列(单元格)cell
 *      写入数据
 */
public class POI01W {

    public static void main(String[] args) {
        try {
            new POI01W().writeExcel03();
            new POI01W().writeExcel07();
            // 批量写
            new POI01W().writeExcel03Batch();
            new POI01W().writeExcel07Batch();
            // 大数据写
            new POI01W().bigWriteExcel07Batch();
        } catch (Exception e) {
            System.out.println("写03版excel异常");

        }
    }


    /**
     * 03版本写入
     */
    public void writeExcel03() throws Exception{
        // HSSFWorkbook主要用来处理excel
        Workbook workbook = new HSSFWorkbook();
        // 创建指定名称的sheet
        Sheet sheet = workbook.createSheet("03版本01");
        // 创建行(第一行)
        Row row = sheet.createRow(0);
        // 创建单元格
        Cell cell = row.createCell(0);
        // 写数据
        cell.setCellValue("商品ID");
        Cell cell2 = row.createCell(1);
        cell2.setCellValue("商品名称");

        Row row2 = sheet.createRow(1);
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue(111);
        Cell cell22 = row2.createCell(1);
        cell22.setCellValue("商品名称");


        // 生成一张表(IO流)
        // ./CoddeGather/03版本01.xls
        FileOutputStream outFile = new FileOutputStream("./excel/03版本01.xls");
        workbook.write(outFile);
        outFile.close();
    }

    /**
     * 07版本写入
     */
    public void writeExcel07() throws Exception{
        // HSSFWorkbook主要用来处理excel
        Workbook workbook = new XSSFWorkbook();
        // 创建指定名称的sheet
        Sheet sheet = workbook.createSheet("07版本01");
        // 创建行(第一行)
        Row row = sheet.createRow(0);
        // 创建单元格
        Cell cell = row.createCell(0);
        // 写数据
        cell.setCellValue("商品ID");
        Cell cell2 = row.createCell(1);
        cell2.setCellValue("商品名称");

        Row row2 = sheet.createRow(1);
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue(111);
        Cell cell22 = row2.createCell(1);
        cell22.setCellValue("鼠标");


        // 生成一张表(IO流)
        // ./CoddeGather/03版本01.xls
        FileOutputStream outFile = new FileOutputStream("./excel/07版本01.xlsx");
        workbook.write(outFile);
        outFile.close();
    }



    /**
     * 03版本写入
     */
    public void writeExcel03Batch() throws Exception{
        long start = System.currentTimeMillis();
        // HSSFWorkbook主要用来处理excel
        Workbook workbook = new HSSFWorkbook();
        // 创建指定名称的sheet
        Sheet sheet = workbook.createSheet("03版本02");
        for (int rowNumber = 0; rowNumber < 65536; rowNumber++) {
            Row row = sheet.createRow(rowNumber);
            for (int cellNumber = 0; cellNumber < 20; cellNumber++) {
                Cell cell = row.createCell(cellNumber);
                cell.setCellValue(rowNumber + " : " + cellNumber);
            }
        }

        // 生成一张表(IO流)
        FileOutputStream outFile = new FileOutputStream("./excel/03版本02.xls");
        workbook.write(outFile);
        outFile.close();
        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000 + " (s)");

    }

    /**
     * 07版本写入
     */
    public void writeExcel07Batch() throws Exception{
        long start = System.currentTimeMillis();
        // HSSFWorkbook主要用来处理excel
        Workbook workbook = new XSSFWorkbook();
        // 创建指定名称的sheet
        Sheet sheet = workbook.createSheet("07版本02");
        for (int rowNumber = 0; rowNumber < 65536; rowNumber++) {
            Row row = sheet.createRow(rowNumber);
            for (int cellNumber = 0; cellNumber < 20; cellNumber++) {
                Cell cell = row.createCell(cellNumber);
                cell.setCellValue(rowNumber + " : " + cellNumber);
            }
        }

        // 生成一张表(IO流)
        FileOutputStream outFile = new FileOutputStream("./excel/07版本02.xlsx");
        workbook.write(outFile);
        outFile.close();
        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000 + " (s)");
    }


    /**
     * 大数据写入操作
     *      XSSF读取所有的行,速度慢也容易OOM
     *
     *      使用SXSSF解决大量数据的写入: 内存中存放的行有上限(默认100),
     *      超过上限后会被写入到磁盘的临时文件中,
     *      临时文件需要使用dispose手动清除
     */
    public void bigWriteExcel07Batch() throws Exception{
        long start = System.currentTimeMillis();
        // SXSSFWorkbook主要用来处理大数据文件
        Workbook workbook = new SXSSFWorkbook();
        // 创建指定名称的sheet
        Sheet sheet = workbook.createSheet("07版本03");
        for (int rowNumber = 0; rowNumber < 65536; rowNumber++) {
            Row row = sheet.createRow(rowNumber);
            for (int cellNumber = 0; cellNumber < 20; cellNumber++) {
                Cell cell = row.createCell(cellNumber);
                cell.setCellValue(rowNumber + " : " + cellNumber);
            }
        }

        // 生成一张表(IO流)
        FileOutputStream outFile = new FileOutputStream("./excel/07版本03.xlsx");
        workbook.write(outFile);
        outFile.close();
        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000 + " (s)");
    }

}
