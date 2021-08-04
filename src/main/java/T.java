import org.w3c.dom.Node;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class T {
    private static volatile int flag = 0;

    public static void main(String[] args) {
        //创建一个线程A
        Thread A = new Thread(() -> {
            synchronized (T.class) {
                while (true) {
                    if (flag == 0) {
                        System.out.println("A");
                        flag = 1;
                    } else {
                        //休眠1秒
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "A");
        A.start();
        //创建一个线程B
        Thread B = new Thread(() -> {
            while (true) {
                if (flag == 1) {
                    System.out.println("B");
                    flag = 2;
                } else {
                    //休眠1秒
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "B");
        B.start();
        //创建一个线程C
        Thread C = new Thread(() -> {
            while (true) {
                if (flag == 2) {
                    System.out.println("C");
                    flag = 0;
                } else {
                    //休眠1秒
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "C");
        C.start();
    }
}
