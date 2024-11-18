import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPoll implements Runnable {
    @Override
    public void run() {
        //线程执行的代码
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5); //线程池大小
        for (int i = 0; i < 5; i++) {
            executorService.submit(new MyThreadPoll()); //提交任务到线程池
        }
        executorService.shutdown(); //关闭
    }
}
