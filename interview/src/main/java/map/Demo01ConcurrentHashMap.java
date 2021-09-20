package map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * 对比ConcurrentHashMap 和 HashMap的多线程安全操作问题
 * ConcurrentHashMap的线程安全实现原理：
 * HashMap的实现原理：
 */
public class Demo01ConcurrentHashMap {
    public static void main(String[] args) {
        Map<Object, Object> objectObjectMap = Collections.synchronizedMap(new HashMap<>());
    }

    private static void successHashTable() {
        Hashtable<String, String> hashtable = new Hashtable<>();
        final int threadSize = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        hashtable.put(Thread.currentThread().getName() + j, "" + j);
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
        System.out.println(hashtable.size());
    }

    /**
     * 线程安全，输出：100
     */
    private static void successConcurrentHashMap() {
        ConcurrentHashMap<String, String> integerStringHashMap = new ConcurrentHashMap<>();
        final int threadSize = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        integerStringHashMap.put(Thread.currentThread().getName() + j, "" + j);
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
        System.out.println(integerStringHashMap.size());
    }

    /**
     * 线程不安全，输出格式：99
     */
    private static void errorHashMap() {
        HashMap<String, String> integerStringHashMap = new HashMap<>();
        final int threadSize = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        integerStringHashMap.put(Thread.currentThread().getName() + j, "" + j);
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
        System.out.println(integerStringHashMap.size());
    }
}
