package org.licretey;


import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Value {
     transient
     int v;
     public Value(int v) {
         this.v = v;
     }

    @Override
    public String toString() {
        return "Value{" +
                "v=" + v +
                '}';
    }

    public final void cal(){
         this.v *= 10;
    }
}

class Value2 extends Value{

    public Value2(int v) {
        super(v);
    }

}


@Slf4j
public class FinalTest {

     final int f1 = 1;
     final int f2;
     public FinalTest() {
         f2 = 2;
     }

     public static void main(String[] args) {
         final int value1 = 1;
         // value1 = 4;
         final double value2;
         value2 = 2.0;
         final Value value3 = new Value(1);
         System.out.println(value3); // 1
         value3.v = 4;
         value3.cal();
         System.out.println(value1); // 1
         System.out.println(value2); // 2.0
         System.out.println(value3); // 4

         // 类型擦除  那
         // Map map2 = new HashMap();
         Map<String, String> map = new HashMap<String, String>();
         map.put("name", "hollis");
         map.put("wechat", "Hollis");
         map.put("blog", "www.hollischuang.com");
         Set<Map.Entry<String, String>> entries = map.entrySet();
         for (Map.Entry<String, String> entry : entries) {
             String key = entry.getKey();
             String value = entry.getValue();
             System.out.println(key+"----"+value);
             //Logger log = new Logger();
             //log.notify(); //  自动拆箱装箱
             log.info("key:{}, value:{}", key, value);
         }
     }
 }

