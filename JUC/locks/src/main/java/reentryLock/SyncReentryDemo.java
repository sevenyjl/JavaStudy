package reentryLock;

import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * sync可重入demo
 */
public class SyncReentryDemo {

    public static void main(String[] args) {
        SyncReentryDemo syncReentryDemo = new SyncReentryDemo();
        Object o = new Object();
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + DateUtil.formatDateTime(new Date()) + "\t come int");
            syncReentryDemo.syncWithOtherLock(o);
            System.out.println(Thread.currentThread().getName() + DateUtil.formatDateTime(new Date()) + "\t exit int");
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + DateUtil.formatDateTime(new Date()) + "\t come int");
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + DateUtil.formatDateTime(new Date()) + "\t lock o ------");
            }
            System.out.println(Thread.currentThread().getName() + DateUtil.formatDateTime(new Date()) + "\t exit int");
        }, "b");
        b.start();
    }

    /**
     * a线程验证了sync是可重入锁，如果不可重入，那在外层获取锁并没法释放，但是中层仍然能进入
     * a2021-08-12 17:32:35	 come int
     * b2021-08-12 17:32:35	 come int
     * a2021-08-12 17:32:36	外
     * a2021-08-12 17:32:37	中
     * a2021-08-12 17:32:37	内
     * b2021-08-12 17:32:37	 ------
     * a2021-08-12 17:32:37	 exit int
     * b2021-08-12 17:32:37	 exit int
     *
     * @param syncReentryDemo
     */
    private static void syncReentryDemo01(SyncReentryDemo syncReentryDemo) {
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + DateUtil.formatDateTime(new Date()) + "\t come int");
            syncReentryDemo.syncThis();
            System.out.println(Thread.currentThread().getName() + DateUtil.formatDateTime(new Date()) + "\t exit int");
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + DateUtil.formatDateTime(new Date()) + "\t come int");
            synchronized (syncReentryDemo) {
                System.out.println(Thread.currentThread().getName() + DateUtil.formatDateTime(new Date()) + "\t ------");
            }
            System.out.println(Thread.currentThread().getName() + DateUtil.formatDateTime(new Date()) + "\t exit int");
        }, "b");
        b.start();
    }

    public void syncWithOtherLock(Object lock) {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + DateUtil.formatDateTime(new Date()) + "\t外");
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + DateUtil.formatDateTime(new Date()) + "\t中");
                synchronized (this) {
                    System.out.println(Thread.currentThread().getName() + DateUtil.formatDateTime(new Date()) + "\t内");
                }
            }
        }
    }

    public void syncThis() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + DateUtil.formatDateTime(new Date()) + "\t外");
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + DateUtil.formatDateTime(new Date()) + "\t中");
                synchronized (this) {
                    System.out.println(Thread.currentThread().getName() + DateUtil.formatDateTime(new Date()) + "\t内");
                }
            }
        }
    }
}
