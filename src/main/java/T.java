import org.w3c.dom.Node;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class T {
    private static volatile int flag = 0;


}

class ExecutorsDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Object o1 = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "\texecutors.submit(Runnable)");
            }
        }).get();
        System.out.println("executors.submit(Runnable) 的结果：" + o1);
        Object o = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println(Thread.currentThread().getName() + "\texecutors.submit(Callable)");
                return "hello";
            }
        }).get();
        System.out.println("executors.submit(Callable) 的结果：" + o);
        String hello = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "\texecutors.submit(Runnable,Result)");
            }
        }, "hello").get();
        System.out.println("executors.submit(Runnable,Result) 的结果：" + o);
        //todo 为什么主线程一直挂着？过一会才完成
    }
}

class CallableDemo implements Callable<Integer> {
    public static void main(String[] args) throws Exception {
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new CallableDemo());
        FutureTask<Integer> integerFutureTask2 = new FutureTask<>(new CallableDemo());
        new Thread(integerFutureTask).start();
        System.out.println(integerFutureTask.get());
        new Thread(integerFutureTask).start();
        System.out.println(integerFutureTask.get());
        new Thread(integerFutureTask).start();
        System.out.println(integerFutureTask.get());
        new Thread(integerFutureTask2).start();
        System.out.println(integerFutureTask2.get());
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\tCallable启动了...");
        return new Random().nextInt();
    }
}

class RunnableDemo implements Runnable {

    public static void main(String[] args) throws Exception {
        new Thread(new RunnableDemo()).start();
        new Thread(new RunnableDemo()).start();
        new Thread(new RunnableDemo()).start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "\tRunnable启动了...");
    }
}

class ThreadDemo extends Thread {
    public static void main(String[] args) throws Exception {
        new ThreadDemo().start();
        new ThreadDemo().start();
        new ThreadDemo().start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "\tThread启动了..");
        super.run();
    }
}
