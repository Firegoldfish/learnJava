import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Thread {
    @Override
    public void run() {
        // 执行线程的代码
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();
    }
    public synchronized void someMethod(){
        // 一些代码
    }
    public void anotherMethod(){
        synchronized(someObject){
            // 一些代码
        }
    }
    public volatile int sharedVariable;
    private final ReentrantLock reentrantLock = new ReentrantLock();
    public void someMethod1(){
        reentrantLock.lock();
        try{
            // 1
        }
        finally{
            reentrantLock.unlock();
        }
    }
    AtomicInteger atomicInteger = new AtomicInteger(0);
    int newValue = atomicInteger.incrementAndGet();
    
    ThreadLocal<Integer> threadLocalVar = new ThreadLocal<>();
    threadLocalVar.set(10);
    int value = threadLocalVar.get();
}