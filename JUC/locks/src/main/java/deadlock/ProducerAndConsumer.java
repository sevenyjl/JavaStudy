package deadlock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * 生产者和消费者
 */
public class ProducerAndConsumer {

    public static void main(String[] args) {
        Stack<String> temp = new Stack<>();
        for (int j = 0; j < 10; j++) {
            //创建一个线程producer
            Thread producer = new Thread(() -> {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
                for (int i = 0; i < 100; i++) {
                    //休眠1秒
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t生产：" + temp.push(i + ""));
                }
            }, "producer->" + j);
            producer.start();
        }
        //创建一个线程consumer
        Thread consumer = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            while (true) {
                if (temp.empty()) {
                    //休眠1秒
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\t消费：" + temp.pop());
                }
            }
        }, "consumer");
        consumer.start();
    }
}
