package org.licretey;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MapReduce {
    public static void main(String[] args) throws UnsupportedEncodingException {
        List<Book> books = null;
        // books.add(new Book("yang"));
        // books.add(new Book("jian"));
        // books.add(new Book("ping"));
        // System.out.println(StringUtils.i);
        // books = books.stream().filter(bb -> {bb.getAge()})
        // String s = books.stream().map(Book::getAge, Book::getSize).toString();
        // System.out.println(s);
        // System.out.println(integerStream);
        // List<Integer> collect = books.stream().map(Book::getSize,Book::getAge).collect(Collectors.toList());
        // System.out.println(collect);
        // Integer reduce = books.stream().map(Book::getAge).reduce(11, Math::addExact);
        // System.out.println(reduce);
        // System.out.println(Strings.i);
        // System.out.println(true);
        // System.out.println(StringUtils.isBlank(null));
        // LocalDateTime  nn = LocalDateTime.now();
        // System.out.println(nn);
        // System.out.println(nn.atOffset(ZoneOffset.MAX));

        BigDecimal b1 = new BigDecimal(3);
        BigDecimal b2 = new BigDecimal(0.1);
        // HashMap<String, Object> map = new HashMap<>();
        // System.out.println( bigDecimal.add(bigDecimal2).toString());
        // System.out.println( bigDecimal);
        // Object s3 = map.remove("s3");
        // Object s2 = map.remove("s2");
        // Object s4 = map.remove("s4");
        // if(s2 == null) System.out.println("0");
        // if(s2 != null) System.out.println(new BigDecimal(s2.toString()));
        //
        // if(s3 == null) System.out.println("0");
        // if(s3 != null) System.out.println(new BigDecimal(s3.toString()));
        //
        // if(s4 == null) System.out.println("0");
        // if(s4 != null) System.out.println(new BigDecimal(s4.toString()));

        // System.out.println(bigDecimal.stripTrailingZeros().toPlainString());
        // System.out.println(bigDecimal.setScale(0,BigDecimal.ROUND_HALF_UP));
        // System.out.println(bigDecimal2.setScale(0,BigDecimal.ROUND_HALF_UP));
        // System.out.println(new BigDecimal(s2).toString());
        // System.out.println(new BigDecimal(s3).toString());
        // System.out.println(new BigDecimal(s4).toString());
        // System.out.println(b1.multiply(b2).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        String a = StringUtils.getIfBlank(StringUtils.chop("yang"), () -> "");
        System.out.println(" yang" + a);
        List<String> list = new ArrayList<>();
        list.add("yang");
        list.add("jina");
        list.add("ping");
        list.forEach(st -> {
            System.out.println(st);
            throw new RuntimeException();
        });


    }


}
