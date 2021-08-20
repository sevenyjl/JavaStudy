package demo02.future;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class FutureDemo02 {
    public static void main(String[] args) {
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
            //休眠1  SECONDS
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t1 over");
        });
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
            //休眠2  SECONDS
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t2 over");
        });
        CompletableFuture<Void> task3 = CompletableFuture.runAsync(() -> {
            //休眠3  SECONDS
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t3 over");
        });
        CompletableFuture<Void> task4 = CompletableFuture.runAsync(() -> {
            //休眠4  SECONDS
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t4 over");
        });
        //休眠3  SECONDS
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("start");
        CompletableFuture<Void> over = CompletableFuture.allOf(task1, task2, task3, task4);
        //所有task运行完毕才往下执行
        over.join();
        System.out.println("end");

    }

    private static void demoWithCombine() {
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
            //休眠1  SECONDS
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t1运行完成");
        });
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t2运行完成");
        });
        CompletableFuture<Void> task3 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t3运行完成");
        });
        CompletableFuture<Void> task4 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t4运行完成");
        });
        Object join = task1.thenCombine(task2, (r1, r2) -> {
            System.out.println(Thread.currentThread().getName() + "\t1-->结果1：" + r1 + " 结果2：" + r2);
            return null;
        }).thenCombine(task3, (r1, r2) -> {
            System.out.println(Thread.currentThread().getName() + "\t2-->结果1：" + r1 + " 结果2：" + r2);
            return null;
        }).thenCombine(task4, (r1, r2) -> {
            System.out.println(Thread.currentThread().getName() + "\t3-->结果1：" + r1 + " 结果2：" + r2);
            return null;
        }).join();
        System.out.println(join);
    }
}
