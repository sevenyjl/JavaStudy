import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 验证volatile的没有原子性
 */
public class VolatileNoAtomicity {

    public static void main(String[] args) {
        原子性();
    }

    private static void 原子性() {
        Resource resource = new Resource();
        for (int i = 0; i < 100; i++) {
            //创建一个线程a
            Thread a = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    resource.addPlus();
                    resource.addPlusVolatile();
                    resource.addPlusSync();
                    resource.addPlusAtomic();
                }
            }, "a");
            a.start();
        }
        //休眠3  SECONDS
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(resource.getNum());//<=10000
        System.out.println(resource.getNumVolatile());//<=10000
        System.out.println(resource.getNumSync());//=10000
        System.out.println(resource.getNumAtomic());//=10000
    }
}

class Resource {
    private int num = 0;
    private volatile int numVolatile = 0;
    private int numSync = 0;
    private final AtomicInteger numAtomic = new AtomicInteger(0);

    public void addPlus() {
        num++;
    }

    public void addPlusVolatile() {
        numVolatile++;
    }

    public int getNumVolatile() {
        return numVolatile;
    }

    public synchronized void addPlusSync() {
        numSync++;
    }

    public void addPlusAtomic() {
        numAtomic.getAndIncrement();
    }

    public int getNum() {
        return num;
    }

    public int getNumSync() {
        return numSync;
    }

    public AtomicInteger getNumAtomic() {
        return numAtomic;
    }
}
