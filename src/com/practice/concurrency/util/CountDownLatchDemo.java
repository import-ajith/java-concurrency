package com.practice.concurrency.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * A synchronization aid that allows one or more threads to wait until a set of operations
 * being performed in other threads completes.
 * */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(4);
        CountDownLatch latch = new CountDownLatch(3);
      /*  service.submit(new DependentService(latch));
        service.submit(new DependentService(latch));
        service.submit(new DependentService(latch));*/
        IntStream.range(1,4).forEach(i -> service.execute(new DependentService(latch)));

        latch.await();
        System.out.println("All Dependents Services are initialized.....");
        //program initialized perform other operation

    }

    public static class DependentService implements Runnable {

        private CountDownLatch latch;

        public DependentService(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            //startup task
            latch.countDown();
            //continue with other operation
        }
    }
}
