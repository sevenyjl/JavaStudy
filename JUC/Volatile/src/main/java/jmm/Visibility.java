package jmm;

import java.util.concurrent.TimeUnit;

/**
 * 验证JMM可见性
 * java中能保证可见性的为volatile
 */
public class Visibility {
    public static void main(String[] args) throws InterruptedException {
    }


    /**
     * 验证volatile保证可见性,当线程A调用完change()方法后修改了num会立刻同步到住内存中
     */
    public static void volatileVisibility() {
        DemoVolatile demoVolatile = new DemoVolatile();
        //创建一个线程a
        Thread a = new Thread(() -> {
            //暂停1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            demoVolatile.change();
            System.out.println("我修改完了，volatileNum=" + demoVolatile.num);
        }, "a");
        a.start();
        while (demoVolatile.num != 10) {

        }
        System.out.println("修改成功");
    }


    /**
     * a线程修改了num,但是由于无可见性，main线程已经读取到了主内存num=0到自己的工作内存中，所以会一直循环，无法感知60已经修改成功了
     */
    public static void error() {
        DemoError demoError = new DemoError();
        //创建一个线程a
        Thread a = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            demoError.change();
            System.out.println("我修改完了，num=" + demoError.num);
        }, "a");
        a.start();
        //暂停1秒
        while (demoError.num != 10) {
        }
        System.out.println("修改成功");
    }


}

class DemoError {
    public int num = 0;

    public void change() {
        num = 10;
    }
}

class DemoVolatile {
    public volatile int num = 0;

    public void change() {
        num = 10;
    }
}
