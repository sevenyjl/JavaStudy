package demo02.future.function;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * CompletableFuture 常用方法之 主动获取计算结果
 * 用于主动获取结果
 * auth: seven
 * date: 2021/8/11 20:25
 */
public class Demo01ZDGetFunction {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        getDemo();
//        getTimeOut();
//        getNow();
//        join();
    }

    /**
     * 阻塞获取不抛异常
     */
    private static void join() {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----come in");
            //休眠2秒
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11;
        });
        System.out.println(integerCompletableFuture.join());
    }

    /**
     * 立刻获取，没有就返回默认值
     */
    private static void getNow() {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----come in");
            //休眠2秒
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11;
        });
        System.out.println(integerCompletableFuture.getNow(100));//现在就要，没有我给你默认值
        //休眠1秒
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(integerCompletableFuture.getNow(200));
        //休眠1秒
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(integerCompletableFuture.getNow(300));
        //休眠1秒
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TODO: 2021/8/11 研究底层原理
        System.out.println(integerCompletableFuture.getNow(400));//输出11，已经计算完成就不获取默认值了
    }

    /**
     * 限时获取，超时没有就报错
     *
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    private static void getTimeOut() throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----come in");
            //休眠2秒
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11;
        });
        try {
            System.out.println(integerCompletableFuture.get(1, TimeUnit.SECONDS));//过时不侯，这里会报错
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println(integerCompletableFuture.get(3, TimeUnit.SECONDS));//过时不侯，这里正常运行
    }

    /**
     * 阻塞获取抛出异常
     */
    private static void getDemo() {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t----come in");
            //休眠2秒
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11;
        });
        try {
            System.out.println(integerCompletableFuture.get());//不见不散
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
