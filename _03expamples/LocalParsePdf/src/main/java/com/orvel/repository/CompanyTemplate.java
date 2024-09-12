package com.orvel.repository;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class CompanyTemplate {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("template_name")
    private String templateName;
    @JsonProperty("remark")
    private String remark;
    @JsonProperty("invoicing_id")
    private Long invoicingIDr;
    @JsonProperty("invoicing_keywords")
    private String invoicingKeywords;
    @JsonProperty("billto_id1")
    private Long billtoId1;
    @JsonProperty("billto_keywords1")
    private String billtoKeywords1;
    @JsonProperty("billto_id2")
    private Long billtoId2;
    @JsonProperty("billto_keywords2")
    private String billtoKeywords2;
    @JsonProperty("billto_id3")
    private Object billtoId3;
    @JsonProperty("billto_keywords3")
    private Object billtoKeywords3;
    @JsonProperty("version")
    private Long version;
    @JsonProperty("insert_user_id")
    private Long insertUserID;
    @JsonProperty("update_user_id")
    private Long updateUserID;
    @JsonProperty("insert_timestamp")
    private LocalDateTime insertTimestamp;
    @JsonProperty("update_timestamp")
    private LocalDateTime updateTimestamp;
    @JsonProperty("due_date_keywords")
    private String dueDateKeywords;
    @JsonProperty("invoice_date_replace_char")
    private Object invoiceDateReplaceChar;
    @JsonProperty("amount_keywords")
    private String amountKeywords;
    @JsonProperty("invoice_no_end")
    private String invoiceNoEnd;
    @JsonProperty("invoice_date_format")
    private String invoiceDateFormat;
    @JsonProperty("invoice_date_offset_lines")
    private Object invoiceDateOffsetLines;
    @JsonProperty("amount_replace_char")
    private String amountReplaceChar;
    @JsonProperty("amount_begin")
    private String amountBegin;
    @JsonProperty("due_date_replace_char")
    private Object dueDateReplaceChar;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("due_date_end")
    private String dueDateEnd;
    @JsonProperty("invoice_date_begin")
    private String invoiceDateBegin;
    @JsonProperty("invoice_no_replace_char")
    private Object invoiceNoReplaceChar;
    @JsonProperty("due_date_format")
    private String dueDateFormat;
    @JsonProperty("amount_end")
    private String amountEnd;
    @JsonProperty("invoice_no_begin")
    private String invoiceNoBegin;
    @JsonProperty("due_date_begin")
    private String dueDateBegin;
    @JsonProperty("payment_days")
    private String paymentDays;
    @JsonProperty("amount_offset_lines")
    private String amountOffsetLines;
    @JsonProperty("invoice_date_end")
    private String invoiceDateEnd;
    @JsonProperty("invoice_no_offset_lines")
    private Object invoiceNoOffsetLines;
    @JsonProperty("due_date_offset_lines")
    private Object dueDateOffsetLines;
    @JsonProperty("invoice_no_keywords")
    private String invoiceNoKeywords;
    @JsonProperty("invoice_date_keywords")
    private String invoiceDateKeywords;
    @JsonProperty("invoicing_coc_type")
    private String invoicingCocType;
    @JsonProperty("invoicing_company_name")
    private String invoicingCompanyName;
    @JsonProperty("billto_company_name1")
    private String billtoCompanyName1;
    @JsonProperty("billto_company_name2")
    private String billtoCompanyName2;
    @JsonProperty("billto_company_name3")
    private Object billtoCompanyName3;
    @JsonProperty("shipment_end")
    private Object shipmentEnd;
    @JsonProperty("detail_amount_keywords")
    private Object detailAmountKeywords;
    @JsonProperty("shipment_begin")
    private Object shipmentBegin;
    @JsonProperty("detail_amount_replace_char")
    private String detailAmountReplaceChar;
    @JsonProperty("departure_date_replace_char")
    private Object departureDateReplaceChar;
    @JsonProperty("detail_amount_end")
    private String detailAmountEnd;
    @JsonProperty("detail_amount_begin")
    private String detailAmountBegin;
    @JsonProperty("container_replace_char")
    private Object containerReplaceChar;
    @JsonProperty("match_detail_method")
    private String matchDetailMethod;
    @JsonProperty("shipment_keywords")
    private Object shipmentKeywords;
    @JsonProperty("detail_amount_offset_lines")
    private String detailAmountOffsetLines;
    @JsonProperty("container_offset_lines")
    private Object containerOffsetLines;
    @JsonProperty("shipment_offset_lines")
    private Object shipmentOffsetLines;
    @JsonProperty("departure_date_offset_lines")
    private String departureDateOffsetLines;
    @JsonProperty("departure_date_begin")
    private String departureDateBegin;
    @JsonProperty("container_end")
    private Object containerEnd;
    @JsonProperty("container_keywords")
    private Object containerKeywords;
    @JsonProperty("shipment_replace_char")
    private Object shipmentReplaceChar;
    @JsonProperty("container_begin")
    private String containerBegin;
    @JsonProperty("departure_date_end")
    private String departureDateEnd;
    @JsonProperty("departure_date_keywords")
    private String departureDateKeywords;
    @JsonProperty("departure_date_format")
    private String departureDateFormat;
    @JsonProperty("billto_vat1")
    private Long billtoVat1;
    @JsonProperty("billto_vat2")
    private double billtoVat2;
    @JsonProperty("billto_vat3")
    private Long billtoVat3;
    @JsonProperty("auto_remove")
    private String autoRemove;
    @JsonProperty("bac_replace_char")
    private Object bacReplaceChar;
    @JsonProperty("bac_begin")
    private String bacBegin;
    @JsonProperty("iban_end")
    private String ibanEnd;
    @JsonProperty("iban_replace_char")
    private String ibanReplaceChar;
    @JsonProperty("bac_keywords")
    private String bacKeywords;
    @JsonProperty("bac_offset_lines")
    private Object bacOffsetLines;
    @JsonProperty("iban_keywords")
    private String ibanKeywords;
    @JsonProperty("iban_offset_lines")
    private Object ibanOffsetLines;
    @JsonProperty("bac_end")
    private String bacEnd;
    @JsonProperty("iban_begin")
    private String ibanBegin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getInvoicingIDr() {
        return invoicingIDr;
    }

    public void setInvoicingIDr(Long invoicingIDr) {
        this.invoicingIDr = invoicingIDr;
    }

    public String getInvoicingKeywords() {
        return invoicingKeywords;
    }

    public void setInvoicingKeywords(String invoicingKeywords) {
        this.invoicingKeywords = invoicingKeywords;
    }

    public Long getBilltoId1() {
        return billtoId1;
    }

    public void setBilltoId1(Long billtoId1) {
        this.billtoId1 = billtoId1;
    }

    public String getBilltoKeywords1() {
        return billtoKeywords1;
    }

    public void setBilltoKeywords1(String billtoKeywords1) {
        this.billtoKeywords1 = billtoKeywords1;
    }

    public Long getBilltoId2() {
        return billtoId2;
    }

    public void setBilltoId2(Long billtoId2) {
        this.billtoId2 = billtoId2;
    }

    public String getBilltoKeywords2() {
        return billtoKeywords2;
    }

    public void setBilltoKeywords2(String billtoKeywords2) {
        this.billtoKeywords2 = billtoKeywords2;
    }

    public Object getBilltoId3() {
        return billtoId3;
    }

    public void setBilltoId3(Object billtoId3) {
        this.billtoId3 = billtoId3;
    }

    public Object getBilltoKeywords3() {
        return billtoKeywords3;
    }

    public void setBilltoKeywords3(Object billtoKeywords3) {
        this.billtoKeywords3 = billtoKeywords3;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getInsertUserID() {
        return insertUserID;
    }

    public void setInsertUserID(Long insertUserID) {
        this.insertUserID = insertUserID;
    }

    public Long getUpdateUserID() {
        return updateUserID;
    }

    public void setUpdateUserID(Long updateUserID) {
        this.updateUserID = updateUserID;
    }

    public LocalDateTime getInsertTimestamp() {
        return insertTimestamp;
    }

    public void setInsertTimestamp(LocalDateTime insertTimestamp) {
        this.insertTimestamp = insertTimestamp;
    }

    public LocalDateTime getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(LocalDateTime updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public String getDueDateKeywords() {
        return dueDateKeywords;
    }

    public void setDueDateKeywords(String dueDateKeywords) {
        this.dueDateKeywords = dueDateKeywords;
    }

    public Object getInvoiceDateReplaceChar() {
        return invoiceDateReplaceChar;
    }

    public void setInvoiceDateReplaceChar(Object invoiceDateReplaceChar) {
        this.invoiceDateReplaceChar = invoiceDateReplaceChar;
    }

    public String getAmountKeywords() {
        return amountKeywords;
    }

    public void setAmountKeywords(String amountKeywords) {
        this.amountKeywords = amountKeywords;
    }

    public String getInvoiceNoEnd() {
        return invoiceNoEnd;
    }

    public void setInvoiceNoEnd(String invoiceNoEnd) {
        this.invoiceNoEnd = invoiceNoEnd;
    }

    public String getInvoiceDateFormat() {
        return invoiceDateFormat;
    }

    public void setInvoiceDateFormat(String invoiceDateFormat) {
        this.invoiceDateFormat = invoiceDateFormat;
    }

    public Object getInvoiceDateOffsetLines() {
        return invoiceDateOffsetLines;
    }

    public void setInvoiceDateOffsetLines(Object invoiceDateOffsetLines) {
        this.invoiceDateOffsetLines = invoiceDateOffsetLines;
    }

    public String getAmountReplaceChar() {
        return amountReplaceChar;
    }

    public void setAmountReplaceChar(String amountReplaceChar) {
        this.amountReplaceChar = amountReplaceChar;
    }

    public String getAmountBegin() {
        return amountBegin;
    }

    public void setAmountBegin(String amountBegin) {
        this.amountBegin = amountBegin;
    }

    public Object getDueDateReplaceChar() {
        return dueDateReplaceChar;
    }

    public void setDueDateReplaceChar(Object dueDateReplaceChar) {
        this.dueDateReplaceChar = dueDateReplaceChar;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDueDateEnd() {
        return dueDateEnd;
    }

    public void setDueDateEnd(String dueDateEnd) {
        this.dueDateEnd = dueDateEnd;
    }

    public String getInvoiceDateBegin() {
        return invoiceDateBegin;
    }

    public void setInvoiceDateBegin(String invoiceDateBegin) {
        this.invoiceDateBegin = invoiceDateBegin;
    }

    public Object getInvoiceNoReplaceChar() {
        return invoiceNoReplaceChar;
    }

    public void setInvoiceNoReplaceChar(Object invoiceNoReplaceChar) {
        this.invoiceNoReplaceChar = invoiceNoReplaceChar;
    }

    public String getDueDateFormat() {
        return dueDateFormat;
    }

    public void setDueDateFormat(String dueDateFormat) {
        this.dueDateFormat = dueDateFormat;
    }

    public String getAmountEnd() {
        return amountEnd;
    }

    public void setAmountEnd(String amountEnd) {
        this.amountEnd = amountEnd;
    }

    public String getInvoiceNoBegin() {
        return invoiceNoBegin;
    }

    public void setInvoiceNoBegin(String invoiceNoBegin) {
        this.invoiceNoBegin = invoiceNoBegin;
    }

    public String getDueDateBegin() {
        return dueDateBegin;
    }

    public void setDueDateBegin(String dueDateBegin) {
        this.dueDateBegin = dueDateBegin;
    }

    public String getPaymentDays() {
        return paymentDays;
    }

    public void setPaymentDays(String paymentDays) {
        this.paymentDays = paymentDays;
    }

    public String getAmountOffsetLines() {
        return amountOffsetLines;
    }

    public void setAmountOffsetLines(String amountOffsetLines) {
        this.amountOffsetLines = amountOffsetLines;
    }

    public String getInvoiceDateEnd() {
        return invoiceDateEnd;
    }

    public void setInvoiceDateEnd(String invoiceDateEnd) {
        this.invoiceDateEnd = invoiceDateEnd;
    }

    public Object getInvoiceNoOffsetLines() {
        return invoiceNoOffsetLines;
    }

    public void setInvoiceNoOffsetLines(Object invoiceNoOffsetLines) {
        this.invoiceNoOffsetLines = invoiceNoOffsetLines;
    }

    public Object getDueDateOffsetLines() {
        return dueDateOffsetLines;
    }

    public void setDueDateOffsetLines(Object dueDateOffsetLines) {
        this.dueDateOffsetLines = dueDateOffsetLines;
    }

    public String getInvoiceNoKeywords() {
        return invoiceNoKeywords;
    }

    public void setInvoiceNoKeywords(String invoiceNoKeywords) {
        this.invoiceNoKeywords = invoiceNoKeywords;
    }

    public String getInvoiceDateKeywords() {
        return invoiceDateKeywords;
    }

    public void setInvoiceDateKeywords(String invoiceDateKeywords) {
        this.invoiceDateKeywords = invoiceDateKeywords;
    }

    public String getInvoicingCocType() {
        return invoicingCocType;
    }

    public void setInvoicingCocType(String invoicingCocType) {
        this.invoicingCocType = invoicingCocType;
    }

    public String getInvoicingCompanyName() {
        return invoicingCompanyName;
    }

    public void setInvoicingCompanyName(String invoicingCompanyName) {
        this.invoicingCompanyName = invoicingCompanyName;
    }

    public String getBilltoCompanyName1() {
        return billtoCompanyName1;
    }

    public void setBilltoCompanyName1(String billtoCompanyName1) {
        this.billtoCompanyName1 = billtoCompanyName1;
    }

    public String getBilltoCompanyName2() {
        return billtoCompanyName2;
    }

    public void setBilltoCompanyName2(String billtoCompanyName2) {
        this.billtoCompanyName2 = billtoCompanyName2;
    }

    public Object getBilltoCompanyName3() {
        return billtoCompanyName3;
    }

    public void setBilltoCompanyName3(Object billtoCompanyName3) {
        this.billtoCompanyName3 = billtoCompanyName3;
    }

    public Object getShipmentEnd() {
        return shipmentEnd;
    }

    public void setShipmentEnd(Object shipmentEnd) {
        this.shipmentEnd = shipmentEnd;
    }

    public Object getDetailAmountKeywords() {
        return detailAmountKeywords;
    }

    public void setDetailAmountKeywords(Object detailAmountKeywords) {
        this.detailAmountKeywords = detailAmountKeywords;
    }

    public Object getShipmentBegin() {
        return shipmentBegin;
    }

    public void setShipmentBegin(Object shipmentBegin) {
        this.shipmentBegin = shipmentBegin;
    }

    public String getDetailAmountReplaceChar() {
        return detailAmountReplaceChar;
    }

    public void setDetailAmountReplaceChar(String detailAmountReplaceChar) {
        this.detailAmountReplaceChar = detailAmountReplaceChar;
    }

    public Object getDepartureDateReplaceChar() {
        return departureDateReplaceChar;
    }

    public void setDepartureDateReplaceChar(Object departureDateReplaceChar) {
        this.departureDateReplaceChar = departureDateReplaceChar;
    }

    public String getDetailAmountEnd() {
        return detailAmountEnd;
    }

    public void setDetailAmountEnd(String detailAmountEnd) {
        this.detailAmountEnd = detailAmountEnd;
    }

    public String getDetailAmountBegin() {
        return detailAmountBegin;
    }

    public void setDetailAmountBegin(String detailAmountBegin) {
        this.detailAmountBegin = detailAmountBegin;
    }

    public Object getContainerReplaceChar() {
        return containerReplaceChar;
    }

    public void setContainerReplaceChar(Object containerReplaceChar) {
        this.containerReplaceChar = containerReplaceChar;
    }

    public String getMatchDetailMethod() {
        return matchDetailMethod;
    }

    public void setMatchDetailMethod(String matchDetailMethod) {
        this.matchDetailMethod = matchDetailMethod;
    }

    public Object getShipmentKeywords() {
        return shipmentKeywords;
    }

    public void setShipmentKeywords(Object shipmentKeywords) {
        this.shipmentKeywords = shipmentKeywords;
    }

    public String getDetailAmountOffsetLines() {
        return detailAmountOffsetLines;
    }

    public void setDetailAmountOffsetLines(String detailAmountOffsetLines) {
        this.detailAmountOffsetLines = detailAmountOffsetLines;
    }

    public Object getContainerOffsetLines() {
        return containerOffsetLines;
    }

    public void setContainerOffsetLines(Object containerOffsetLines) {
        this.containerOffsetLines = containerOffsetLines;
    }

    public Object getShipmentOffsetLines() {
        return shipmentOffsetLines;
    }

    public void setShipmentOffsetLines(Object shipmentOffsetLines) {
        this.shipmentOffsetLines = shipmentOffsetLines;
    }

    public String getDepartureDateOffsetLines() {
        return departureDateOffsetLines;
    }

    public void setDepartureDateOffsetLines(String departureDateOffsetLines) {
        this.departureDateOffsetLines = departureDateOffsetLines;
    }

    public String getDepartureDateBegin() {
        return departureDateBegin;
    }

    public void setDepartureDateBegin(String departureDateBegin) {
        this.departureDateBegin = departureDateBegin;
    }

    public Object getContainerEnd() {
        return containerEnd;
    }

    public void setContainerEnd(Object containerEnd) {
        this.containerEnd = containerEnd;
    }

    public Object getContainerKeywords() {
        return containerKeywords;
    }

    public void setContainerKeywords(Object containerKeywords) {
        this.containerKeywords = containerKeywords;
    }

    public Object getShipmentReplaceChar() {
        return shipmentReplaceChar;
    }

    public void setShipmentReplaceChar(Object shipmentReplaceChar) {
        this.shipmentReplaceChar = shipmentReplaceChar;
    }

    public String getContainerBegin() {
        return containerBegin;
    }

    public void setContainerBegin(String containerBegin) {
        this.containerBegin = containerBegin;
    }

    public String getDepartureDateEnd() {
        return departureDateEnd;
    }

    public void setDepartureDateEnd(String departureDateEnd) {
        this.departureDateEnd = departureDateEnd;
    }

    public String getDepartureDateKeywords() {
        return departureDateKeywords;
    }

    public void setDepartureDateKeywords(String departureDateKeywords) {
        this.departureDateKeywords = departureDateKeywords;
    }

    public String getDepartureDateFormat() {
        return departureDateFormat;
    }

    public void setDepartureDateFormat(String departureDateFormat) {
        this.departureDateFormat = departureDateFormat;
    }

    public Long getBilltoVat1() {
        return billtoVat1;
    }

    public void setBilltoVat1(Long billtoVat1) {
        this.billtoVat1 = billtoVat1;
    }

    public double getBilltoVat2() {
        return billtoVat2;
    }

    public void setBilltoVat2(double billtoVat2) {
        this.billtoVat2 = billtoVat2;
    }

    public Long getBilltoVat3() {
        return billtoVat3;
    }

    public void setBilltoVat3(Long billtoVat3) {
        this.billtoVat3 = billtoVat3;
    }

    public String getAutoRemove() {
        return autoRemove;
    }

    public void setAutoRemove(String autoRemove) {
        this.autoRemove = autoRemove;
    }

    public Object getBacReplaceChar() {
        return bacReplaceChar;
    }

    public void setBacReplaceChar(Object bacReplaceChar) {
        this.bacReplaceChar = bacReplaceChar;
    }

    public String getBacBegin() {
        return bacBegin;
    }

    public void setBacBegin(String bacBegin) {
        this.bacBegin = bacBegin;
    }

    public String getIbanEnd() {
        return ibanEnd;
    }

    public void setIbanEnd(String ibanEnd) {
        this.ibanEnd = ibanEnd;
    }

    public String getIbanReplaceChar() {
        return ibanReplaceChar;
    }

    public void setIbanReplaceChar(String ibanReplaceChar) {
        this.ibanReplaceChar = ibanReplaceChar;
    }

    public String getBacKeywords() {
        return bacKeywords;
    }

    public void setBacKeywords(String bacKeywords) {
        this.bacKeywords = bacKeywords;
    }

    public Object getBacOffsetLines() {
        return bacOffsetLines;
    }

    public void setBacOffsetLines(Object bacOffsetLines) {
        this.bacOffsetLines = bacOffsetLines;
    }

    public String getIbanKeywords() {
        return ibanKeywords;
    }

    public void setIbanKeywords(String ibanKeywords) {
        this.ibanKeywords = ibanKeywords;
    }

    public Object getIbanOffsetLines() {
        return ibanOffsetLines;
    }

    public void setIbanOffsetLines(Object ibanOffsetLines) {
        this.ibanOffsetLines = ibanOffsetLines;
    }

    public String getBacEnd() {
        return bacEnd;
    }

    public void setBacEnd(String bacEnd) {
        this.bacEnd = bacEnd;
    }

    public String getIbanBegin() {
        return ibanBegin;
    }

    public void setIbanBegin(String ibanBegin) {
        this.ibanBegin = ibanBegin;
    }
}