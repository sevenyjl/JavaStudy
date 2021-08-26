package optionalLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS ABA问题
 */
public class CACABAProblem {

    public static void main(String[] args) {
        solution2();
    }

    private static void solution2() {
        AtomicMarkableReference<Integer> atomicInteger = new AtomicMarkableReference<>(100, false);
        //创建一个线程a
        Thread a = new Thread(() -> {
            boolean marked = atomicInteger.isMarked();
            //休眠1  SECONDS 为了 线程b也获取相同marked
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //这里的 expectedReference 值不得大于127 底层是==  而Integer 两个 1000 对象可能不一样
            boolean b = atomicInteger.compareAndSet(100, 101, marked, true);
            System.out.println(Thread.currentThread().getName() + "\t修改" + b + "，是否已经被修改：" + marked + " 当前值为：" + atomicInteger.getReference());
            //休眠1  SECONDS
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b1 = atomicInteger.compareAndSet(101, 100, false, true);
            System.out.println(Thread.currentThread().getName() + "\t修改" + b1 + "，是否已经被修改：" + atomicInteger.isMarked() + " 当前值为：" + atomicInteger.getReference());
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            boolean marked = atomicInteger.isMarked();
            //休眠3  SECONDS 为了 线程b也获取相同版本，并且a执行修改
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean bo = atomicInteger.compareAndSet(100, 101, marked, true);
            System.out.println(Thread.currentThread().getName() + "\t修改" + bo + "，是否已经被修改：" + marked + " 当前值为：" + atomicInteger.getReference());
        }, "b");
        b.start();
    }

    private static void solution1() {
        AtomicStampedReference<Integer> atomicInteger = new AtomicStampedReference<Integer>(100, 1);
        //创建一个线程a
        Thread a = new Thread(() -> {
            int stamp = atomicInteger.getStamp();
            //休眠1  SECONDS 为了 线程b也获取相同版本
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //这里的 expectedReference 值不得大于127 底层是==  而Integer 两个 1000 对象可能不一样
            boolean b = atomicInteger.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\t修改" + b + "，第" + stamp + "次修改 当前值为：" + atomicInteger.getReference() + "当前版本号为：" + atomicInteger.getStamp());
            //休眠1  SECONDS
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int stamp2 = atomicInteger.getStamp();
            boolean b1 = atomicInteger.compareAndSet(101, 100, stamp2, stamp2 + 1);
            System.out.println(Thread.currentThread().getName() + "\t修改" + b1 + "，第" + stamp2 + "次修改 当前值为：" + atomicInteger.getReference() + "当前版本号为：" + atomicInteger.getStamp());
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            int stamp = atomicInteger.getStamp();
            //休眠3  SECONDS 为了 线程b也获取相同版本，并且a执行修改
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t修改" + atomicInteger.compareAndSet(101, 1024, stamp, stamp + 1) + "，第" + stamp + "次修改 当前值为：" + atomicInteger.getReference() + "当前版本号为：" + atomicInteger.getStamp());
        }, "b");
        b.start();
    }

    private static void problem() {
        AtomicInteger atomicInteger = new AtomicInteger(100);
        //创建一个线程a
        Thread a = new Thread(() -> {
            atomicInteger.compareAndExchange(100, 1000);
            System.out.println(Thread.currentThread().getName() + "\t修改成功，当前值为：" + atomicInteger.get());
            //休眠1  SECONDS
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicInteger.compareAndExchange(1000, 100);
            System.out.println(Thread.currentThread().getName() + "\t修改成功，当前值为：" + atomicInteger.get());
        }, "a");
        a.start();
        //休眠3  SECONDS
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //创建一个线程b
        Thread b = new Thread(() -> {
            atomicInteger.compareAndExchange(100, 2014);
            System.out.println(Thread.currentThread().getName() + "\t修改成功，当前值为：" + atomicInteger.get());
        }, "b");
        b.start();
    }
}
