package com.seven.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * bool 原子类别，封装了原子类 类似 AtomicReference<Boolean>
 * 但是 这个 AtomicBoolean 底层是 volatile int value--->1=true 0=false
 * 做一个多线程 自选锁demo
 */
public class AtomicBooleanDemo {
    AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public void lock() {
        //【注意】compareAndSet和compareAndExchange是不一样的
        /*

          auth: seven
          date: 2021/8/27 14:12
         */
        while (!atomicBoolean.compareAndSet(false, true)) {

        }
    }

    public void unlock() {
        while (!atomicBoolean.compareAndSet(true, false)) {

        }
    }

    public static void main(String[] args) {
        AtomicBooleanDemo atomicBooleanDemo = new AtomicBooleanDemo();
        //创建一个线程a
        Thread a = new Thread(() -> {
            atomicBooleanDemo.lock();
            System.out.println(Thread.currentThread().getName() + "\tcome in");
            //休眠3  SECONDS
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\tout");
            atomicBooleanDemo.unlock();
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
            atomicBooleanDemo.lock();
            System.out.println(Thread.currentThread().getName() + "\tcome in");
            System.out.println(Thread.currentThread().getName() + "\tout");
            atomicBooleanDemo.unlock();
        }, "b");
        b.start();
    }
}
