package fairNoFair;

import java.util.concurrent.locks.ReentrantLock;

class Ticket {
    private int number = 30;

    public void sale(ReentrantLock reentrantLock) {
        reentrantLock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第：\t" + (number--) + "\t 还剩下:" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}


public class SaleTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        noFairLock(ticket);
    }

    /**
     * 雨露均沾
     * a卖出第：	30	 还剩下:29
     * b卖出第：	29	 还剩下:28
     * c卖出第：	28	 还剩下:27
     * a卖出第：	27	 还剩下:26
     * b卖出第：	26	 还剩下:25
     * c卖出第：	25	 还剩下:24
     * a卖出第：	24	 还剩下:23
     * b卖出第：	23	 还剩下:22
     * c卖出第：	22	 还剩下:21
     * a卖出第：	21	 还剩下:20
     * b卖出第：	20	 还剩下:19
     * c卖出第：	19	 还剩下:18
     * a卖出第：	18	 还剩下:17
     * b卖出第：	17	 还剩下:16
     * c卖出第：	16	 还剩下:15
     * a卖出第：	15	 还剩下:14
     * b卖出第：	14	 还剩下:13
     * c卖出第：	13	 还剩下:12
     * a卖出第：	12	 还剩下:11
     * b卖出第：	11	 还剩下:10
     * c卖出第：	10	 还剩下:9
     * a卖出第：	9	 还剩下:8
     * b卖出第：	8	 还剩下:7
     * c卖出第：	7	 还剩下:6
     * a卖出第：	6	 还剩下:5
     * b卖出第：	5	 还剩下:4
     * c卖出第：	4	 还剩下:3
     * a卖出第：	3	 还剩下:2
     * b卖出第：	2	 还剩下:1
     * c卖出第：	1	 还剩下:0
     *
     * @param ticket
     */
    private static void fairLock(Ticket ticket) {
        ReentrantLock lock = new ReentrantLock(true);
        new Thread(() -> {
            for (int i = 0; i < 35; i++) ticket.sale(lock);
        }, "a").start();
        new Thread(() -> {
            for (int i = 0; i < 35; i++) ticket.sale(lock);
        }, "b").start();
        new Thread(() -> {
            for (int i = 0; i < 35; i++) ticket.sale(lock);
        }, "c").start();
    }

    /**
     * 可能存在一个线程独占，线程饥饿
     * a卖出第：	30	 还剩下:29
     * a卖出第：	29	 还剩下:28
     * a卖出第：	28	 还剩下:27
     * a卖出第：	27	 还剩下:26
     * a卖出第：	26	 还剩下:25
     * a卖出第：	25	 还剩下:24
     * a卖出第：	24	 还剩下:23
     * a卖出第：	23	 还剩下:22
     * a卖出第：	22	 还剩下:21
     * a卖出第：	21	 还剩下:20
     * a卖出第：	20	 还剩下:19
     * a卖出第：	19	 还剩下:18
     * a卖出第：	18	 还剩下:17
     * a卖出第：	17	 还剩下:16
     * a卖出第：	16	 还剩下:15
     * a卖出第：	15	 还剩下:14
     * a卖出第：	14	 还剩下:13
     * a卖出第：	13	 还剩下:12
     * a卖出第：	12	 还剩下:11
     * a卖出第：	11	 还剩下:10
     * a卖出第：	10	 还剩下:9
     * a卖出第：	9	 还剩下:8
     * a卖出第：	8	 还剩下:7
     * a卖出第：	7	 还剩下:6
     * a卖出第：	6	 还剩下:5
     * a卖出第：	5	 还剩下:4
     * a卖出第：	4	 还剩下:3
     * a卖出第：	3	 还剩下:2
     * a卖出第：	2	 还剩下:1
     * a卖出第：	1	 还剩下:0
     *
     * @param ticket
     */
    private static void noFairLock(Ticket ticket) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {
            for (int i = 0; i < 35; i++) ticket.sale(lock);
        }, "a").start();
        new Thread(() -> {
            for (int i = 0; i < 35; i++) ticket.sale(lock);
        }, "b").start();
        new Thread(() -> {
            for (int i = 0; i < 35; i++) ticket.sale(lock);
        }, "c").start();
    }
}
 