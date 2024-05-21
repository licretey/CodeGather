package org.licretey;

public enum InvoiceParseStatus {
    PARSE_SUCCESS, // 解析成功
    PARSE_FAILURE, // 解析失败
    PUSH_SUCCESS, // 关联成功
    PUSH_FAILURE // 关联失败
    ;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
