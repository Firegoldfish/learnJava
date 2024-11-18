public class MyRunnable implements Runnable {
    public void run() {
        //线程执行的代码
    }

    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();
    }
}
