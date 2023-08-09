package licretey.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache cache = new MyCache();
        for (int i = 1; i < 5; i++){
            final int temp = i;
            new Thread(()->{
                cache.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i < 5; i++){
            final int temp = i;
            new Thread(()->{
                cache.get(temp+"");
            },String.valueOf(i)).start();
        }


        System.out.println("==================================");

        MyCacheLock cache2 = new MyCacheLock();
        for (int i = 1; i < 5; i++){
            final int temp = i;
            new Thread(()->{
                cache2.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i < 5; i++){
            final int temp = i;
            new Thread(()->{
                cache2.get(temp+"");
            },String.valueOf(i)).start();
        }
    }




}

// 自定义缓存

class MyCache{
    private volatile Map<String, Object> map = new HashMap<>();

    // 存 写：只允许一个线程
    public void put(String key, Object value){
        System.out.println(Thread.currentThread().getName()+"写入"+key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName()+"写入ok");
    }

    // 取 读 ：允许多个线程
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"读取"+key);
        map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取ok");
    }


}


class MyCacheLock{
    private volatile Map<String, Object> map = new HashMap<>();
    // 读写锁：
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    // 存 写：只允许一个线程
    public void put(String key, Object value){
        lock.writeLock().lock(); // 加上写锁

        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"写入ok");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }

    }

    // 取 读 ：允许多个线程
    public void get(String key){
        lock.readLock().lock(); // 加上读锁

        try {
            System.out.println(Thread.currentThread().getName()+"读取"+key);
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取ok");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
    }
}
