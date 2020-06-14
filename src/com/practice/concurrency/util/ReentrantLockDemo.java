package com.practice.concurrency.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
* A reentrant mutual exclusion Lock with the same basic behavior and semantics as the implicit monitor
* lock accessed using synchronized methods and statements, but with extended capabilities.*/
public class ReentrantLockDemo {

    private static ReentrantLock lock = new ReentrantLock();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(4);
    private static int t = 0;

    public static void accessResource()  {
        //lock the resource
        boolean lockAcquired=lock.tryLock();//try to access lock for unfair lock,have option to give time
        if (lockAcquired) {
            // lock.lock();
            try {
                //access the resource
                //  accessResource();-Thread can re-enter here.it will allow to call lock multiple time
                t = t + 1;
                System.out.println("Thread Name  " + Thread.currentThread().getName() + "  " + t);
                //unlock the resource
            } finally {
                //define inside finally block
                lock.unlock();
            }
        }else{
            //do something alternatives
        }
 }

    public static void main(String[] args) {
        /*threadPool.submit(()->{
            accessResource();
        });
        threadPool.shutdown();*/

        new Thread(() -> accessResource(), "Thread-1").start();
        new Thread(() -> accessResource(), "Thread-2").start();
        new Thread(() -> accessResource(), "Thread-3").start();
        new Thread(() -> accessResource(), "Thread-4").start();
        new Thread(() -> accessResource(), "Thread-5").start();
    }
}
