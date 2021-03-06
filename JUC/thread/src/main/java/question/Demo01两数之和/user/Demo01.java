package question.Demo01两数之和.user;

/**
 * 用户线程
 *
 * Thread-0	  开始运行用户线程
 * 运行...
 * main	  main 线程结束用户线程
 * 运行...
 * 运行...
 * 运行...
 * 运行...
 * 运行...
 * 运行...
 * 运行...
 * 程序还在运行
 */
public class Demo01 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "\t  开始运行"
                        + (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
                while (true){
                    try {
                        Thread.sleep(1000);
                        System.out.println("运行...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        Thread.sleep(2000l);
        System.out.println(Thread.currentThread().getName() + "\t  main 线程结束"
                + (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
    }
}
