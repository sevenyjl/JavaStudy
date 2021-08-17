import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 验证volatile的可见性
 */
public class VolatileSeeDemo {
    static volatile boolean v1 = true;
    static boolean v2 = true;
    static boolean flag = true;       //不加volatile，没有可见性

    //static volatile boolean flag = true;       //加了volatile，保证可见性

    /**
     * 方法中不建议有任何代码，因为可能会导致v2的值 重新从主内存中获取
     */
    public static void justMethod() {

    }

    /**
     * 只是对比justMethod 来结论 sync可以有可见性
     */
    public static synchronized void justSyncMethod() {
    }

    public static void main(String[] args) {
        seeDemo();
    }

    /**
     * 2021-08-17 23:36:58.000304-->	c	come in
     * 2021-08-17 23:36:58.000304-->	b	come in
     * 2021-08-17 23:36:58.000304-->	a	come in
     * 2021-08-17 23:36:58.000322-->	a	读取变量：true
     * 2021-08-17 23:36:58.000336-->	a	修改变量：true为false
     * 2021-08-17 23:36:59.000346-->	c	out
     * 2021-08-17 23:36:59.000346-->	a	修改完成后的值：false
     * 2021-08-17 23:37:00.000346-->	a	exit
     */
    private static void errorDemo() {
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t读取变量：" + v2);
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t修改变量：" + v2 + "为false");
            //休眠1  SECONDS 休息1秒 模拟计算
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            v2 = false;
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t修改完成后的值：" + v2);
            //休眠1  SECONDS 休息1秒 给线程b留点时间
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\texit");
        }, "a");
        a.start();

        //创建一个线程b
        Thread c = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            while (v2) {
                justSyncMethod();
            }
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tout");
        }, "c");
        c.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            while (v2) {
                justMethod();
            }
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tout");
        }, "b");
        b.start();
    }

    /**
     * 2 021-08-17 23:40:41.000511-->	b	come in
     * 2021-08-17 23:40:41.000511-->	a	come in
     * 2021-08-17 23:40:41.000528-->	a	读取变量：true
     * 2021-08-17 23:40:41.000544-->	a	修改变量：true为 false
     * 2021-08-17 23:40:42.000552-->	b	out
     * 2021-08-17 23:40:42.000552-->	a	修改完成后的值：false
     */
    private static void seeDemo() {
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t读取变量：" + v1);
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t修改变量：" + v1 + "为 false");
            //模拟计算 休眠1  SECONDS
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            v1 = false;
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t修改完成后的值：" + v1);
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            while (v1) {
            }
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tout");
        }, "b");
        b.start();
    }
}
