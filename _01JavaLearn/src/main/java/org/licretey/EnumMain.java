package org.licretey;

public class EnumMain {
    public static void main(String[] args) {
        System.out.println(InvoiceParseStatus.PARSE_FAILURE.toString());
        System.out.println(InvoiceParseStatus.PARSE_FAILURE.getStatus());
        System.out.println(InvoiceParseStatus.PARSE_FAILURE);
    }
}
