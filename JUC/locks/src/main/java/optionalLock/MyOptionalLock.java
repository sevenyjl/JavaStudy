package optionalLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 手写自旋锁
 * <p>
 * auth: seven
 * date: 2021/8/26 22:12
 */
public class MyOptionalLock {

    AtomicReference<Thread> current = new AtomicReference<>();

    public void lock() {
        while (!current.compareAndSet(null, Thread.currentThread())) {

        }
    }

    public void unlock() {
        while (!current.compareAndSet(Thread.currentThread(), null)) {

        }
    }

    public static void main(String[] args) {
        MyOptionalLock myOptionalLock = new MyOptionalLock();
        //创建一个线程a
        Thread a = new Thread(() -> {
            myOptionalLock.lock();
            System.out.println(Thread.currentThread().getName() + "\t进入了");
            //休眠3  SECONDS
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t出去了");
            myOptionalLock.unlock();
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
            myOptionalLock.lock();
            System.out.println(Thread.currentThread().getName() + "\t进入了");
            //休眠3  SECONDS
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t出去了");
            myOptionalLock.unlock();
        }, "b");
        b.start();

    }
}
