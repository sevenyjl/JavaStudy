package sync;

import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 8种案例分析sync锁
 * 1    标准访问有ab两个线程，请问先打印邮件还是短信
 * 2    sendEmail方法暂停3秒钟，请问先打印邮件还是短信
 * 3    新增一个普通的hello方法，请问先打印邮件还是hello
 * 4    有两部手机，请问先打印邮件还是短信
 * 5    两个静态同步方法，同1部手机，请问先打印邮件还是短信
 * 6    两个静态同步方法， 2部手机，请问先打印邮件还是短信
 * 7    1个静态同步方法，1个普通同步方法,同1部手机，请问先打印邮件还是短信
 * 8    1个静态同步方法，1个普通同步方法,2部手机，请问先打印邮件还是短信
 */
class Phone {
    public synchronized void syncSendEmail(int timeConsuming) {
        //休眠timeConsuming秒
        try {
            TimeUnit.SECONDS.sleep(timeConsuming);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t send email   -->time:" + DateUtil.formatDateTime(new Date()));
    }

    public synchronized void syncSendSMS() {
        System.out.println(Thread.currentThread().getName() + "\t send sms   -->time:" + DateUtil.formatDateTime(new Date()));
    }

    public static synchronized void syncStaticSendEmail(int timeConsuming) {
        //休眠timeConsuming秒
        try {
            TimeUnit.SECONDS.sleep(timeConsuming);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\tstatic send email   -->time:" + DateUtil.formatDateTime(new Date()));
    }

    public static synchronized void syncStaticSendSMS() {
        System.out.println(Thread.currentThread().getName() + "\tstatic send sms   -->time:" + DateUtil.formatDateTime(new Date()));
    }

    public void justPlayUSB() {
        System.out.println(Thread.currentThread().getName() + "\tjustPlayUSB   -->time:" + DateUtil.formatDateTime(new Date()));
    }

    /*---------------------------------以下为验证-------------------------------------------*/
    public void syncThisSendEmail(int timeConsuming) {
        synchronized (this) {
            //休眠timeConsuming秒
            try {
                TimeUnit.SECONDS.sleep(timeConsuming);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t send email   -->time:" + DateUtil.formatDateTime(new Date()));
        }
    }

    public void syncThisSendSMS() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + "\tmethod this send sms");
        }
    }

    public void syncClassSendEmail(int timeConsuming) {
        synchronized (Phone.class) {
            //休眠timeConsuming秒
            try {
                TimeUnit.SECONDS.sleep(timeConsuming);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t send email   -->time:" + DateUtil.formatDateTime(new Date()));
        }
    }

    public void syncClassSendSMS() {
        synchronized (Phone.class) {
            System.out.println(Thread.currentThread().getName() + "\tmethod this send sms");
        }
    }
}

public class Locks8Demo {

    /**
     * 总结：
     * 手机demo总结。
     * 1.普通同步方法锁的是手机实例，两人好比两个线程，只能在同一个实例手机只能进行发邮件或者发消息操作，不能两个人，同一个实例能做两件事
     * 2.静态同步方法锁的是手机模板对象，与手机实例对象无关，你可以用实例手机发短信，但是模板手机就只有一个，不能被两人同时操作，但可以手机实例和模板手机同时操作不同功能
     * <p>
     * java中的sync
     * 普通同步方法锁当前实例对象this
     * 静态同步方法锁 类对象Phone.class
     * <p>
     * 总结：能不用锁就不用，其次同步代码块，其次同步方法（对象锁），最后类锁
     *
     * @param args
     */
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone huawei = new Phone();
        demo01(phone);
        demo02(phone);
        demo03(phone);
        demo04(phone, huawei);
        demo05(phone);
        demo06(phone, huawei);
        demo07(phone);
        demo08(phone, huawei);
        demo09(phone);
        demo10(phone);
    }

    /**
     * a	start time:2021-08-11 23:52:32
     * b	start time:2021-08-11 23:52:32
     * a	 send email   -->time:2021-08-11 23:52:35
     * a	end time:2021-08-11 23:52:35
     * b	static send sms   -->time:2021-08-11 23:52:35
     * b	end time:2021-08-11 23:52:35
     *
     * @param phone
     */
    private static void demo10(Phone phone) {
        //10    1个同步代码块锁Phone.class对象 1个静态同步方法 同一个手机，先打印邮件还是短信
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            phone.syncClassSendEmail(3);
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            phone.syncStaticSendSMS();
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "b");
        b.start();
    }

    /**
     * a	start time:2021-08-11 23:50:38
     * b	start time:2021-08-11 23:50:38
     * a	 send email   -->time:2021-08-11 23:50:41
     * a	end time:2021-08-11 23:50:41
     * b	 send sms   -->time:2021-08-11 23:50:41
     * b	end time:2021-08-11 23:50:41
     *
     * @param phone
     */
    private static void demo09(Phone phone) {
        //9     1个同步代码块锁this对象 1个同步方法 同一个手机，先打印邮件还是短信
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            phone.syncThisSendEmail(3);
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            phone.syncSendSMS();
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "b");
        b.start();
    }

    /**
     * b	start time:2021-08-11 23:47:42
     * a	start time:2021-08-11 23:47:42
     * b	justPlayUSB   -->time:2021-08-11 23:47:42
     * b	end time:2021-08-11 23:47:42
     * a	static send email   -->time:2021-08-11 23:47:45
     * a	end time:2021-08-11 23:47:45
     *
     * @param phone
     * @param huawei
     */
    private static void demo08(Phone phone, Phone huawei) {
        //8    1个静态同步方法，1个普通同步方法,2部手机，请问先打印邮件还是justPlayUSB
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            phone.syncStaticSendEmail(3);
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            huawei.justPlayUSB();
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "b");
        b.start();
    }

    /**
     * a	start time:2021-08-11 23:45:47
     * b	start time:2021-08-11 23:45:47
     * b	 send sms   -->time:2021-08-11 23:45:47
     * b	end time:2021-08-11 23:45:47
     * a	static send email   -->time:2021-08-11 23:45:50
     * a	end time:2021-08-11 23:45:50
     *
     * @param phone
     */
    private static void demo07(Phone phone) {
        //7    1个静态同步方法，1个普通同步方法,同1部手机，请问先打印邮件还是短信
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            phone.syncStaticSendEmail(3);
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            phone.syncSendSMS();
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "b");
        b.start();
    }

    /**
     * a	start time:2021-08-11 23:44:44
     * b	start time:2021-08-11 23:44:44
     * a	static send email   -->time:2021-08-11 23:44:47
     * b	static send sms   -->time:2021-08-11 23:44:47
     * a	end time:2021-08-11 23:44:47
     * b	end time:2021-08-11 23:44:47
     *
     * @param phone
     * @param huawei
     */
    private static void demo06(Phone phone, Phone huawei) {
        //6    两个静态同步方法， 2部手机，请问先打印邮件还是短信
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            phone.syncStaticSendEmail(3);
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            huawei.syncStaticSendSMS();
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "b");
        b.start();
    }

    /**
     * a	start time:2021-08-11 23:42:24
     * b	start time:2021-08-11 23:42:24
     * a	static send email
     * b	static send sms
     * a	end time:2021-08-11 23:42:27
     * b	end time:2021-08-11 23:42:27
     *
     * @param phone
     */
    private static void demo05(Phone phone) {
        //5     两个静态同步方法，同1部手机，请问先打印邮件还是短信
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            phone.syncStaticSendEmail(3);
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            phone.syncStaticSendSMS();
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "b");
        b.start();
    }

    /**
     * a	start time:2021-08-11 23:40:23
     * b	start time:2021-08-11 23:40:23
     * b	 send sms
     * b	end time:2021-08-11 23:40:23
     * a	 send email
     * a	end time:2021-08-11 23:40:26
     *
     * @param phone
     * @param huawei
     */
    private static void demo04(Phone phone, Phone huawei) {
        //4    有两部手机，请问先打印邮件还是短信
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            phone.syncSendEmail(3);
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            huawei.syncSendSMS();
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "b");
        b.start();
    }

    /**
     * a	start time:2021-08-11 23:40:42
     * b	start time:2021-08-11 23:40:42
     * b	justPlayUSB
     * b	end time:2021-08-11 23:40:42
     * a	 send email
     * a	end time:2021-08-11 23:40:45
     *
     * @param phone
     */
    private static void demo03(Phone phone) {
        //3    新增一个普通的justPlayUSB方法，请问先打印邮件还是justPlayUSB
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            phone.syncSendEmail(3);
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            phone.justPlayUSB();
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "b");
        b.start();
    }

    /**
     * a	start time:2021-08-11 23:36:50
     * b	start time:2021-08-11 23:36:50
     * a	 send email
     * b	 send sms
     * b	end time:2021-08-11 23:36:53
     * a	end time:2021-08-11 23:36:53
     *
     * @param phone
     */
    private static void demo02(Phone phone) {
        // 2    sendEmail方法暂停3秒钟，请问先打印邮件还是短信
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            phone.syncSendEmail(3);
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            phone.syncSendSMS();
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "b");
        b.start();
    }

    /**
     * b	start time:2021-08-11 23:36:18
     * a	start time:2021-08-11 23:36:18
     * b	 send sms
     * a	 send email
     * b	end time:2021-08-11 23:36:19
     * a	end time:2021-08-11 23:36:19
     *
     * @param phone
     */
    private static void demo01(Phone phone) {
        //        1    标准访问有ab两个线程，请问先打印邮件还是短信
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            phone.syncSendEmail(0);
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\tstart time:" + DateUtil.formatDateTime(new Date()));
            phone.syncSendSMS();
            System.out.println(Thread.currentThread().getName() + "\tend time:" + DateUtil.formatDateTime(new Date()));
        }, "b");
        b.start();
    }
}
