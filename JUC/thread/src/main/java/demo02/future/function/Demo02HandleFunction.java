package demo02.future.function;

import java.util.concurrent.*;

/**
 * 对计算结果进行处理
 * auth: seven
 * date: 2021/8/11 20:51
 */
public class Demo02HandleFunction {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----1  come in");
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11;
        }).handle((result, throwable) -> {
            System.out.println(Thread.currentThread().getName() + "\t----2  come in,result:" + result);
            System.out.println(Thread.currentThread().getName() + "\t----2  come in,throwable:" + throwable);
//            int i = 10 / 0;//虽然我这里报错了，但是不影响下一个handle的运行，我会把异常给下一个handle，并且返回null
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11 + (result == null ? 0 : result);
        }).handle((result, throwable) -> {
            System.out.println(Thread.currentThread().getName() + "\t----3  come in,result:" + result);
            System.out.println(Thread.currentThread().getName() + "\t----3  come in,throwable:" + throwable);
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 12 + (result == null ? 0 : result);
        });
        for (int i = 0; i < 5; i++) {
            System.out.println("getNow:" + integerCompletableFuture.getNow(null));
//            System.out.println(integerCompletableFuture.get());
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
