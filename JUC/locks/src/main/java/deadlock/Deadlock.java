package deadlock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * demo01:写一个死锁
 */
public class Deadlock {

    public static void main(String[] args) {
    }

    /**
     * sync导致的死锁
     */
    private static void demo01DeadLockBySync() {
        Object lock1 = new Object();
        Object lock2 = new Object();
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            synchronized (lock1) {
                //休眠1秒
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {

                }
            }
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\texit in");
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            synchronized (lock2) {
                synchronized (lock1) {

                }
            }
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\texit in");
        }, "b");
        b.start();
    }

    /**
     * ReentrantLock导致的死锁
     */
    private static void demo01deadLockByReentrantLock() {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            lock1.lock();
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock2.lock();
            lock2.unlock();
            lock1.unlock();
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\texit in");
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            lock2.lock();
            lock1.lock();
            lock1.unlock();
            lock2.unlock();
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\texit in");
        }, "b");
        b.start();
    }
}
