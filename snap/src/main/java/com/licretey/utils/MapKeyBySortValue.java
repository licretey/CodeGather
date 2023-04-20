package com.licretey.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MapKeyBySortValue {
    public static void main(String[] args) {
        Map<String,Integer> countArea = new HashMap<>();// 统计最多的库位
        String[] ids = scwi.getValue("ids").toString().split(",");
        for (String id : ids) {
            SupplyChainWarehouseIoDetail ioDetail = SupplyChainWarehouseIoDetail.findById(ctx, Long.parseLong(id));
            ioDetail.setSupplyChainWarehouseIo(supplyChainWarehouseIo.getId());
            ioDetail.save();

            int count = countArea.getOrDefault(ioDetail.getAreaCode(),0) + 1;
            // if (countArea.containsKey(ioDetail.getAreaCode())) {
            //     count = countArea.get(ioDetail.getAreaCode()).intValue() + 1 ;
            // }
            countArea.put(ioDetail.getAreaCode(), count);
        }
        Map.Entry<String, Integer> maxEntry = countArea.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .orElse(null);
    }
}
