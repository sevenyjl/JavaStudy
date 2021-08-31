package com.seven.atomic;

import cn.hutool.core.util.RandomUtil;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 使用场景：对大多int需要修改的做运算
 * 这里就以买牌为例：
 * 现在有10种票，1等、2等。。
 * 每个都有100张
 * 现在有 200人（线程）买票，每人能买10张，随机到任意窗口购买。请编写
 */

public class AtomicIntegerArrayDemo {
    public static void main(String[] args) throws InterruptedException {
        Ticket ticket = new Ticket();
        CountDownLatch countDownLatch = new CountDownLatch(200);
        for (int i = 0; i < 200; i++) {
            Thread threadTemp = new Thread(() -> {
                int count = 0;
                for (int j = 0; j < 10; j++) {
                    int num = RandomUtil.randomInt(0, 10);
                    if (ticket.getTicket(num)) {
                        count++;
                    }
                }
                System.out.println(Thread.currentThread().getName() + "\t抢到了" + count + "张");
                countDownLatch.countDown();
            }, "thread " + i);
            threadTemp.start();
        }
        countDownLatch.await();
        ticket.showTicket();
    }

    public static class Ticket {
        public static AtomicIntegerArray atomicIntegerArray = null;

        static {
            int[] ints = new int[10];
            Arrays.fill(ints, 100);
            atomicIntegerArray = new AtomicIntegerArray(ints);
        }

        public boolean getTicket(int i) {
            int num = atomicIntegerArray.get(i);
            if (num != 0) {
                return atomicIntegerArray.compareAndSet(i, num, num - 1);
            }
            return false;
        }

        public void showTicket(int i) {
            System.out.println("窗口" + i + "还有：" + atomicIntegerArray.get(i) + "票");
        }

        public void showTicket() {
            for (int i = 0; i < atomicIntegerArray.length(); i++) {
                showTicket(i);
            }
        }
    }
}
