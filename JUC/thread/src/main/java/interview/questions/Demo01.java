package interview.questions;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 三个线程轮换打印ABCABC...
 */
public class Demo01 {

    static Thread a = null;
    static Thread b = null;
    static Thread c = null;
    static volatile int flag = 0;

    public static void main(String[] args) {
    }

    /**
     * 使用sync+wait+notify来实现
     */
    private static void useBySyncWaitNotify() {
        Object locka = new Object();
        Object lockb = new Object();
        Object lockc = new Object();
        //创建一个线程a
        Thread a = new Thread(() -> {
            while (true) {
                synchronized (locka) {
                    synchronized (lockb) {
                        System.out.println("A");
                        lockb.notify();
                    }
                    try {
                        locka.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            while (true) {
                synchronized (lockb) {
                    synchronized (lockc) {
                        System.out.println("B");
                        lockc.notify();
                    }
                    try {
                        lockb.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "b");
        b.start();
        //创建一个线程c
        Thread c = new Thread(() -> {
            while (true) {
                synchronized (lockc) {
                    synchronized (locka) {
                        System.out.println("C");
                        locka.notify();
                    }
                    try {
                        lockc.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "c");
        c.start();
    }

    /**
     * 使用volatile关键字来控制
     */
    private static void useByVolatileFlag() {
        //创建一个线程a
        Thread a = new Thread(() -> {
            while (true) {
                if (flag == 0) {
                    System.out.println("a");
                    flag = 1;
                }
            }
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            while (true) {
                if (flag == 1) {
                    System.out.println("b");
                    flag = 2;
                }
            }

        }, "b");
        b.start();
        //创建一个线程c
        Thread c = new Thread(() -> {
            while (true) {
                if (flag == 2) {
                    System.out.println("c");
                    flag = 0;
                }
            }
        }, "c");
        c.start();
    }

    /**
     * 使用LockSupport来控制
     */
    private static void userByLockSupport() {
        //创建一个线程a
        a = new Thread(() -> {
            while (true) {
                System.out.println("A");
                LockSupport.unpark(b);
                LockSupport.park();
            }
        }, "a");
        a.start();
        //休眠100  MILLISECONDS
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //创建一个线程b
        b = new Thread(() -> {
            while (true) {
                System.out.println("B");
                LockSupport.unpark(c);
                LockSupport.park();
            }
        }, "b");
        b.start();
        //休眠100  MILLISECONDS
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //创建一个线程c
        c = new Thread(() -> {
            while (true) {
                System.out.println("C");
                LockSupport.unpark(a);
                LockSupport.park();
            }
        }, "c");
        c.start();
    }
}
