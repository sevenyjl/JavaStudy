package com.seven.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * {@link AtomicMarkableReference} 和 {@link AtomicStampedReference}的区别
 *
 * @see AtomicMarkableReference: 主要表达，是否修改过，利用isMark(true 和 fals)来记录对象是否被动过。典型生活案例：任何一次性东西（筷子）
 * @see AtomicStampedReference: 主要表达，版本此时，可以被动用几次并记录。典型生活案例：迭代版本号
 */
public class AtomicMarkableReferenceDemo {

    public static void main(String[] args) {
        Resource markable = new Resource("A");
        AtomicMarkableReference<Resource> resourceAtomicMarkableReference = new AtomicMarkableReference<Resource>(markable, false);
        Resource stamped = new Resource("A");
        AtomicStampedReference<Resource> resourceAtomicStampedReference = new AtomicStampedReference<Resource>(stamped, 0);
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "第一次修改markable:" + resourceAtomicMarkableReference.compareAndSet(markable, new Resource("B"), false, true));
            System.out.println(Thread.currentThread().getName() + "第一次修改markable:" + resourceAtomicStampedReference.compareAndSet(stamped, new Resource("B"), 0, 1));
        }, "a");
        a.start();
        //休眠1  SECONDS
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //创建一个线程b
        Thread b=new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "第一次修改markable:" + resourceAtomicMarkableReference.compareAndSet(markable, new Resource("C"), false, true));
            System.out.println(Thread.currentThread().getName() + "第一次修改markable:" + resourceAtomicStampedReference.compareAndSet(stamped, new Resource("C"), 0, 1));
        },"b");
        b.start();
    }

    public static class Resource {
        private String name;

        public Resource(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Resource{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
