package com.seven.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 版本号的引用原子类，拥有解决ABA问题
 * 案例：
 * 一瓶水500ml，喝一口就少100ml，现在a同学喝了第一口，但是因为有事出去了一趟，他希望没有人喝过他的水，还是原来的400ml。
 * 但是b同学很讨厌，喝了一口，又怕a同学发现，有到了100ml的自来水进去。。。
 * 那么a同学回来还是看见400ml，没人动，拿起水咕咕喝下，结果拉肚子了。。。
 * 解决：
 * a利用摄像头(其他标记手法)，去查看是否有人动过这个水，动过就不要了
 */
public class AtomicStampedReferenceDemo {
    public static void main(String[] args) {
        solutionABA();
    }

    public static void solutionABA() {
        Integer integer500 = 500;
        Integer integer400 = 400;
        Integer integer300 = 300;
        AtomicStampedReference<Integer> water = new AtomicStampedReference<Integer>(integer500, 0);
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "喝了一口");
            boolean b = water.compareAndSet(integer500, integer400, 0, 1);
            System.out.println(Thread.currentThread().getName() + "喝水：" + b + ",还剩余：" + water.getReference() + "，并标记：" + water.getStamp());
            System.out.println(Thread.currentThread().getName() + "出去了一下");
            //休眠2  SECONDS
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b = water.compareAndSet(integer400, integer300, 1, 2);
            if (b) {
                System.out.println(Thread.currentThread().getName() + "喝水：" + b + ",还剩余：" + water.getReference() + "，并标记：" + water.getStamp());
            } else {
                System.out.println(Thread.currentThread().getName() + "查看到标记有人动过，扔了不喝！");
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
            System.out.println(Thread.currentThread().getName() + "喝了一口");
            System.out.println(Thread.currentThread().getName() + "喝水：" + water.compareAndSet(integer400, integer300, 1, 2) + ",还剩余：" + water.getReference() + "，并标记：" + water.getStamp());
            System.out.println(Thread.currentThread().getName() + "做贼心虚~~  开始灌自来水");
            System.out.println(Thread.currentThread().getName() + "灌水：" + water.compareAndSet(integer300, integer400, 2, 3) + ",还剩余：" + water.getReference() + "，并标记：" + water.getStamp());
        }, "b");
        b.start();
    }

    public static void aba() {
        Integer integer500 = 500;
        Integer integer400 = 400;
        Integer integer300 = 300;
        AtomicReference<Integer> water = new AtomicReference<>(integer500);
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "喝了一口");
            boolean b = water.compareAndSet(integer500, integer400);
            System.out.println(Thread.currentThread().getName() + "喝水：" + b + ",还剩余：" + water.get());
            System.out.println(Thread.currentThread().getName() + "出去了一下");
            //休眠2  SECONDS
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "喝水：" + water.compareAndSet(integer400, integer300) + ",还剩余：" + water.get());
            System.out.println(Thread.currentThread().getName() + "拉肚子了。。。。");
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
            System.out.println(Thread.currentThread().getName() + "喝了一口");
            System.out.println(Thread.currentThread().getName() + "喝水：" + water.compareAndSet(integer400, integer300) + ",还剩余：" + water.get());
            System.out.println(Thread.currentThread().getName() + "做贼心虚~~  开始灌自来水");
            System.out.println(Thread.currentThread().getName() + "灌水：" + water.compareAndSet(integer300, integer400) + ",还剩余：" + water.get());
        }, "b");
        b.start();
    }

    /**
     * 注意这里integer有坑！！！
     */
    public static void abaButHaveIntegerProblem() {
        AtomicReference<Integer> water = new AtomicReference<>(500);
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "喝了一口");
            boolean b = water.compareAndSet(500, 500 - 100);
            System.out.println(Thread.currentThread().getName() + "喝水：" + b + ",还剩余：" + water.get());
            System.out.println(Thread.currentThread().getName() + "出去了一下");
            //休眠2  SECONDS
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "喝水：" + water.compareAndSet(400, 400 - 100) + ",还剩余：" + water.get());
            System.out.println(Thread.currentThread().getName() + "拉肚子了。。。。");
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
            System.out.println(Thread.currentThread().getName() + "喝了一口");
            System.out.println(Thread.currentThread().getName() + "喝水：" + water.compareAndSet(400, 400 - 100) + ",还剩余：" + water.get());
            System.out.println(Thread.currentThread().getName() + "做贼心虚~~  开始灌自来水");
            System.out.println(Thread.currentThread().getName() + "灌水：" + water.compareAndSet(300, 300 - 100) + ",还剩余：" + water.get());
        }, "b");
        b.start();
    }
}
