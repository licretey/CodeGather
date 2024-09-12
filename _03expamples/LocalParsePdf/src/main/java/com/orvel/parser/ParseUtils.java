package com.orvel.parser;

import com.google.common.collect.Lists;
import com.orvel.utils.FileUtils;
import com.orvel.utils.LogUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class ParseUtils {

    public static void readFile(String directoryPath) {
        // 使用Guava库读取指定目录下的文件
        List<File> files = getFilesInDirectory(directoryPath);
        List<File> pdfFiles = files.stream().filter(file ->
                    file.isFile() && file.getName().toLowerCase().endsWith(".pdf"))
                .collect(Collectors.toList());
        for (File file : pdfFiles) {
            LogUtils.info("File Name: " + file.getName());
            // 读取文件内容并进行处理
            String content = FileUtils.pdfStreamToText(file);
            LogUtils.info(content);
        }
        LogUtils.info("读取文件完成，正在解析中...("+pdfFiles.size()+")份");
    }



    private static List<File> getFilesInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Specified path is not a directory.");
        }
        return Lists.newArrayList(directory.listFiles());
    }


    /**
     * 解析开票公司、付款公司
     * @param readResult
     * @param template
     * @param fileText :  兼容按文件名匹配，内容文本中添加了文件名
     */
//    private void set2Company(TruckInvoiceReadResult readResult, TruckInvoiceReadTemplate template, String fileText) {
//        if(template.getInvoicingKeywords() != null && fileText.contains(template.getInvoicingKeywords())){
//            readResult.setInvoicingId(template.getInvoicingId());
//            readResult.setInvoiceCompany(template.getInvoicingCompanyName());
//            readResult.setInvoiceCocType(template.getInvoicingCocType());
//        }
//        if(template.getBilltoKeywords1() != null && fileText.contains(template.getBilltoKeywords1())){
//            readResult.setBilltoCompanyId1(template.getBilltoId1());
//            readResult.setBilltoCompanyName1(template.getBilltoCompanyName1());
//            if(template.getBilltoVat1()!=null) readResult.setVatRate(template.getBilltoVat1().toString());
//        }
//        if(template.getBilltoKeywords2() != null && fileText.contains(template.getBilltoKeywords2())){
//            readResult.setBilltoCompanyId1(template.getBilltoId2());
//            readResult.setBilltoCompanyName1(template.getBilltoCompanyName2());
//            if(template.getBilltoVat2()!=null) readResult.setVatRate(template.getBilltoVat2().toString());
//        }
//        if(template.getBilltoKeywords3() != null && fileText.contains(template.getBilltoKeywords3())){
//            readResult.setBilltoCompanyId1(template.getBilltoId3());
//            readResult.setBilltoCompanyName1(template.getBilltoCompanyName3());
//            if(template.getBilltoVat3()!=null) readResult.setVatRate(template.getBilltoVat3().toString());
//        }
//        if(StringUtils.isBlank(readResult.getBilltoCompanyName1())){
//            InvoiceParseUtils.parseError(readResult,"未匹配到付款方");
//        }
//    }

    /**
     * 通用解析文本
     *
     * @param readResult
     * @param template
     */
//    private void parseResultKeys(Context ctx,TruckInvoiceReadResult readResult, TruckInvoiceReadTemplate template) {
//        // 解析关键字
//        StringBuilder errorStr = new StringBuilder();
//        String fileText = readResult.getFileText();
//        List<String> textRows = Arrays.asList(fileText.split("\\n"));
//
//        // 记录匹配的模板
//        readResult.setTruckInvoiceReadTemplateId(template.getId());
//        // 开票公司，付款公司
//        this.set2Company(readResult, template, fileText+"file_name:"+readResult.getFileName().replaceAll("\\u00A0", " "));
//        // 币种
//        readResult.setCurrency(template.getCurrency());
//        // 账期
//        if (StringUtils.isNotBlank(template.getPaymentDays())) {
//            readResult.setPaymentDays(Integer.parseInt(template.getPaymentDays()));
//        }
//
//        Map<String, List<String>> keywordsGroup = this.groupByKeys(template, textRows);
//        // 根据不同编码解析模板上关键字
//        InvoiceParseContext parseContext = new InvoiceParseContext(template, textRows, keywordsGroup, errorStr, ctx);
//
//        // 发票号
//        parseContext.parse(readResult, InvoiceParseEnum.INVOICE_NO);
//        // 开票日期
//        parseContext.parse(readResult, InvoiceParseEnum.INVOICE_DATE);
//        // 到期日
//        parseContext.parse(readResult, InvoiceParseEnum.DUE_DATE);
//        // 开票金额
//        parseContext.parse(readResult, InvoiceParseEnum.AMOUNT);
//        // IBAN
//        parseContext.parse(readResult, InvoiceParseEnum.IBAN);
//        // BAC
//        parseContext.parse(readResult, InvoiceParseEnum.BAC);
//        // 税率
//        // parseContext.parse(readResult, InvoiceParseEnum.VAT);
//        // 税额
//        InvoiceParseEnum.calculateTaxAmount(readResult);
//
//        // 记录异常
//        if(StringUtils.isBlank(readResult.getErrorDescription())
//                && StringUtils.isBlank(errorStr.toString())) readResult.setStatus(InvoiceParseStatus.PARSE_SUCCESS);
//        if (StringUtils.isNotBlank(errorStr.toString())) {
//            InvoiceParseUtils.parseError(readResult, errorStr.toString());
//            errorStr = new StringBuilder();
//        }
//    }
}
