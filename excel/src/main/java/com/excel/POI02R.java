package com.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 读操作
 *      03版本
 *      07版本
 * 步骤: 利用文件流FileInputStream读取
 *      创建工作簿workbook
 *      创建工作表sheet
 *      创建行row
 *      创建列(单元格)cell
 *      读取数据
 */
public class POI02R {

    public static void main(String[] args) {
        try {
            new POI02R().readExcel03();
            new POI02R().readExcel07();
            new POI02R().readExcelCellType();
        } catch (Exception e) {
            System.out.println("读excel异常: " + e.getMessage());

        }
    }


    /**
     * 03版本读取
     */
    public void readExcel03() throws Exception{
        FileInputStream fileIS = new FileInputStream("./excel/03版本01.xls");

        // 获取工作薄 HSSFWorkbook主要用来处理excel
        Workbook workbook = new HSSFWorkbook(fileIS);
        // 获取sheet(通过下标, sheet名)
        Sheet sheet = workbook.getSheetAt(0);
        // 获取行(通过下标)
        Row row = sheet.getRow(0);
        // 获取单元格
        Cell cell = row.getCell(0);
        // 写数据
        String v11 = cell.getStringCellValue();
        Cell cell2 = row.getCell(1);
        String v12 = cell2.getStringCellValue();

        Row row2 = sheet.getRow(1);
        Cell cell21 = row2.getCell(0);
        double dv = cell21.getNumericCellValue();
        String v21 = dv+"";
        Cell cell22 = row2.getCell(1);
        String v22 = cell22.getStringCellValue();
        fileIS.close();
        System.out.println(v11 + " | " + v12 );
        System.out.println(v21 + " | " + v22 );
    }

    /**
     * 07版本读取
     */
    public void readExcel07() throws Exception{
        FileInputStream fileIS = new FileInputStream("./excel/07版本01.xlsx");

        // 获取工作薄 HSSFWorkbook主要用来处理excel
        Workbook workbook = new XSSFWorkbook(fileIS);
        // 获取sheet(通过下标, sheet名)
        Sheet sheet = workbook.getSheetAt(0);
        // 获取行(通过下标)
        Row row = sheet.getRow(0);
        // 获取单元格
        Cell cell = row.getCell(0);
        // 写数据
        String v11 = cell.getStringCellValue();
        Cell cell2 = row.getCell(1);
        String v12 = cell2.getStringCellValue();

        Row row2 = sheet.getRow(1);
        Cell cell21 = row2.getCell(0);
        double dv = cell21.getNumericCellValue();
        String v21 = dv+"";
        Cell cell22 = row2.getCell(1);
        String v22 = cell22.getStringCellValue();
        fileIS.close();
        System.out.println(v11 + " | " + v12 );
        System.out.println(v21 + " | " + v22 );
    }

    public void readExcelCellType() throws Exception{
        FileInputStream fileIS = new FileInputStream("./excel/temp.xlsx");
        Workbook workbook = new XSSFWorkbook(fileIS);
        Sheet sheet = workbook.getSheetAt(0);
        Row titleRow = sheet.getRow(0);
        if(titleRow!=null){
            // 获取单元格数量
            int cellSize = titleRow.getPhysicalNumberOfCells();
            for (int cellNumber = 0; cellNumber < cellSize; cellNumber++) {
                Cell cell = titleRow.getCell(cellNumber);
                if(cell!=null){
                    // 获取所有数据
                    String value = cell.getStringCellValue();
                    System.out.println(value);
                }
            }
        }

        // 获取标题以下的所有内容
        int rowSize = sheet.getPhysicalNumberOfRows();// 获取行数
        // 排除第一行title开始读
        for (int rowNumber = 1; rowNumber < rowSize; rowNumber++) {
            Row row = sheet.getRow(rowNumber);
            if(row!=null){
                // 获取每一行的单元格数量
                int cellSize = row.getPhysicalNumberOfCells();
                for (int cellNumber = 0; cellNumber < cellSize; cellNumber++) {
                    Cell cell = row.getCell(cellNumber);
                    if(cell!=null){
                        // 获取数据类型
                        CellType cellType = cell.getCellType();
                        String cellVal = "";
                        // 根据不同的类型读取数据
                        switch (cellType){
                            case STRING:
                                cellVal = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                // 判断是否为日期类型
                                if(DateUtil.isCellDateFormatted(cell)){
                                    Date date = cell.getDateCellValue();
                                    cellVal = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                }else {
                                    cellVal = cell.toString();
                                }
                                break;
                            case BLANK:
                                // 空白字符不处理
                                break;
                            case BOOLEAN:
                                cellVal = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case ERROR:
                                cellVal = "格式解析错误";
                                break;
                        }
                        System.out.println(cellVal);
                    }
                }

            }
        }
        fileIS.close();
    }

}
