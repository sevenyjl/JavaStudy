package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * shutdown和shutdownnow区别？
 * <p>
 * - shutdown()
 * - 将线程池状态置为SHUTDOWN,并不会立即停止
 * - 停止接收外部submit的任务
 * - 内部正在跑的任务和队列里等待的任务，会执行完
 * - shutdownNow()
 * - 将线程池状态置为STOP。企图立即停止，事实上不一定：
 * - 跟shutdown()一样，先停止接收外部提交的任务
 * - 忽略队列里等待的任务
 * - 尝试将正在跑的任务interrupt中断
 * - 返回未执行的任务列表
 * - 它试图终止线程的方法是通过调用Thread.interrupt()方法来实现的，但这种方法的作用有限，
 * 如果线程中没有sleep 、wait、Condition、定时锁等应用, interrupt()方法是无法中断当前的线程的。
 * 所以，ShutdownNow()并不代表线程池就一定立即就能退出，它也可能必须要等待所有正在执行的任务都执行完成了才能退出。
 */
public class ShutdownDemo {

    private static void shutdownNow() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            executorService.submit(new Task(i));
        }
        try {
            //休眠 1 SECONDS
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
        System.out.println("----------------over");
        for (int i = 0; i < 20; i++) {
            System.out.println("继续submit");
            executorService.submit(new Task(i));
        }
    }

    private static void shutdown() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            executorService.submit(new Task(i));
        }
        try {
            //休眠 1 SECONDS
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("----------------over");
        for (int i = 0; i < 20; i++) {
            System.out.println("继续submit");
            executorService.submit(new Task(i));
        }
    }

    static class Task extends Thread {
        private int name;

        public Task(int name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                //休眠 1 SECONDS
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "->run->" + name);
        }
    }
}
