package demo02.future.function;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 谁快用谁
 * auth: seven
 * date: 2021/8/11 21:36
 */
public class Demo04applyToEither {

    public static void main(String[] args) {
        applyToEither();

    }

    private static void applyToEither() {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----1  come in");//ForkJoinPool.commonPool-worker-19	----1  come in
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11;
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----2  come in");//ForkJoinPool.commonPool-worker-5	----2  come in
            return 0;
        }), result -> {
            System.out.println(Thread.currentThread().getName() + "\t----3  come in,result:" + result);//main	----3  come in,result:0
            return result;
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
