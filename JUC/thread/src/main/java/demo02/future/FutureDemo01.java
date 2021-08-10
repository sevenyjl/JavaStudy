package demo02.future;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * 演示future使用
 * 案例：我在看书，突然口渴，又不想打断我学习的进度，只能叫我弟弟帮我接瓶水来
 */
public class FutureDemo01 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        demo06CompletableFuture();
    }

    /**
     * 这样就完全解决了问题
     */
    public static void demo06CompletableFuture() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        System.out.println(Thread.currentThread().getName() + "\t开始看书...");
        System.out.println(Thread.currentThread().getName() + "\t口渴了...叫弟弟拿水");
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t弟弟收到了...去接水了");
            int water = 0;
            for (int i = 0; i < 3; i++) {
                water += 100;
                //休眠1秒
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t接水中..." + water);
            }
            System.out.println(Thread.currentThread().getName() + "\t接水完成");
            return water;
        }, threadPoolExecutor).whenComplete((result, e) -> {
            if (e == null) {
                System.out.println(Thread.currentThread().getName() + "\t弟弟去接完了水");
                drink(result);
            }
        }).exceptionally((throwable -> {
            System.out.println(Thread.currentThread().getName() + "\t弟弟跑去玩了，接水失败");
            return 0;
        }));
        System.out.println(Thread.currentThread().getName() + "\t我在等待水的到来");
    }

    public static void drink(int water) {
        System.out.println(Thread.currentThread().getName() + "\t我喝到水了，一口气喝了" + water);
    }

    /**
     * 对比demo02FutureTask的优点（缺点一样）
     * 1. 可以进行完成后的再操作（如弟弟送水过程中 可以水会潵出来）
     * 2. 对异常进行控制了，防止弟弟跑了我还傻傻等着
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void demo05CompletableFuture() throws InterruptedException, ExecutionException {
        System.out.println(Thread.currentThread().getName() + "\t开始看书...");
        System.out.println(Thread.currentThread().getName() + "\t口渴了...叫弟弟拿水");
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t弟弟收到了...去接水了");
            int water = 0;
            for (int i = 0; i < 10; i++) {
                water += 100;
                System.out.println(Thread.currentThread().getName() + "\t接水中..." + water);
                //休眠1秒
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "\t接水完成");
            return water;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println(Thread.currentThread().getName() + "\t接水没意外，水=" + v);
            }
        }).exceptionally(e -> {
            System.out.println(Thread.currentThread().getName() + "\t弟弟打晃了!" + e.getMessage());
            System.out.println("-----exception: " + e.getCause() + "\t" + e.getMessage());
            return 0;
        });
        Integer integer = completableFuture.get();//不见不散，任然阻塞
        System.out.println(Thread.currentThread().getName() + "\t继续看书...");
        System.out.println(Thread.currentThread().getName() + "\t喝" + integer + "水了...");
    }

    /**
     * 缺点：
     * 虽然没有阻塞我主线程继续进行，但是我还是没有喝上水！！！
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void demo04FutureTask() throws InterruptedException, ExecutionException {
        System.out.println(Thread.currentThread().getName() + "\t开始看书...");
        System.out.println(Thread.currentThread().getName() + "\t口渴了...叫弟弟拿水");
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int water = 0;
                System.out.println(Thread.currentThread().getName() + "\t弟弟收到了...去接水了");
                for (int i = 0; i < 10; i++) {
                    water += 100;
                    System.out.println(Thread.currentThread().getName() + "\t接水中..." + water);
                    //休眠1秒
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "\t接水完成");
                return water;
            }
        });
        System.out.println(Thread.currentThread().getName() + "\t等待...");
        new Thread(integerFutureTask).start();
        Integer integer = null;
        try {
            integer = integerFutureTask.get(5, TimeUnit.SECONDS);//过时不侯，一般用这个
            System.out.println(Thread.currentThread().getName() + "\t喝" + integer + "水了...");
            System.out.println(Thread.currentThread().getName() + "\t继续看书...");
        } catch (TimeoutException e) {
            e.printStackTrace();
            System.out.println(Thread.currentThread().getName() + "\t等不急了，大发雷霆！！！！");
        }
    }

    /**
     * 对比demo02FutureTask，可能只解决了问题1 单线程问题。并没有解决阻塞。
     * 但是对于demo02FutureTask中的阻塞有两点好处：1.可以继续做自己的事情（局限）2.CAS比直接阻塞有时要强。缺点：自旋cpu资源消耗大
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void demo03FutureTask() throws InterruptedException, ExecutionException {
        System.out.println(Thread.currentThread().getName() + "\t开始看书...");
        System.out.println(Thread.currentThread().getName() + "\t口渴了...叫弟弟拿水");
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int water = 0;
                System.out.println(Thread.currentThread().getName() + "\t弟弟收到了...去接水了");
                for (int i = 0; i < 3; i++) {
                    water += 100;
                    System.out.println(Thread.currentThread().getName() + "\t接水中..." + water);
                    //休眠1秒
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "\t接水完成");
                return water;
            }
        });
        System.out.println(Thread.currentThread().getName() + "\t等待...");
        new Thread(integerFutureTask).start();
        while (!integerFutureTask.isDone()) {
            System.out.println(Thread.currentThread().getName() + "\t看书并等待...");
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Integer integer = integerFutureTask.get();//不见不散
        System.out.println(Thread.currentThread().getName() + "\t喝" + integer + "水了...");
        System.out.println(Thread.currentThread().getName() + "\t继续看书...");
    }

    /**
     * 缺点：
     * 1. 弟弟接水时开小差了，我一直在等待水的到来，才能继续看书（变成单线程了）
     * 2. 如果弟弟出去玩了，我还一直等着弟弟给我拿水，直到我口渴死亡也没有水喝┭┮﹏┭┮（阻塞主线程，可能照成程序出奇的慢）
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void demo02FutureTask() throws InterruptedException, ExecutionException {
        System.out.println(Thread.currentThread().getName() + "\t开始看书...");
        System.out.println(Thread.currentThread().getName() + "\t口渴了...叫弟弟拿水");
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int water = 0;
                System.out.println(Thread.currentThread().getName() + "\t弟弟收到了...去接水了");
                for (int i = 0; i < 3; i++) {
                    water += 100;
                    System.out.println(Thread.currentThread().getName() + "\t接水中..." + water);
                    //休眠1秒
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "\t接水完成");
                return water;
            }
        });
        System.out.println(Thread.currentThread().getName() + "\t等待...");
        new Thread(integerFutureTask).start();
        Integer integer = integerFutureTask.get();//不见不散，一般吧不用
        System.out.println(Thread.currentThread().getName() + "\t喝" + integer + "水了...");
        System.out.println(Thread.currentThread().getName() + "\t继续看书...");
    }

    /**
     * 1.缺点无返回值，不知道接了多少水
     * 2.使用的轮询等等不应该用线程数来判断（todo 改进wait/notify）
     */
    private static void demo01Thread() {
        System.out.println(Thread.currentThread().getName() + "\t开始看书...");
        System.out.println(Thread.currentThread().getName() + "\t口渴了...叫弟弟拿水");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "\t弟弟收到了...去接水了");
                for (int i = 0; i < 3; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t接水中...");
                    //休眠1秒
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "\t接水完成");
            }
        }).start();
        while (Thread.activeCount() > 2) {
            System.out.println(Thread.currentThread().getName() + "\t看书并等待...");
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t喝水了...");
        System.out.println(Thread.currentThread().getName() + "\t继续看书...");
    }
}
