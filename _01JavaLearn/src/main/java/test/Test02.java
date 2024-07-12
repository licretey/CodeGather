package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Description:
 * @Date: 2024/6/27 16:09
 */
public class Test02 {
    public static void main(String[] args) {
        extracted();
        System.out.println("over11");

    }

    private static void extracted() {
        List<String> list = Arrays.asList("a", "b", "c");
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        abbcd(list, map);
        System.out.println("over");
    }

    private static void abbcd(List<String> list, Map<String, String> map) {
        label:
        for (String s : list) {
            System.out.println(s);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry);
                if(entry.getKey().equals(s)){
                    System.out.println("找到了");
                    return;
                }else {

                }
            }
            System.out.println(s+" over");
        }
    }

}
