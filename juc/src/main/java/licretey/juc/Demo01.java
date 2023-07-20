package licretey.juc;

public class Demo01 {
    public static void main(String[] args) {
        // cpu密集型 IO密集型
        // 获取cpu核数
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
