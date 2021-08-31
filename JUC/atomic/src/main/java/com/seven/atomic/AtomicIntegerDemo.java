package com.seven.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Integer 运算问题 这里对比  volatile
 */
public class AtomicIntegerDemo {
    public static void main(String[] args) {
        AtomicResource atomicResource = new AtomicResource();
        VolatileResource volatileResource = new VolatileResource();
        final int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    for (int j = 0; j < 100; j++) {
                        atomicResource.add();
                        volatileResource.add();
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
        System.out.println("AtomicResource=" + atomicResource.getRunResult());//10000
        System.out.println("VolatileResource=" + volatileResource.getRunResult());//<=10000
    }

    public static class AtomicResource {
        private AtomicInteger m = new AtomicInteger(0);

        public void add() {
            m.getAndIncrement();
        }

        public int getRunResult() {
            return m.get();
        }
    }

    public static class VolatileResource {
        private volatile int m = 0;

        public void add() {
            m++;
        }

        public int getRunResult() {
            return m;
        }
    }
}
