package threadLocal;

import cn.hutool.core.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * threadLocal，为什么会出现?
 * 案例：有个卖电影票单位，有3个售卖员，每人随机卖1~50张，请统计售卖总共多少张？
 */
public class Demo01TreadLocal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        System.out.println("---" + next);
//        myLocal();//耗时：189毫秒
        threadLocalSpeed();//耗时：1199毫秒  ?? 性能上 毫无优势可言。。。

        //休眠999  DAYS
        try {
            TimeUnit.DAYS.sleep(999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 耗时：227毫秒
     */
    private static void threadLocalSpeed() {
        long startTime = System.currentTimeMillis();
        MovieTicket movieTicket = new MovieTicket();
        final int threadSize = 50;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    for (int j = 0; j < 100 * 10000; j++) {
                        movieTicket.sell();
//                        System.out.println(Thread.currentThread().getName() + "\t销售了：" + movieTicket.getThreadLocal().get());
                    }
                } finally {
                    movieTicket.report();
                    countDownLatch.countDown();
                }
            }, "thread " + i);
            threadTemp.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("销售完成，总共销售：" + movieTicket.getTotal());
        System.out.printf("耗时：%s毫秒\n", System.currentTimeMillis() - startTime);
    }

    private static void threadLocalDemo() {
        MovieTicket movieTicket = new MovieTicket();
        final int threadSize = 3;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    for (int j = 0; j < RandomUtil.randomInt(0, 10); j++) {
                        movieTicket.sell();
                        System.out.println(Thread.currentThread().getName() + "\t销售了：" + movieTicket.getThreadLocal().get());
                    }
                } finally {
                    movieTicket.report();
                    countDownLatch.countDown();
                }
            }, "thread " + i);
            threadTemp.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("销售完成，总共销售：" + movieTicket.getTotal());
    }

    private static void myLocal() {
        //50个线程，每个搞到 100*10000
        long startTime = System.currentTimeMillis();
        MovieTicket movieTicket = new MovieTicket();
        final int threadSize = 50;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    MovieTicket.Seller seller = movieTicket.instance();
                    for (int j = 0; j < 100 * 10000; j++) {
                        seller.sellTicket();
//                        System.out.println(Thread.currentThread().getName() + "\t销售了：" + seller.getSyncNum());
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, "thread " + i);
            threadTemp.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("销售完成，总共销售：" + movieTicket.sum());
        System.out.printf("耗时：%s毫秒\n", System.currentTimeMillis() - startTime);
    }

    private static void smallDemo() {
        MovieTicket movieTicket = new MovieTicket();
        final int threadSize = 3;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    MovieTicket.Seller seller = movieTicket.instance();
                    for (int j = 0; j < RandomUtil.randomInt(0, 10); j++) {
                        seller.sellTicket();
                        System.out.println(Thread.currentThread().getName() + "\t销售了：" + seller.getSyncNum());
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, "thread " + i);
            threadTemp.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("销售完成，总共销售：" + movieTicket.sum());
    }

    public static class MovieTicket {
        private List<Seller> sellerList = new ArrayList<>();
        private int total = 0;

        private ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

        public ThreadLocal<Integer> getThreadLocal() {
            return threadLocal;
        }

        public void sell() {
            Integer value = threadLocal.get();
            value++;
            threadLocal.set(value);
        }

        public synchronized void report() {
            total += threadLocal.get();
        }

        public int getTotal() {
            return total;
        }

        public int sum() {
            int count = 0;
            for (Seller seller : sellerList) {
                count += seller.syncNum;
            }
            return count;
        }

        private synchronized Seller instance() {
            Seller seller = new Seller(0);
            sellerList.add(seller);
            return seller;
        }

        public static class Seller {
            private Seller(int syncNum) {
                this.syncNum = syncNum;
            }

            private int syncNum;

            public synchronized void sellTicket() {
                syncNum++;
            }

            public int getSyncNum() {
                return syncNum;
            }
        }
    }
}
