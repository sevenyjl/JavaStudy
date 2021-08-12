package reentryLock;

import cn.hutool.core.date.DateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * sync可重入demo
 * 总结
 * 生活案例：
 * 我用我的指纹开启了我家的门，然后又用我的指纹开启了我的卧室，我并没有关闭/释放我家的门的锁，拿了卧室的手机急急忙忙出去了。（我家的门和我的卧室，是同一把锁我的指纹都可打开这就是可重入）
 * 我用我的指纹开启了我家的门，然后又用去开启我爸妈的房间卧室发现开启不了，需要去获取他们的钥匙，于是我就去尝试寻找爸妈他们卧室的钥匙。（这就是内部中的锁不一样，无可重入性）
 * 一句话：sync可以不许释放已经持有锁对象的锁，而再次进入锁的代码块或者方法中
 */
public class SyncReentryDemo {

    public static void main(String[] args) {
        SyncReentryDemo syncReentryDemo = new SyncReentryDemo();
        //创建一个线程a
        Thread a = new Thread(() -> {
            synchronized (syncReentryDemo) {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t1");
                synchronized (SyncReentryDemo.class) {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t2");
                    synchronized (syncReentryDemo) {
                        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t3");
                        synchronized (SyncReentryDemo.class) {
                            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t4");
                        }
                    }
                }
            }
        }, "a");
        a.start();
    }

    /**
     * 可重入中的中间锁为其他人的锁时，就需要抢锁了
     *
     * @param syncReentryDemo
     */
    private static void syncReentryWithOtherLock(SyncReentryDemo syncReentryDemo) {
        Object o = new Object();
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            syncReentryDemo.syncWithOtherLock(o);
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\texit");
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            synchronized (o) {
                //休眠10秒
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + DateUtil.formatDateTime(new Date()) + "\t lock o ------");
            }
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\texit");
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
            System.out.println(DateUtil.formatDateTime(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t外");
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                System.out.println(DateUtil.formatDateTime(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t中");
                synchronized (this) {
                    System.out.println(DateUtil.formatDateTime(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t内");
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
