package com.collection;

import com.google.common.collect.HashBasedTable;

import java.util.Map;

/**
 * @Description:
 * @Date: 2024/12/11 17:20
 */
public class HashBaseTable {
    public static void main(String[] args) {
        HashBasedTable<String, String, String> table = HashBasedTable.create();
        table.put("a", "1", "A");
        table.put("a", "2", "B");
        table.put("b", "3", "C");
        table.put("b", "4", "D");
        System.out.println(table);
        System.out.println(table.rowKeySet());
        System.out.println(table.values());
        Map<String, Map<String, String>> stringMapMap = table.rowMap();
        System.out.println(stringMapMap);
    }
}
