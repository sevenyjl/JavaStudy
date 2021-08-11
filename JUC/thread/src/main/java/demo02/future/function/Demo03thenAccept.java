package demo02.future.function;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class Demo03thenAccept {

    public static void main(String[] args) {

    }

    /**
     * 任务 A 执行完执行 B，并且 B 不需要 A 的结果
     */
    private static void thenRun() {
        CompletableFuture<Void> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----1  come in");
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11;
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----2  come in");
        });
        for (int i = 0; i < 5; i++) {
            System.out.println("getNow:" + integerCompletableFuture.getNow(null));
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            System.out.println("get:" + integerCompletableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 任务 A、C 执行完执行 B，B 需要 A、C 的结果，但是任务 B 无返回值
     * 等待两个task完成，并进行同时消费
     */
    private static void thenAcceptBoth() {
        CompletableFuture<Void> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----1  come in");//ForkJoinPool.commonPool-worker-19	----1  come in
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11;
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----2  come in");//ForkJoinPool.commonPool-worker-5	----2  come in
            return 12;
        }), (result, result2) -> {
            System.out.println(Thread.currentThread().getName() + "\t----3  come in,result:" + result);//ForkJoinPool.commonPool-worker-19	----3  come in,result:11
            System.out.println(Thread.currentThread().getName() + "\t----3  come in,result2:" + result2);//ForkJoinPool.commonPool-worker-19	----3  come in,result2:12
        });
        for (int i = 0; i < 5; i++) {
            System.out.println("getNow:" + integerCompletableFuture.getNow(null));
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            System.out.println("get:" + integerCompletableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    /**
     * 任务 A 执行完执行 B，B 需要 A 的结果，但是任务 B 无返回值
     * 消费数据，get一直获取都是null
     */
    private static void thenAccept() {
        CompletableFuture<Void> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----1  come in");
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11;
        }).thenAccept(result -> {
            System.out.println(Thread.currentThread().getName() + "\t----2  come in,result:" + result);//ForkJoinPool.commonPool-worker-19	----2  come in,result:11
            //消费数据并返回null
        }).thenAccept(result -> {
            System.out.println(Thread.currentThread().getName() + "\t----3 come in,result:" + result);//ForkJoinPool.commonPool-worker-19	----3 come in,result:null
        });
        for (int i = 0; i < 5; i++) {
            System.out.println("getNow:" + integerCompletableFuture.getNow(null));
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            System.out.println("get:" + integerCompletableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
