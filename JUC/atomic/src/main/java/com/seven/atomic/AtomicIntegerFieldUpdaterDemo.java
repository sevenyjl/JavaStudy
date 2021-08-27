package com.seven.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 对非现场安全的对象 进行线程安全的操作
 */

public class AtomicIntegerFieldUpdaterDemo {
    private int num = 0;
    private volatile int updaterNum = 0;//Must be volatile type
    private volatile int numVolatile = 0;

    public void addNum() {
        num++;
    }

    public void addNumVolatile() {
        numVolatile++;
    }

    public int getNum() {
        return num;
    }

    public int getUpdaterNum() {
        return updaterNum;
    }

    public int getNumVolatile() {
        return numVolatile;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterDemo> updaterNum = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterDemo.class, "updaterNum");
        AtomicIntegerFieldUpdaterDemo demo = new AtomicIntegerFieldUpdaterDemo();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            Thread threadTemp = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    demo.addNum();
                    demo.addNumVolatile();
                    updaterNum.getAndIncrement(demo);
                }
                countDownLatch.countDown();
            }, "thread " + i);
            threadTemp.start();
        }
        countDownLatch.await();
        System.out.println("getNum" + demo.getNum());
        System.out.println("getUpdaterNum" + demo.getUpdaterNum());
        System.out.println("getNumVolatile" + demo.getNumVolatile());

    }
}
