package demo02.future.function;

import java.util.concurrent.*;

/**
 * 对计算结果进行处理
 * auth: seven
 * date: 2021/8/11 20:51
 */
public class Demo02ApplyFunction {

    public static void main(String[] args) {
//        whenApply();
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 20, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
//        thenApplyAsync();
    }

    /**
     * 这里不在会启动新的线程进行计算，这个不同于whenComplete。但是只限于两个async都在同一个线程池中
     */
    private static void thenApplyAsync() {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----come in");//pool-1-thread-1	----come in
            //休眠2秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11;
        }).thenApplyAsync((result) -> {
            System.out.println(Thread.currentThread().getName() + "\t----come in2,result:" + result);//pool-1-thread-2	----come in2
//            int i = 10 / 0;//出现异常就不会继续下面的apply方法了
            //休眠2秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11 + result;
        }).thenApplyAsync((result) -> {
            System.out.println(Thread.currentThread().getName() + "\t----come in3,result:" + result);
            //休眠2秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11 + result;
        });
        for (int i = 0; i < 5; i++) {
            System.out.println(integerCompletableFuture.getNow(null));
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
        System.out.println(integerCompletableFuture.getNow(null));
    }

    /**
     * 任务 A 执行完执行 B，B 需要 A 的结果，同时任务 B 有返回值
     * 得到计算结果，再进行计算.
     * getNow一定是所有apply运行完成才会返回。相当于他们被绑成了一条绳上的蚂蚱，要么同生要么同死（结果完成运算完成才返回，一个报异常，后面全部拜拜）
     */
    private static void whenApply() {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----come in");
            //休眠2秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11;
        }).thenApply((result) -> {
            System.out.println(Thread.currentThread().getName() + "\t----come in2,result:" + result);
            //            int i = 10 / 0;//出现异常就不会继续下面的apply方法了
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11 + result;
        }).thenApply((result) -> {
            System.out.println(Thread.currentThread().getName() + "\t----come in3,result:" + result);
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11 + result;
        });
        for (int i = 0; i < 5; i++) {
            System.out.println(integerCompletableFuture.getNow(null));
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
        System.out.println(integerCompletableFuture.getNow(null));
    }
}
