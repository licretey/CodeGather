package licretey.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
    public static void main(String[] args) {
        List list = new ArrayList(); // 线程不安全
        // 线程安全的三种方式
        List list1 = new Vector();
        List list2 = Collections.synchronizedList(new ArrayList<>());
        List list3 = new CopyOnWriteArrayList();

    }
}
