package test;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.javassist.expr.NewArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Description:
 * @Date: 2024/6/27 16:09
 */
public class Test03 {
    public static void main(String[] args) {
        String a = "yang,jian,ping";
        List<String> list = Optional.ofNullable(a)
                .map(s -> Arrays.asList(StringUtils.split(s, ",")))
                .orElse(new ArrayList<>());
        System.out.println(list);
        if (!list.stream().allMatch(str-> "yag".startsWith(str))) {
            System.out.println("包含");
        }

        List<String> b = Arrays.asList(" ",null,"yang");
        b.forEach(s -> {
            if(" ".equals(s))return;
            System.out.println(s);
        });


        String aa = "{\"waybillNumber\":\"6A49389109829\"}";
        String bb = "{\"waybillNumber\":\"6A49389109829\"} \n";
        System.out.println(aa.length());
        System.out.println(bb.length());



    }


}
