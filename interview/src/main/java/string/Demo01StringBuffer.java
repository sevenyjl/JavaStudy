package string;

import java.util.concurrent.CountDownLatch;

/**
 * 多线程下的string安全操作
 * 案例：10个线程每个线程，对str字符串进行追加100次字符a，最后稳定输出10*100个a拼接的字符串
 * 结论：
 * 同：
 * 1. StringBuilder和StringBuffer都是可变字符，String为不可变
 * 2. StringBuilder和StringBuffer的默认初始容量是16
 * 异：
 * StringBuffer是线程安全的，底层是在append方法上加入了sync锁
 * StringBuilder是线程不安全的
 */
public class Demo01StringBuffer {
    public static void main(String[] args) {
        errorWithString();
    }

    /**
     * 线程不安全只会输出：979个
     */
    private static void stringBuilderDemo() {
        StringBuilder stringBuilder = new StringBuilder();
        final int threadSize = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    for (int j = 0; j < 100; j++) {
                        stringBuilder.append("a");
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
        System.out.println(stringBuilder.length());
    }

    /**
     * 线程安全，输出：1000个
     */
    private static void stringBufferDemo() {
        StringBuffer stringBuffer = new StringBuffer();
        final int threadSize = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    for (int j = 0; j < 100; j++) {
                        stringBuffer.append("a");
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
        System.out.println(stringBuffer.length());
    }

    static String str = "";

    /**
     * 线程不安全：118
     */
    private static void errorWithString() {
        final int threadSize = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread threadTemp = new Thread(() -> {
                try {
                    for (int j = 0; j < 100; j++) {
                        str += "a";
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
        System.out.println(str.length());
    }
}
