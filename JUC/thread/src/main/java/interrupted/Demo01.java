package interrupted;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * thread的interrupt相关demo
 */
public class Demo01 {
    public static void main(String[] args) {
        interruptedDemo05();
    }

    /**
     * 结论：Thread.interrupted()做了两件事：1 返回当前线程的中断状态 2 将当前线程的中断状态设为false
     * <p>
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 0
     * 2021-08-13 17:18:53-->	我试图打断main
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 1
     * 返回当前线程的打断状态：true
     * 2021-08-13 17:18:53-->	a	print 2
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 3
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 4
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 5
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 6
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 7
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 8
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 9
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 10
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 11
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 12
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 13
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 14
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 15
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 16
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 17
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 18
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 19
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 20
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 21
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 22
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 23
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 24
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 25
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 26
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 27
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 28
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 29
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 30
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 31
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 32
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 33
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 34
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 35
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 36
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 37
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 38
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 39
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 40
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 41
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 42
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 43
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 44
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 45
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 46
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 47
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 48
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 49
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 50
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 51
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 52
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 53
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 54
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 55
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 56
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 57
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 58
     * 返回当前线程的打断状态：false
     * 2021-08-13 17:18:53-->	a	print 59
     */
    private static void interruptedDemo05() {
        //创建一个线程a
        Thread a = new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                System.out.println("返回当前线程的打断状态：" + Thread.interrupted());
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tprint " + i);
            }
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            //线程b调用interrupt试图打断a线程
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + "我试图打断" + a.getThreadGroup().getName());
            a.interrupt();
        }, "b");
        b.start();
    }

    /**
     * 结论：在InterruptedException一定要自我了结Thread.currentThread().interrupt();
     * 2021-08-13 17:15:09-->	a	print 0
     * 2021-08-13 17:15:09-->	a	print 1
     * 2021-08-13 17:15:09-->	a	print 2
     * 2021-08-13 17:15:09-->	a	print 3
     * 2021-08-13 17:15:09-->	我试图打断main
     * 他妈 吵醒我睡了！！ 我不工作了！！
     * 2021-08-13 17:15:09-->	靠 我听你的我停止
     * java.lang.InterruptedException: sleep interrupted
     * at java.base/java.lang.Thread.sleep(Native Method)
     * at java.base/java.lang.Thread.sleep(Thread.java:339)
     * at java.base/java.util.concurrent.TimeUnit.sleep(TimeUnit.java:446)
     * at interrupted.Demo01.lambda$main$0(Demo01.java:17)
     * at java.base/java.lang.Thread.run(Thread.java:834)
     */
    private static void interruptedDemo04() {
        //创建一个线程a
        Thread a = new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                //休眠100  MILLISECONDS
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("他妈 吵醒我睡了！！ 我不工作了！！");
                    Thread.currentThread().interrupt();
                }
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t靠 我听你的我停止");
                    break;
                } else {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tprint " + i);
                }
            }
        }, "a");
        a.start();
        //休眠500  MILLISECONDS
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //创建一个线程b
        Thread b = new Thread(() -> {
            //线程b调用interrupt试图打断a线程
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + "我试图打断" + a.getThreadGroup().getName());
            a.interrupt();
        }, "b");
        b.start();
    }

    /**
     * 在线程a中加入休眠后，发现不能打断了。。他妈异常了
     * 2021-08-13 17:13:45-->	a	print 0
     * 2021-08-13 17:13:45-->	a	print 1
     * 2021-08-13 17:13:46-->	a	print 2
     * 2021-08-13 17:13:46-->	a	print 3
     * 2021-08-13 17:13:46-->	我试图打断main
     * 2021-08-13 17:13:46-->	a	print 4
     * java.lang.InterruptedException: sleep interrupted
     * at java.base/java.lang.Thread.sleep(Native Method)
     * at java.base/java.lang.Thread.sleep(Thread.java:339)
     * at java.base/java.util.concurrent.TimeUnit.sleep(TimeUnit.java:446)
     * at interrupted.Demo01.lambda$interruptedDemo03$0(Demo01.java:24)
     * at java.base/java.lang.Thread.run(Thread.java:834)
     * 2021-08-13 17:13:46-->	a	print 5
     * 2021-08-13 17:13:46-->	a	print 6
     * 2021-08-13 17:13:46-->	a	print 7
     * 2021-08-13 17:13:46-->	a	print 8
     * 2021-08-13 17:13:46-->	a	print 9
     * 2021-08-13 17:13:46-->	a	print 10
     * 2021-08-13 17:13:46-->	a	print 11
     * 2021-08-13 17:13:47-->	a	print 12
     * 2021-08-13 17:13:47-->	a	print 13
     * 2021-08-13 17:13:47-->	a	print 14
     * 2021-08-13 17:13:47-->	a	print 15
     * 2021-08-13 17:13:47-->	a	print 16
     * 2021-08-13 17:13:47-->	a	print 17
     * 2021-08-13 17:13:47-->	a	print 18
     * 2021-08-13 17:13:47-->	a	print 19
     * 2021-08-13 17:13:47-->	a	print 20
     * 2021-08-13 17:13:47-->	a	print 21
     * 2021-08-13 17:13:48-->	a	print 22
     * 2021-08-13 17:13:48-->	a	print 23
     * 2021-08-13 17:13:48-->	a	print 24
     * 2021-08-13 17:13:48-->	a	print 25
     * 2021-08-13 17:13:48-->	a	print 26
     * 2021-08-13 17:13:48-->	a	print 27
     * 2021-08-13 17:13:48-->	a	print 28
     * 2021-08-13 17:13:48-->	a	print 29
     * 2021-08-13 17:13:48-->	a	print 30
     * 2021-08-13 17:13:49-->	a	print 31
     * 2021-08-13 17:13:49-->	a	print 32
     * 2021-08-13 17:13:49-->	a	print 33
     * 2021-08-13 17:13:49-->	a	print 34
     * 2021-08-13 17:13:49-->	a	print 35
     * 2021-08-13 17:13:49-->	a	print 36
     * 2021-08-13 17:13:49-->	a	print 37
     * 2021-08-13 17:13:49-->	a	print 38
     * 2021-08-13 17:13:49-->	a	print 39
     * 2021-08-13 17:13:50-->	a	print 40
     * 2021-08-13 17:13:50-->	a	print 41
     * 2021-08-13 17:13:50-->	a	print 42
     * 2021-08-13 17:13:50-->	a	print 43
     * 2021-08-13 17:13:50-->	a	print 44
     * 2021-08-13 17:13:50-->	a	print 45
     * 2021-08-13 17:13:50-->	a	print 46
     * 2021-08-13 17:13:50-->	a	print 47
     * 2021-08-13 17:13:50-->	a	print 48
     * 2021-08-13 17:13:51-->	a	print 49
     * 2021-08-13 17:13:51-->	a	print 50
     * 2021-08-13 17:13:51-->	a	print 51
     * 2021-08-13 17:13:51-->	a	print 52
     * 2021-08-13 17:13:51-->	a	print 53
     * 2021-08-13 17:13:51-->	a	print 54
     * 2021-08-13 17:13:51-->	a	print 55
     * 2021-08-13 17:13:51-->	a	print 56
     * 2021-08-13 17:13:51-->	a	print 57
     * 2021-08-13 17:13:52-->	a	print 58
     * 2021-08-13 17:13:52-->	a	print 59
     */
    private static void interruptedDemo03() {
        //创建一个线程a
        Thread a = new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                //休眠100  MILLISECONDS
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t靠 我听你的我停止");
                    break;
                } else {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tprint " + i);
                }
            }
        }, "a");
        a.start();
        //休眠500  MILLISECONDS
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //创建一个线程b
        Thread b = new Thread(() -> {
            //线程b调用interrupt试图打断a线程
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + "我试图打断" + a.getThreadGroup().getName());
            a.interrupt();
        }, "b");
        b.start();
    }

    /**
     * 结论：a线程必须监听interrupted的状态 来自行决定是否要停止
     * 2021-08-13 17:08:22-->	a	print 0
     * 2021-08-13 17:08:22-->	我试图打断main
     * 2021-08-13 17:08:22-->	a	print 1
     * 2021-08-13 17:08:22-->	靠 我听你的我停止
     */
    private static void interruptedDemo02() {
        //创建一个线程a
        Thread a = new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t靠 我听你的我停止");
                    break;
                } else {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tprint " + i);
                }
            }
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            //线程b调用interrupt试图打断a线程
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + "我试图打断" + a.getThreadGroup().getName());
            a.interrupt();
        }, "b");
        b.start();
    }

    /**
     * 验证了interrupt并非立刻、马上 让其他线程停止
     * 2021-08-13 16:55:51-->	a	print 0
     * 2021-08-13 16:55:51-->	我试图打断main
     * 2021-08-13 16:55:51-->	a	print 1
     * 2021-08-13 16:55:51-->	a	print 2
     * 2021-08-13 16:55:51-->	a	print 3
     * 2021-08-13 16:55:51-->	a	print 4
     * 2021-08-13 16:55:51-->	a	print 5
     * 2021-08-13 16:55:51-->	a	print 6
     * 2021-08-13 16:55:51-->	a	print 7
     * 2021-08-13 16:55:51-->	a	print 8
     * 2021-08-13 16:55:51-->	a	print 9
     * 2021-08-13 16:55:51-->	a	print 10
     * 2021-08-13 16:55:51-->	a	print 11
     * 2021-08-13 16:55:51-->	a	print 12
     * 2021-08-13 16:55:51-->	a	print 13
     * 2021-08-13 16:55:51-->	a	print 14
     * 2021-08-13 16:55:51-->	a	print 15
     * 2021-08-13 16:55:51-->	a	print 16
     * 2021-08-13 16:55:51-->	a	print 17
     * 2021-08-13 16:55:51-->	a	print 18
     * 2021-08-13 16:55:51-->	a	print 19
     * 2021-08-13 16:55:51-->	a	print 20
     * 2021-08-13 16:55:51-->	a	print 21
     * 2021-08-13 16:55:51-->	a	print 22
     * 2021-08-13 16:55:51-->	a	print 23
     * 2021-08-13 16:55:51-->	a	print 24
     * 2021-08-13 16:55:51-->	a	print 25
     * 2021-08-13 16:55:51-->	a	print 26
     * 2021-08-13 16:55:51-->	a	print 27
     * 2021-08-13 16:55:51-->	a	print 28
     * 2021-08-13 16:55:51-->	a	print 29
     * 2021-08-13 16:55:51-->	a	print 30
     * 2021-08-13 16:55:51-->	a	print 31
     * 2021-08-13 16:55:51-->	a	print 32
     * 2021-08-13 16:55:51-->	a	print 33
     * 2021-08-13 16:55:51-->	a	print 34
     * 2021-08-13 16:55:51-->	a	print 35
     * 2021-08-13 16:55:51-->	a	print 36
     * 2021-08-13 16:55:51-->	a	print 37
     * 2021-08-13 16:55:51-->	a	print 38
     * 2021-08-13 16:55:51-->	a	print 39
     * 2021-08-13 16:55:51-->	a	print 40
     * 2021-08-13 16:55:51-->	a	print 41
     * 2021-08-13 16:55:51-->	a	print 42
     * 2021-08-13 16:55:51-->	a	print 43
     * 2021-08-13 16:55:51-->	a	print 44
     * 2021-08-13 16:55:51-->	a	print 45
     * 2021-08-13 16:55:51-->	a	print 46
     * 2021-08-13 16:55:51-->	a	print 47
     * 2021-08-13 16:55:51-->	a	print 48
     * 2021-08-13 16:55:51-->	a	print 49
     * 2021-08-13 16:55:51-->	a	print 50
     * 2021-08-13 16:55:51-->	a	print 51
     * 2021-08-13 16:55:51-->	a	print 52
     * 2021-08-13 16:55:51-->	a	print 53
     * 2021-08-13 16:55:51-->	a	print 54
     * 2021-08-13 16:55:51-->	a	print 55
     * 2021-08-13 16:55:51-->	a	print 56
     * 2021-08-13 16:55:51-->	a	print 57
     * 2021-08-13 16:55:51-->	a	print 58
     * 2021-08-13 16:55:51-->	a	print 59
     */
    private static void interruptDemo01() {
        //创建一个线程a
        Thread a = new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tprint " + i);
            }
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            //线程b调用interrupt试图打断a线程
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + "我试图打断" + a.getThreadGroup().getName());
            a.interrupt();
        }, "b");
        b.start();
    }
}
