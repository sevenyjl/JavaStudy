package waitAndNotify;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 铁三角
 * *      sync
 * *       /\
 * *     /   \
 * *   /      \
 * * /_________\
 * wait         notify
 */
public class SyncDemo {
    public static void main(String[] args) {
        errorWhenNotifyThenWait();
    }

    /**
     * 当先唤醒时，再等待，就造成了阻塞
     * 2021-08-14 00:21:48-->	b	闹钟响了 快快起床
     * 2021-08-14 00:21:49-->	a	come in
     */
    private static void errorWhenNotifyThenWait() {
        Object lock = new Object();
        //创建一个线程a
        Thread a = new Thread(() -> {
            //休眠1  SECONDS
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t被唤醒");
            }
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            synchronized (lock) {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t闹钟响了 快快起床");
                lock.notify();
            }
        }, "b");
        b.start();
    }

    /**
     * 当notify不在sync代码里时报错，造成阻塞并报错
     */
    private static void errorWhenNotifyWhitOutSync() {
        Object lock = new Object();
        //创建一个线程a
        Thread a = new Thread(() -> {
            synchronized (lock) {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t被唤醒");
        }, "a");
        a.start();
        //休眠1  SECONDS
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t闹钟响了 快快起床");
            lock.notify();
        }, "b");
        b.start();
    }

    /**
     * 当wait不在同步代码块里时，报错！！
     */
    private static void errorWhenWaitWithOutSync() {
        Object lock = new Object();
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t被唤醒");
        }, "a");
        a.start();
        //休眠1  SECONDS
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //创建一个线程b
        Thread b = new Thread(() -> {
            synchronized (lock) {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t闹钟响了 快快起床");
                lock.notify();
            }
        }, "b");
        b.start();
    }

    /**
     * 正常情况下的代码。todo 看字节码进行分析
     */
    private static void successSynWaitAndNotify() {
        Object lock = new Object();
        //创建一个线程a
        Thread a = new Thread(() -> {
            synchronized (lock) {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t被唤醒");
            }
        }, "a");
        a.start();
        //休眠1  SECONDS
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //创建一个线程b
        Thread b = new Thread(() -> {
            synchronized (lock) {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t闹钟响了 快快起床");
                lock.notify();
            }
        }, "b");
        b.start();
    }
}
