package temp;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CF {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        System.out.println(Thread.currentThread().getName() + "\t我在看书");
        System.out.println(Thread.currentThread().getName() + "\t我口渴了..叫弟弟接说");
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t弟弟去接水");
            int water = 0;
            for (int i = 0; i < 3; i++) {
                water += 100;
                //休眠1秒
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t接了" + water + "水");
            }
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
}
