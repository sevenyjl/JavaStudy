package demo02.future.function;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 被动通知计算结果
 */
public class Demo01BDCompleteFunction {

    public static void main(String[] args) {
        //自定义线程池，为了防止主线程运行完毕 程序就停止了(实际开发中需要手动shutdown)
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 20, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
//        whenComplete(threadPoolExecutor);//一个线程运行完
        whenCompleteAsync(threadPoolExecutor);//再起一个线程运行剩余的工作
//        threadPoolExecutor.shutdown();
    }

    /**
     * pool-1-thread-1	----come in
     * ForkJoinPool.commonPool-worker-19	e:null
     * ForkJoinPool.commonPool-worker-19	f:11
     *
     * @param threadPoolExecutor
     */
    private static void whenCompleteAsync(ThreadPoolExecutor threadPoolExecutor) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----come in");
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11;
        }, threadPoolExecutor).whenCompleteAsync((f, e) -> {
            System.out.println(Thread.currentThread().getName() + "\te:" + e);
            System.out.println(Thread.currentThread().getName() + "\tf:" + f);
        });
    }

    /**
     * pool-1-thread-1	----come in
     * pool-1-thread-1	e:null
     * pool-1-thread-1	f:11
     *
     * @param threadPoolExecutor
     */
    private static void whenComplete(ThreadPoolExecutor threadPoolExecutor) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----come in");
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11;
        }, threadPoolExecutor).whenComplete((f, e) -> {
            System.out.println(Thread.currentThread().getName() + "\te:" + e);
            System.out.println(Thread.currentThread().getName() + "\tf:" + f);
        });
    }
}
