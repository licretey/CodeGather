package licretey.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetTest {

    public static void main(String[] args) {
        Set<String> set = new HashSet<String>(); // 线程不安全

        Set<String> set1 = Collections.synchronizedSet(new HashSet<>());
        Set<String> set2 = new CopyOnWriteArraySet<>();



        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }).start();
        }
    }
}
