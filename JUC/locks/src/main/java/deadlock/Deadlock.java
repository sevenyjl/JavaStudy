package deadlock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * demo01:写一个死锁
 * 死锁的排查：jps-->得到运行id--->jstack 运行id
 * <p>
 * PS F:\aaa-project\gitee\GTeam_seven\knowledge-points> jps
 * 17712
 * 12788 Deadlock
 * 2356 Jps
 * 34148 Launcher
 * 15308 RemoteMavenServer36
 * PS F:\aaa-project\gitee\GTeam_seven\knowledge-points> jstack 12788
 * 2021-08-13 14:17:17
 * Full thread dump Java HotSpot(TM) 64-Bit Server VM (11.0.11+9-LTS-194 mixed mode):
 * <p>
 * Threads class SMR info:
 * _java_thread_list=0x000001e7615c8ea0, length=13, elements={
 * 0x000001e7608e9000, 0x000001e7608ea800, 0x000001e7611fd000, 0x000001e7611ff000,
 * 0x000001e761201800, 0x000001e761214000, 0x000001e761241000, 0x000001e7608a6000,
 * 0x000001e7615a0800, 0x000001e7615a1000, 0x000001e7615ae800, 0x000001e7615af000,
 * 0x000001e7345b4800
 * }
 * <p>
 * "Reference Handler" #2 daemon prio=10 os_prio=2 cpu=0.00ms elapsed=35.61s tid=0x000001e7608e9000 nid=0x5670 waiting on condition  [0x00000
 * 06f240ff000]
 * java.lang.Thread.State: RUNNABLE
 * at java.lang.ref.Reference.waitForReferencePendingList(java.base@11.0.11/Native Method)
 * at java.lang.ref.Reference.processPendingReferences(java.base@11.0.11/Reference.java:241)
 * at java.lang.ref.Reference$ReferenceHandler.run(java.base@11.0.11/Reference.java:213)
 * <p>
 * "Finalizer" #3 daemon prio=8 os_prio=1 cpu=0.00ms elapsed=35.60s tid=0x000001e7608ea800 nid=0x46cc in Object.wait()  [0x0000006f241ff000]
 * java.lang.Thread.State: WAITING (on object monitor)
 * at java.lang.Object.wait(java.base@11.0.11/Native Method)
 * - waiting on <0x0000000715908fa8> (a java.lang.ref.ReferenceQueue$Lock)
 * at java.lang.ref.ReferenceQueue.remove(java.base@11.0.11/ReferenceQueue.java:155)
 * - waiting to re-lock in wait() <0x0000000715908fa8> (a java.lang.ref.ReferenceQueue$Lock)
 * at java.lang.ref.ReferenceQueue.remove(java.base@11.0.11/ReferenceQueue.java:176)
 * at java.lang.ref.Finalizer$FinalizerThread.run(java.base@11.0.11/Finalizer.java:170)
 * <p>
 * "Signal Dispatcher" #4 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=35.58s tid=0x000001e7611fd000 nid=0x4fac runnable  [0x0000000000000000]
 * java.lang.Thread.State: RUNNABLE
 * <p>
 * "Attach Listener" #5 daemon prio=5 os_prio=2 cpu=0.00ms elapsed=35.58s tid=0x000001e7611ff000 nid=0xa54 waiting on condition  [0x000000000
 * 0000000]
 * java.lang.Thread.State: RUNNABLE
 * <p>
 * "C2 CompilerThread0" #6 daemon prio=9 os_prio=2 cpu=46.88ms elapsed=35.58s tid=0x000001e761201800 nid=0x3ab0 waiting on condition  [0x0000
 * 000000000000]
 * java.lang.Thread.State: RUNNABLE
 * No compile task
 * <p>
 * "C1 CompilerThread0" #9 daemon prio=9 os_prio=2 cpu=156.25ms elapsed=35.58s tid=0x000001e761214000 nid=0x7ac0 waiting on condition  [0x000
 * 0000000000000]
 * java.lang.Thread.State: RUNNABLE
 * No compile task
 * <p>
 * "Sweeper thread" #10 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=35.58s tid=0x000001e761241000 nid=0x67d4 runnable  [0x0000000000000000]
 * java.lang.Thread.State: RUNNABLE
 * <p>
 * "Common-Cleaner" #11 daemon prio=8 os_prio=1 cpu=0.00ms elapsed=35.52s tid=0x000001e7608a6000 nid=0x79cc in Object.wait()  [0x0000006f247f
 * e000]
 * java.lang.Thread.State: TIMED_WAITING (on object monitor)
 * at java.lang.Object.wait(java.base@11.0.11/Native Method)
 * - waiting on <0x000000071582f2e0> (a java.lang.ref.ReferenceQueue$Lock)
 * at java.lang.ref.ReferenceQueue.remove(java.base@11.0.11/ReferenceQueue.java:155)
 * - waiting to re-lock in wait() <0x000000071582f2e0> (a java.lang.ref.ReferenceQueue$Lock)
 * at jdk.internal.ref.CleanerImpl.run(java.base@11.0.11/CleanerImpl.java:148)
 * at java.lang.Thread.run(java.base@11.0.11/Thread.java:834)
 * at jdk.internal.misc.InnocuousThread.run(java.base@11.0.11/InnocuousThread.java:134)
 * <p>
 * "Monitor Ctrl-Break" #12 daemon prio=5 os_prio=0 cpu=31.25ms elapsed=35.43s tid=0x000001e7615a0800 nid=0x6c78 runnable  [0x0000006f249fe00
 * 0]
 * java.lang.Thread.State: RUNNABLE
 * at java.net.SocketInputStream.socketRead0(java.base@11.0.11/Native Method)
 * at java.net.SocketInputStream.socketRead(java.base@11.0.11/SocketInputStream.java:115)
 * at java.net.SocketInputStream.read(java.base@11.0.11/SocketInputStream.java:168)
 * at java.net.SocketInputStream.read(java.base@11.0.11/SocketInputStream.java:140)
 * at sun.nio.cs.StreamDecoder.readBytes(java.base@11.0.11/StreamDecoder.java:284)
 * at sun.nio.cs.StreamDecoder.implRead(java.base@11.0.11/StreamDecoder.java:326)
 * at sun.nio.cs.StreamDecoder.read(java.base@11.0.11/StreamDecoder.java:178)
 * - locked <0x00000007156fbe18> (a java.io.InputStreamReader)
 * at java.io.InputStreamReader.read(java.base@11.0.11/InputStreamReader.java:181)
 * at java.io.BufferedReader.fill(java.base@11.0.11/BufferedReader.java:161)
 * at java.io.BufferedReader.readLine(java.base@11.0.11/BufferedReader.java:326)
 * - locked <0x00000007156fbe18> (a java.io.InputStreamReader)
 * at java.io.BufferedReader.readLine(java.base@11.0.11/BufferedReader.java:392)
 * at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:49)
 * <p>
 * "Service Thread" #13 daemon prio=9 os_prio=0 cpu=0.00ms elapsed=35.43s tid=0x000001e7615a1000 nid=0x63c8 runnable  [0x0000000000000000]
 * java.lang.Thread.State: RUNNABLE
 * <p>
 * "a" #14 prio=5 os_prio=0 cpu=46.88ms elapsed=35.42s tid=0x000001e7615ae800 nid=0x558 waiting on condition  [0x0000006f24cfe000]
 * java.lang.Thread.State: WAITING (parking)
 * at jdk.internal.misc.Unsafe.park(java.base@11.0.11/Native Method)
 * - parking to wait for  <0x00000007157ba450> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
 * at java.util.concurrent.locks.LockSupport.park(java.base@11.0.11/LockSupport.java:194)
 * at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(java.base@11.0.11/AbstractQueuedSynchronizer.java:8
 * 85)
 * at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(java.base@11.0.11/AbstractQueuedSynchronizer.java:917)
 * at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(java.base@11.0.11/AbstractQueuedSynchronizer.java:1240)
 * at java.util.concurrent.locks.ReentrantLock.lock(java.base@11.0.11/ReentrantLock.java:267)
 * at deadlock.Deadlock.lambda$demo01deadLockByReentrantLock$2(Deadlock.java:69)
 * at deadlock.Deadlock$$Lambda$14/0x0000000800066840.run(Unknown Source)
 * at java.lang.Thread.run(java.base@11.0.11/Thread.java:834)
 * <p>
 * "b" #15 prio=5 os_prio=0 cpu=78.13ms elapsed=35.42s tid=0x000001e7615af000 nid=0x7460 waiting on condition  [0x0000006f24dfe000]
 * java.lang.Thread.State: WAITING (parking)
 * at jdk.internal.misc.Unsafe.park(java.base@11.0.11/Native Method)
 * - parking to wait for  <0x00000007157ba420> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
 * at java.util.concurrent.locks.LockSupport.park(java.base@11.0.11/LockSupport.java:194)
 * at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(java.base@11.0.11/AbstractQueuedSynchronizer.java:8
 * 85)
 * at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(java.base@11.0.11/AbstractQueuedSynchronizer.java:917)
 * at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(java.base@11.0.11/AbstractQueuedSynchronizer.java:1240)
 * at java.util.concurrent.locks.ReentrantLock.lock(java.base@11.0.11/ReentrantLock.java:267)
 * at deadlock.Deadlock.lambda$demo01deadLockByReentrantLock$3(Deadlock.java:79)
 * at deadlock.Deadlock$$Lambda$15/0x0000000800066c40.run(Unknown Source)
 * at java.lang.Thread.run(java.base@11.0.11/Thread.java:834)
 * <p>
 * "DestroyJavaVM" #16 prio=5 os_prio=0 cpu=218.75ms elapsed=35.42s tid=0x000001e7345b4800 nid=0xa38 waiting on condition  [0x000000000000000
 * 0]
 * java.lang.Thread.State: RUNNABLE
 * <p>
 * "VM Thread" os_prio=2 cpu=0.00ms elapsed=35.61s tid=0x000001e7608e7800 nid=0x4398 runnable
 * <p>
 * "GC Thread#0" os_prio=2 cpu=0.00ms elapsed=35.63s tid=0x000001e7345cb000 nid=0x8e48 runnable
 * <p>
 * "G1 Main Marker" os_prio=2 cpu=0.00ms elapsed=35.63s tid=0x000001e734624000 nid=0x773c runnable
 * <p>
 * "G1 Conc#0" os_prio=2 cpu=0.00ms elapsed=35.63s tid=0x000001e734626800 nid=0x38bc runnable
 * <p>
 * "G1 Refine#0" os_prio=2 cpu=0.00ms elapsed=35.63s tid=0x000001e7607c9000 nid=0x6ee4 runnable
 * <p>
 * "G1 Young RemSet Sampling" os_prio=2 cpu=0.00ms elapsed=35.63s tid=0x000001e7607cc000 nid=0x3ef0 runnable
 * "VM Periodic Task Thread" os_prio=2 cpu=0.00ms elapsed=35.43s tid=0x000001e7615a2800 nid=0x4de4 waiting on condition
 * <p>
 * JNI global refs: 16, weak refs: 0
 * <p>
 * <p>
 * Found one Java-level deadlock:
 * =============================
 * "a":
 * waiting for ownable synchronizer 0x00000007157ba450, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
 * which is held by "b"
 * "b":
 * waiting for ownable synchronizer 0x00000007157ba420, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
 * which is held by "a"
 * <p>
 * Java stack information for the threads listed above:
 * ===================================================
 * "a":
 * at jdk.internal.misc.Unsafe.park(java.base@11.0.11/Native Method)
 * - parking to wait for  <0x00000007157ba450> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
 * at java.util.concurrent.locks.LockSupport.park(java.base@11.0.11/LockSupport.java:194)
 * at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(java.base@11.0.11/AbstractQueuedSynchronizer.java:8
 * 85)
 * at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(java.base@11.0.11/AbstractQueuedSynchronizer.java:917)
 * at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(java.base@11.0.11/AbstractQueuedSynchronizer.java:1240)
 * at java.util.concurrent.locks.ReentrantLock.lock(java.base@11.0.11/ReentrantLock.java:267)
 * at deadlock.Deadlock.lambda$demo01deadLockByReentrantLock$2(Deadlock.java:69)
 * at deadlock.Deadlock$$Lambda$14/0x0000000800066840.run(Unknown Source)
 * at java.lang.Thread.run(java.base@11.0.11/Thread.java:834)
 * "b":
 * at jdk.internal.misc.Unsafe.park(java.base@11.0.11/Native Method)
 * - parking to wait for  <0x00000007157ba420> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
 * at java.util.concurrent.locks.LockSupport.park(java.base@11.0.11/LockSupport.java:194)
 * at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(java.base@11.0.11/AbstractQueuedSynchronizer.java:8
 * 85)
 * at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(java.base@11.0.11/AbstractQueuedSynchronizer.java:917)
 * at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(java.base@11.0.11/AbstractQueuedSynchronizer.java:1240)
 * at java.util.concurrent.locks.ReentrantLock.lock(java.base@11.0.11/ReentrantLock.java:267)
 * at deadlock.Deadlock.lambda$demo01deadLockByReentrantLock$3(Deadlock.java:79)
 * at deadlock.Deadlock$$Lambda$15/0x0000000800066c40.run(Unknown Source)
 * at java.lang.Thread.run(java.base@11.0.11/Thread.java:834)
 * <p>
 * Found 1 deadlock.
 */
public class Deadlock {

    public static void main(String[] args) {
        demo01deadLockByReentrantLock();
    }

    /**
     * sync导致的死锁
     */
    private static void demo01DeadLockBySync() {
        Object lock1 = new Object();
        Object lock2 = new Object();
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            synchronized (lock1) {
                //休眠1秒
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {

                }
            }
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\texit in");
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            synchronized (lock2) {
                synchronized (lock1) {

                }
            }
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\texit in");
        }, "b");
        b.start();
    }

    /**
     * ReentrantLock导致的死锁
     */
    private static void demo01deadLockByReentrantLock() {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        //创建一个线程a
        Thread a = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            lock1.lock();
            //休眠1秒
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock2.lock();
            lock2.unlock();
            lock1.unlock();
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\texit in");
        }, "a");
        a.start();
        //创建一个线程b
        Thread b = new Thread(() -> {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\tcome in");
            lock2.lock();
            lock1.lock();
            lock1.unlock();
            lock2.unlock();
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->\t" + Thread.currentThread().getName() + "\texit in");
        }, "b");
        b.start();
    }
}
