import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileSeeDemo3 {
    public static void main(String[] args) {
        Resource3 resource3 = new Resource3();
        //创建一个线程a
        Thread a = new Thread(() -> {
            //休眠2  SECONDS
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource3.setNum(1);
            resource3.setNumVolatile(1);
            resource3.setNumSync(1);
            resource3.getNumAtomic().set(1);
            System.out.println(Thread.currentThread().getName() + "\t修改完成");
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
            while (resource3.getNum() != 1) {
            }
            System.out.println(Thread.currentThread().getName() + "\t检测到了修改");
        }, "b");
        b.start();

        //创建一个线程c
        Thread c = new Thread(() -> {
            while (resource3.getNumVolatile() != 1) {
            }
            System.out.println(Thread.currentThread().getName() + "\t检测到了修改");
        }, "c");
        c.start();
        //创建一个线程d
        Thread d = new Thread(() -> {
            while (resource3.getNumSync() != 1) {
            }
            System.out.println(Thread.currentThread().getName() + "\t检测到了修改");
        }, "d");
        d.start();
        //创建一个线程e
        Thread e = new Thread(() -> {
            while (resource3.getNumAtomic().get() != 1) {
            }
            System.out.println(Thread.currentThread().getName() + "\t检测到了修改");
        }, "e");
        e.start();
    }
}

class Resource3 {
    private int num = 0;
    private volatile int numVolatile = 0;
    private int numSync = 0;
    private final AtomicInteger numAtomic = new AtomicInteger(0);

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public synchronized int getNumVolatile() {
        return numVolatile;
    }

    public synchronized void setNumVolatile(int numVolatile) {
        this.numVolatile = numVolatile;
    }

    public int getNumSync() {
        return numSync;
    }

    public void setNumSync(int numSync) {
        this.numSync = numSync;
    }

    public AtomicInteger getNumAtomic() {
        return numAtomic;
    }
}
