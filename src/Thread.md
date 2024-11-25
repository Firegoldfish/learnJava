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
+ 异常法停止：
+ 沉睡中停止：
+ stop()暴力停止：
+ 使用return停止：
### 