package reference;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 软引用：内存不够就是释放
 * 为了演示方便，设置堆内存大小10m ，jvm参数为：-Xmx10m -Xms10m
 * <p>
 * gc回收前：reference.StrongReferenceDemo@3cda1055
 * gc回收后：reference.StrongReferenceDemo@3cda1055
 * Exception in thread "a" java.lang.OutOfMemoryError: Java heap space
 * at reference.SoftReferenceDemo.lambda$init$0(SoftReferenceDemo.java:53)
 * at reference.SoftReferenceDemo$$Lambda$14/0x0000000100066c40.run(Unknown Source)
 * at java.base/java.lang.Thread.run(Thread.java:829)
 * gc invoke  对象将要销毁
 * Exception in thread "a" java.lang.OutOfMemoryError: Java heap space
 * at reference.StrongReferenceDemo.lambda$init$0(StrongReferenceDemo.java:59)
 * at reference.StrongReferenceDemo$$Lambda$15/0x0000000100066040.run(Unknown Source)
 * at java.base/java.lang.Thread.run(Thread.java:829)
 * oom内存不够时：softReference=java.lang.ref.SoftReference@6fb554cc,引用对象：null
 */
public class SoftReferenceDemo {


    public static void main(String[] args) {
        SoftReference<StrongReferenceDemo> softReference = new SoftReference<>(new StrongReferenceDemo());
        System.out.println("gc回收前：" + softReference.get());
        System.gc();
        System.out.println("gc回收后：" + softReference.get());
        //休眠11  SECONDS
        try {
            TimeUnit.SECONDS.sleep(11);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("oom内存不够时：softReference=" + softReference + ",引用对象：" + softReference.get());
    }

    /**
     * 仅仅时拥有查看gc是否回收
     *
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("gc invoke  对象将要销毁");
        ;
    }

    static volatile boolean flag = true;

    static {
        init();
    }

    /**
     * 每1秒写入一个1m大小对象
     */
    public static void init() {
        //创建一个线程a
        Thread a = new Thread(() -> {
            ArrayList<byte[]> bytes = new ArrayList<>();
            while (flag) {
                bytes.add(new byte[1 * 1024 * 1024]);
                //休眠1  SECONDS
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "a");
        a.start();
    }
}
