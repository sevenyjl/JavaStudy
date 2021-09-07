package list;

import cn.hutool.core.util.RandomUtil;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.function.Predicate;

/**
 * 多线程下对list的读、写操作
 */
public class Demo01CopyOnWriteArrayList {


    public static void main(String[] args) {
        readAndWriteSuccess();
    }

    private static void readAndWriteSuccess() {
        CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();
        final int threadSize = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    for (int j = 0; j < 100; j++) {
                        strings.add(j + "");
                        System.out.println(Thread.currentThread().getName() + "\t" + strings);
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
        System.out.println(strings.size());
    }

    /**
     * Exception in thread "thread 4" java.util.ConcurrentModificationException
     */
    private static void readAndWriteError() {
        ArrayList<String> strings = new ArrayList<>();
        final int threadSize = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    for (int j = 0; j < 100; j++) {
                        strings.add(j + "");
                        System.out.println(Thread.currentThread().getName() + "\t" + strings);
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
        System.out.println(strings.size());
    }

    /**
     * 线程不安全：729
     */
    private static void arrayError01() {
        ArrayList<String> strings = new ArrayList<>();
        final int threadSize = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    for (int j = 0; j < 100; j++) {
                        strings.add(j + "");
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
        System.out.println(strings.size());
    }

    /**
     * 线程安全：1000
     */
    private static void successCopyOnWriteArrayListDemo() {
        CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();
        final int threadSize = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    for (int j = 0; j < 100; j++) {
                        strings.add(j + "");
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
        System.out.println(strings.size());
    }
}
