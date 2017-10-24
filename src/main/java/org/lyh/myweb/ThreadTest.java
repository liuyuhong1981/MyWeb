package org.lyh.myweb;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.lyh.myweb.dto.CopyOnWriteJob;
import org.lyh.myweb.dto.Job;
import org.lyh.myweb.dto.MyThread;
import org.lyh.myweb.dto.MyThread2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */

/**
 * @author liuyuho
 *
 */
public class ThreadTest {
    static Logger logger = LoggerFactory.getLogger(ThreadTest.class);

    public static boolean flag = false;

    public static void main(String[] args) throws Exception {
        testConcurrentCollection();
    }

    public static void testConcurrentCollection() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CopyOnWriteJob job = new CopyOnWriteJob();
        job.getList().add("1");
        job.getList().add("2");
        job.getList().add("3");
        job.getList().add("4");
        executorService.execute(job);
        for (int i = 5; i < 15; i++) {
            if (i == 10) {
                job.setFinish(true);
            }
            logger.info("向list添加内容：{}", i);
            job.getList().add(String.valueOf(i));
            Thread.sleep(1000);
        }
    }

    public static void testThreadPool() throws InterruptedException {
        System.out.println("Add non-sync Job.");
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Job job = new Job();
        executorService.execute(job);
        executorService.execute(job);
        executorService.execute(job);
        executorService.execute(job);
        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) {
                System.out.println("Non-sync Job complete.");
                break;
            }
        }

        System.out.println("Add sync Job.");
        executorService = Executors.newFixedThreadPool(4);
        JobSync jobSync = new JobSync();
        executorService.execute(jobSync);
        executorService.execute(jobSync);
        executorService.execute(jobSync);
        executorService.execute(jobSync);
        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) {
                System.out.println("Sync Job complete.");
                break;
            }
        }
    }

    public static void testThread3() {
        int n = 0;
        Thread t1 = new Thread(new MyThread2(n));
        t1.start();
        int n2 = 1;
        Thread t2 = new Thread(new MyThread2(n2));
        t2.start();
    }

    public static void testThread2() {
        int n = 0;
        MyThread t1 = new MyThread(n);
        t1.start();
        int n2 = 1;
        MyThread t2 = new MyThread(n2);
        t2.start();
    }

    public static void testThread1() {
        int n = 0;
        // 线程调用
        FutureTask<Boolean> future = new FutureTask<Boolean>(new Callable<Boolean>() {
            @SuppressWarnings("unused")
            public Boolean call() throws Exception {
                try {
                    if (flag) {
                        System.out.println(flag);
                    }
                    flag = true;
                    int max = 0;
                    for (int i = 0; i < 100000; i++) {
                        System.out.println(n + ":" + i);
                        max += i;
                    }
                } catch (Exception e) {
                    flag = false;
                    return flag;
                }
                return flag;
            }
        });
        new Thread(future).start();
        int n2 = 1;
        // 线程调用
        FutureTask<Boolean> future2 = new FutureTask<Boolean>(new Callable<Boolean>() {
            @SuppressWarnings("unused")
            public Boolean call() throws Exception {
                try {
                    if (flag) {
                        System.out.println(flag);
                    }
                    flag = true;
                    int max = 0;
                    for (int i = 0; i < 100000; i++) {
                        System.out.println(n2 + ":" + i);
                        max += i;
                    }
                } catch (Exception e) {
                    flag = false;
                    return flag;
                }
                return flag;
            }
        });
        new Thread(future2).start();
    }

}