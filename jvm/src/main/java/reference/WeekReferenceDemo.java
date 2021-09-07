package reference;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 虚引用：一调gs就回收
 * 为了演示方便，设置堆内存大小10m ，jvm参数为：-Xmx10m -Xms10m
 * <p>
 * gc回收前：reference.WeekReferenceDemo@380fb434
 * gc invoke  对象将要销毁
 * gc回收后：null
 * Exception in thread "a" java.lang.OutOfMemoryError: Java heap space
 * at reference.WeekReferenceDemo.lambda$init$0(WeekReferenceDemo.java:52)
 * at reference.WeekReferenceDemo$$Lambda$14/0x0000000100066c40.run(Unknown Source)
 * at java.base/java.lang.Thread.run(Thread.java:829)
 * oom内存不够时：softReference=java.lang.ref.WeakReference@3f2a3a5,引用对象：null
 */
public class WeekReferenceDemo {

    public static void main(String[] args) {
        WeakReference<WeekReferenceDemo> weakReference = new WeakReference<>(new WeekReferenceDemo());
        System.out.println("gc回收前：" + weakReference.get());
        System.gc();
        System.out.println("gc回收后：" + weakReference.get());
        //休眠11  SECONDS
        try {
            TimeUnit.SECONDS.sleep(11);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("oom内存不够时：softReference=" + weakReference + ",引用对象：" + weakReference.get());
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
