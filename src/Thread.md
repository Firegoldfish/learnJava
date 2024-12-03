# Java并发
## 多线程
### 线程的创建
+ 继承Thread类  
这是最直接的一种方法，用户自定义类继承java.lang.Thread类，重写其run()方法，run()方法定义了线程的执行具体任务。创建该类的实例后，通过调用start()方法启动线程。
```Java
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
```
优点：编写简单，如果需要访问当前线程，无需使用Thread.currentThread()方法，直接调用this，即可获得该线程。  
缺点：因为线程继承了Thread类，所以不能再继承其他的类。
+ 实现Runnable接口  
如果一个类已经继承了其他类，所以无法在继承Thread类，此时可以使用Runnable接口。实现接口需要重写run()方法，然后将此Runnable对象作为参数传递给Thread类的构造器，创建Thread对象后再调用其start()方法启动线程。
```Java
public class MyRunnable implements Runnable {
    public void run() {
        //线程执行的代码
    }

    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();
    }
}
```
优点：线程类只实现了Runnable接口，还可以继承其他的类。在这种情况下，可以多线程共享同一个目标对象，所以非常适合多个相同线程来处理同一份资源的情况，从而可以将CPU和代码分开。体现了面向对象的思想。  
缺点：编程稍微复杂，如果想访问当前线程，必须调用Thread.currentThread()方法。
+ 实现Callable接口与FutureTask  
java.util.concurrent.Callable接口类似于Runnable，但Callable的call()方法可以有返回值并可以抛出异常。要执行Callable任务，需要将其先包装一个FutureTask，因为Thread类的构造器只接受Runnable参数，而FutureTask实现了Runnable接口。  
```Java
public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        //线程执行的代码，此处返回一个0
        return 0;
    }

    public static void main(String[] args) {
        MyCallable myCallable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            Integer result = futureTask.get();
            System.out.println(result);
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
```
优点：线程只实现了Runnable或实现Callable接口，还可以继承其他类。这种方式下，多个线程可以共享一个target对象，非常适合多线程处理同一份资源的情形。  
缺点：代码复杂，如果要访问当前线程，需使用Thread.currentThread()
+ 使用线程池  
从Java5开始的java.util.concurrent.ExecutorService和相关类提供了线程池的支持，这是一种高效的线程管理，避免了频繁创建和销毁线程的开销。可以通过Executor类的静态方法创建不同类型的线程池。  
```Java
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
```
优点：线程池可以重用预先创建的线程，避免了线程创建和销毁的开销，显著提高了性能。  
缺点：线程池增加了程序的复杂度。
### 线程的启动
启动线程通过Thread类的start()方法
```Java
MyThread myThread = new MyThread();
myThread.start();
```
### 线程的停止
+ 异常法停止：调用interrupt()方法后，在线程的run方法中判断当前对象的interrupt()状态，如果是中断则抛出异常，以达到中断线程的效果。
+ 沉睡中停止：先将线程sleep，然后调用interrupt标记中断状态，interrupt会将阻塞状态的线程中断。会抛出中断异常以达到停止的效果。
+ stop()暴力停止：线程调用stop()会暴力停止。但可能使得一些请理性的工作无法完成。
+ 使用return停止：调用interrupt标记为中断后，在run方法判断当前线程状态，如果为中断，则return。
### 阻塞和等待
+ 线程进入阻塞态，通常试图获取一个对象的锁，但该锁已被其他线程持有。通常发生在尝试进入synchronized块时，如果锁已被占用，则线程将被阻塞直到锁可用。
+ 线程进入等待态，因为其正在等待另一个线程执行某些操作，例如调用Object.wait()方法、Thread.join()、LockSupport.park()。在这些状态下，线程不消耗CPU，也不会参与锁竞争。
### 等待态恢复运行态
+ 等待的线程被其他线程对象唤醒，notify()、notifyAll()。
+ 如果线程没有获得锁，则进入等待态，实际上执行了LockSupport.park()进入wait，则在解锁时执行LockSupport.unpark(Thread)方法，解除等待。
### notify()和notifyAll()
两者都是唤醒等待的进程，都是最多只有一个线程能获得锁，但不能控制哪个线程获得锁。
+ notify：唤醒一个进程，其他进程依然处于wait等待唤醒，如果被唤醒的线程结束时没有调用notify，则永远不唤醒，只能等待超时，或者等中断。
+ notifyAll：所有进程退出wait，开始竞争锁，只有一个线程能抢到，这个线程执行完后，其他线程又开始竞争，又一个竞争成功，以此类推。
### notify的选择性
在JDK源码中，notify注释说到其选择为随机的，取决于jvm，流行的是hotspot,但并不是随机唤醒，而是先进先出。
## 并发安全
### 怎样保证多线程安全
+ <b>synchronized关键字</b>：使用synchronized关键字来同步代码块或者方法，确保同一时刻只有一个线程访问这些代码。对象锁是通过synchronized关键字锁定对象的监视器monitor实现的。
```Java
public synchronized void someMethod(){
        // 一些代码
    }
    public void anotherMethod(){
        synchronized(someObject){
            // 一些代码
        }
    }
```
+ <b>volatile关键字</b>：volatile关键字用于变量，确保所有线程看到的该变量的所有值，而不是可能存储在内部寄存器的副本。
```Java
public volatile int sharedVariable;
```
+ <b>Lock接口和ReentrantLock类</b>：java.util.concurrent.Lock接口提供了比synchronized更强大的锁定机制，ReentreantLock是一个实现该接口的例子，提供了更灵活的锁管理和更高的性能。
```Java
private final ReentrantLock reentrantLock = new ReentrantLock();
    public void someMethod1(){
        reentrantLock.lock();
        try{
            // 1
        }
        finally{
            reentrantLock.unlock();
        }
```
+ <b>原子类</b>：Java并发库提供了原子类，如AtomicInteger、AtomicLong等，这些类提供了原子操作，可用于更新基本类型的变量而无需额外的同步。
```Java
  AtomicInteger atomicInteger = new AtomicInteger(0);
  int newValue = atomicInteger.incrementAndGet();
```
+ <b>线程局部变量</b>：ThreadLocal类可以为每个线程提供独立的变量副本，这样每个线程都拥有自己的变量，消除了竞争。
```Java
    ThreadLocal<Integer> threadLocalVar = new ThreadLocal<>();
    threadLocalVar.set(10);
    int value = threadLocalVar.get();
```
+ <b>并发集合</b>：使用java.util.concurrent包中的线程安全集合，如ConcurrentHashMap、ConcurrentLinkedQueue等，这些集合内部已经实现了线程安全。
+ <b>JUC工具类</b>：java.util.concurrent包中的一些工具类可以控制线程的同步和协作。如Semaphore和CyclicBarrier等。
哈吉米
哈吉米
哈吉米