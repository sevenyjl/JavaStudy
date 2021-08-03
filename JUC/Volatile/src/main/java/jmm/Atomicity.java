package jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 验证JMM的原子性
 * 保证可见性基础上，还需要保证原子性（有的不需要保证原子性的业务）
 * synchronized、AtomicXXX类能保证原子性
 */
public class Atomicity {

    public static void main(String[] args) {
        error();
        sync();
        atomic();
    }

    public static void error() {
        NonAtomic nonAtomic = new NonAtomic();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    nonAtomic.addPlusPlus();
                }
            }, "thread " + i).start();
        }
        while (Thread.activeCount() > 2) {
            //暂停1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("最终计算结果：" + nonAtomic.num);
        /**
         * 最终计算结果：2000
         * 最终计算结果：1922
         * 最终计算结果：1958
         */
    }

    public static void sync() {
        SyncAtomic nonAtomic = new SyncAtomic();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    nonAtomic.addPlusPlus();
                }
            }, "thread " + i).start();
        }
        while (Thread.activeCount() > 2) {
            //暂停1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("最终计算结果：" + nonAtomic.num);
        /**
         * 最终计算结果：2000
         * 最终计算结果：2000
         * 最终计算结果：2000
         */
    }

    public static void atomic() {
        Atomic nonAtomic = new Atomic();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    nonAtomic.addPlusPlus();
                }
            }, "thread " + i).start();
        }
        while (Thread.activeCount() > 2) {
            //暂停1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("最终计算结果：" + nonAtomic.num);
        /**
         * 最终计算结果：2000
         * 最终计算结果：2000
         * 最终计算结果：2000
         */
    }
}


class Atomic {
    public volatile AtomicInteger num = new AtomicInteger();

    public void addPlusPlus() {
        num.getAndIncrement();
    }
}

class SyncAtomic {
    public volatile int num = 0;

    public synchronized void addPlusPlus() {
        num++;
    }
}

class NonAtomic {
    public volatile int num = 0;

    public void addPlusPlus() {
        num++;
    }
}
