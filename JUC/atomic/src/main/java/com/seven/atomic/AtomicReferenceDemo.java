package com.seven.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 引用原子类，拥有自定义对象的原子操作，底层是比较对象地址值
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReferenceDemo atomicReferenceDemo = new AtomicReferenceDemo(1);
        AtomicReference<AtomicReferenceDemo> atomicReference = new AtomicReference<>(atomicReferenceDemo);
        System.out.println("同hash和equals=" + atomicReference.compareAndSet(new AtomicReferenceDemo(1), new AtomicReferenceDemo(2)));
        System.out.println("同一个对象（同地址值）=" + atomicReference.compareAndSet(atomicReferenceDemo, new AtomicReferenceDemo(2)));
    }

    private int i;

    public AtomicReferenceDemo(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtomicReferenceDemo that = (AtomicReferenceDemo) o;
        return i == that.i;
    }

    @Override
    public int hashCode() {
        return i;
    }
}
