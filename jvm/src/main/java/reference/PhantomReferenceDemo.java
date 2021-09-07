package reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 虚引用：形同虚设，new了也白搭。必须和        new PhantomReference<>()搭配使用
 * <p>
 * gc回收前：null
 * gc回收前队列：null
 * 调用gc
 * gc invoke  对象将要销毁
 * gc回收后：null
 * gc回收后队列：null
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<PhantomReferenceDemo> referenceQueue = new ReferenceQueue<>();
        PhantomReference<PhantomReferenceDemo> phantomReference = new PhantomReference<>(new PhantomReferenceDemo(), referenceQueue);
        System.out.println("gc回收前：" + phantomReference.get());
        System.out.println("gc回收前队列：" + referenceQueue.poll());
        System.out.println("调用gc");
        System.gc();
        //休眠1  SECONDS
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("gc回收后：" + phantomReference.get());
        System.out.println("gc回收后队列：" + referenceQueue.poll());
        //休眠11  SECONDS
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("oom内存不够时：softReference=" + phantomReference + ",引用对象：" + phantomReference.get());
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
