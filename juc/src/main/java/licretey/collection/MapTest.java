package licretey.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
    /**
     * HashMap不安全
     *      默认参数：加载因子0.75， 初始容量16
     *
     */
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String,String>();
        // map的集合
        Map<String,String> map1 = Collections.synchronizedMap(new HashMap<>());
        Map<String,String> map2 = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,5));
            },String.valueOf(i)).start();
        }
    }
}
