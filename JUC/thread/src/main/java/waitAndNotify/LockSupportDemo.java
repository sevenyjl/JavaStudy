package waitAndNotify;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 两点一线
 * park----unpark
 */
public class LockSupportDemo {
    public static void main(String[] args) {
        justSolveError();
    }

    /**
     * 解决          LockSupport.park();
     * #            LockSupport.park();
     * 问题
     * 2021-08-14 00:35:21-->	a	come in
     * 2021-08-14 00:35:22-->	b	闹钟响了 快快起床
     * 2021-08-14 00:35:23-->	main	闹钟响了 快快起床
     * 2021-08-14 00:35:23-->	a	被唤醒
     */
    private static void justSolveError() {
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            LockSupport.park();
            LockSupport.park();
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
            LockSupport.unpark(a);
            LockSupport.unpark(a);
        }, "b");
        b.start();
        //休眠1  SECONDS
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t闹钟响了 快快起床");
        LockSupport.unpark(a);
    }

    /**
     * 只有一个线程颁发证书（unpark）
     * 但是人家要两张同行证（a线程调用两次park）
     */
    private static void errorWhenOntThtreadUnparkButHave2Park() {
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            LockSupport.park();
            LockSupport.park();
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
            LockSupport.unpark(a);
            LockSupport.unpark(a);
        }, "b");
        b.start();
    }

    private static void success() {
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            LockSupport.park();
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
            LockSupport.unpark(a);
        }, "b");
        b.start();
    }
}
