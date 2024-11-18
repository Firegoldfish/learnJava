public class MyThread extends Thread {
    @Override
    public void run() {
        // 执行线程的代码
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();
    }
}