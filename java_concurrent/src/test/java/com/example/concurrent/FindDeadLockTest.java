package com.example.concurrent;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 15:27 on 2020/4/1
 * @version V0.1
 * @classNmae FindDeadLockTest
 */
public class FindDeadLockTest {


    /**
     * 检测运行中出现死锁的线程
     * @throws InterruptedException
     */
    @Test
    public void findDeadLockTest() throws InterruptedException {
        ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
        Runnable dlCheck = new Runnable() {

            @Override
            public void run() {
                long[] threadIds = mbean.findDeadlockedThreads();
                if (threadIds != null) {
                    ThreadInfo[] threadInfos = mbean.getThreadInfo(threadIds);
                    System.out.println("Detected deadlock threads:");
                    for (ThreadInfo threadInfo : threadInfos) {
                        System.out.println(threadInfo.getThreadName());
                    }
                }
            }
        };

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        // 稍等5秒，然后每10秒进行一次死锁扫描
        scheduler.scheduleAtFixedRate(dlCheck, 5L, 10L, TimeUnit.SECONDS);
        // 死锁样例代码…
        deadLockCode();

    }

    /**
     * 死锁部分
     * @throws InterruptedException
     */
    public void deadLockCode() throws InterruptedException {

            String lockA = "lockA";
            String lockB = "lockB";
            DeadLockSample t1 = new DeadLockSample("Thread1", lockA, lockB);
            DeadLockSample t2 = new DeadLockSample("Thread2", lockB, lockA);
            t1.start();
            t2.start();
            t1.join();
            t2.join();

    }
}
