package com.seven.atomic;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 性能比较
 * sync的性能  耗时：7371毫秒
 * {@link java.util.concurrent.atomic.AtomicInteger}的性能 耗时：13553毫秒
 * {@link java.util.concurrent.atomic.LongAdder}的性能 耗时：1564毫秒
 * {@link LongAdderPrinciple} 按照{@link java.util.concurrent.atomic.LongAdder}分而治之思想，做一个自己的简单版   耗时：2832毫秒
 */
public class LongAdderDemo {
    /**
     * longAdder的add方法解析：
     * 当第一个线程进行计算时，由于竞争不激烈，所以对base进行计算赋值。当一堆线程开始并发涌入时，对base进行C A S赋值操作时，可能有线程赋值失败，那么这时，LongAdder就会开始初始化Cells，刚开始初始化cells时的长度为2，然后对cells中的随机元素（是获取线程 probe哈希cells的长度减1）进行C A S 赋值。当线程量继续变大，cells已经不够用了，cells中的元素进行赋值失败了，就需要进行扩容，扩容为原来2倍，最大扩容不大于cpu核数。
     *
     * @param args
     */
    public static void main(String[] args) {
//        addPlusSync();
//        addPlusAtomicInteger();
//        addPlusLongAdder();
//        addPlusMyLongAdder();
    }

    private static void addPlusLongAdder() {
        long startTime = System.currentTimeMillis();
        Resource resource = new Resource();
        final int threadSize = 500;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    for (int j = 0; j < 100 * 10000; j++) {
                        resource.addPlusLongAdder();
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
        System.out.printf("longAdder=" + resource.getLongAdder() + "\n耗时：%s毫秒\t", System.currentTimeMillis() - startTime);
    }

    private static void addPlusSync() {
        long startTime = System.currentTimeMillis();
        Resource resource = new Resource();
        final int threadSize = 500;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    for (int j = 0; j < 100 * 10000; j++) {
                        resource.addPlusSync();
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
        System.out.printf("syncNum=" + resource.getSyncNum() + "\n耗时：%s毫秒\t", System.currentTimeMillis() - startTime);
    }

    private static void addPlusAtomicInteger() {
        long startTime = System.currentTimeMillis();
        Resource resource = new Resource();
        final int threadSize = 500;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    for (int j = 0; j < 100 * 10000; j++) {
                        resource.addPlusAtomicInteger();
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
        System.out.printf("atomicInteger=" + resource.getAtomicInteger() + "\n耗时：%s毫秒\t", System.currentTimeMillis() - startTime);
    }


    private static void addPlusMyLongAdder() {
        long startTime = System.currentTimeMillis();
        Resource resource = new Resource();
        final int threadSize = 500;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    for (int j = 0; j < 100 * 10000; j++) {
                        resource.addPlusMyLongAdder();
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
        System.out.printf("myLongAdder=" + resource.getMyLongAdder() + "\n耗时：%s毫秒\t", System.currentTimeMillis() - startTime);
    }

    public static class Resource {
        private long syncNum = 0;
        private AtomicLong atomicInteger = new AtomicLong(0);
        private LongAdder longAdder = new LongAdder();
        private LongAdderPrinciple myLongAdder = new LongAdderPrinciple();

        public long getMyLongAdder() {
            return myLongAdder.sum();
        }

        public void addPlusMyLongAdder() {
            myLongAdder.increment();
        }

        public long getLongAdder() {
            return longAdder.sum();
        }

        public void addPlusLongAdder() {
            longAdder.increment();
        }

        public long getAtomicInteger() {
            return atomicInteger.get();
        }

        public void addPlusAtomicInteger() {
            atomicInteger.getAndIncrement();
        }

        public long getSyncNum() {
            return syncNum;
        }

        public void addPlusSync() {
            synchronized (LongAdderDemo.class) {
                syncNum++;
            }
        }
    }

    /**
     * 模拟 LongAdder 原理
     */
    public static class LongAdderPrinciple {
        private ConcurrentHashMap<String, AtomicLong> cells = new ConcurrentHashMap<>();

        public void increment() {
            AtomicLong atomicLong = cells.get(Thread.currentThread().getName());
            if (atomicLong == null) {
                atomicLong = new AtomicLong(0);
                cells.put(Thread.currentThread().getName(), atomicLong);
            }
            atomicLong.getAndIncrement();
        }


        public long sum() {
            long result = 0;
            for (AtomicLong value : cells.values()) {
                result += value.get();
            }
            return result;
        }
    }
}
