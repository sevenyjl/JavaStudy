package reference;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 强引用案例：死也不放手（oom我也不会释放）
 * 为了演示方便，设置堆内存大小10m ，jvm参数为：-Xmx10m -Xms10m
 * <p>
 * gc回收前：reference.StrongReferenceDemo@380fb434
 * gc回收后：reference.StrongReferenceDemo@380fb434
 * Exception in thread "a" java.lang.OutOfMemoryError: Java heap space
 * at reference.StrongReferenceDemo.lambda$init$0(StrongReferenceDemo.java:49)
 * at reference.StrongReferenceDemo$$Lambda$14/0x0000000100066c40.run(Unknown Source)
 * at java.base/java.lang.Thread.run(Thread.java:829)
 * 11s后：reference.StrongReferenceDemo@380fb434
 */
public class StrongReferenceDemo {
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

    public static void main(String[] args) {
        StrongReferenceDemo strongReferenceDemo = new StrongReferenceDemo();
        System.out.println("gc回收前：" + strongReferenceDemo);
        System.gc();
        System.out.println("gc回收后：" + strongReferenceDemo);

        //休眠11  SECONDS，等待内存满
        try {
            TimeUnit.SECONDS.sleep(11);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("11s后：" + strongReferenceDemo);
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
