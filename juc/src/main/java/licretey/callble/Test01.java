package licretey.callble;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 新版
        new Thread().start();
        FutureTask futureTask = new FutureTask(new myCallThread());
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();
        String str = (String)futureTask.get(); // 获取返回结果，可能阻塞

        // 旧版
        // new Thread(new myThread()).start();
    }
}

class myThread implements Runnable{

    @Override
    public void run() {
        System.out.println("11111");
    }
}


class myCallThread implements Callable<String> {

    @Override
    public String call() {
        System.out.println("11111");
        return "2333";
    }
}
