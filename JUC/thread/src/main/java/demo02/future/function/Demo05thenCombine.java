package demo02.future.function;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 两个CompletionStage任务都完成后，最终能把两个任务的结果一起交给thenCombine 来处理
 * 先完成的先等着，等待其它分支任务
 */
public class Demo05thenCombine {
    public static void main(String[] args) {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----1  come in");//ForkJoinPool.commonPool-worker-19	----1  come in
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----2  come in");//ForkJoinPool.commonPool-worker-5	----2  come in
            return 1;
        }), (result1, result2) -> {
            //ForkJoinPool.commonPool-worker-19	----3  come in,result1:11
            System.out.println(Thread.currentThread().getName() + "\t----3  come in,result1:" + result1);
            //ForkJoinPool.commonPool - worker - 19---- 3 come in, result2:1
            System.out.println(Thread.currentThread().getName() + "\t----3  come in,result2:" + result2);
            return result1 + result2;
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
