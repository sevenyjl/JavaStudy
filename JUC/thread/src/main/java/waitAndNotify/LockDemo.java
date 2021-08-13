package waitAndNotify;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 铁三角
 * *   lock的condition
 * *       /\
 * *     /   \
 * *   /      \
 * * /_________\
 * await         signal
 */
public class LockDemo {
    public static void main(String[] args) {
        //更多参考 @See SyncDemo
    }

    /**
     * 正确案例
     */
    private static void success() {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        //创建一个线程a
        Thread a = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
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
            lock.lock();
            try {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t闹钟响了 快快起床");
                condition.signal();
            } finally {
                lock.unlock();
            }
        }, "b");
        b.start();
    }
}
