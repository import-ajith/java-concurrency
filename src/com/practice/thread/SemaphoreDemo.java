package com.practice.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/*
* semaphore maintains a set of permits. Each acquire() blocks if necessary until a permit is available,
* and then takes it. Each release() adds a permit, potentially releasing a blocking acquirer. However,
* no actual permit objects are used; the Semaphore just keeps a count of the number available and acts accordingly.
*
* */
public class SemaphoreDemo {


    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore=new Semaphore(5);//FIFO,fairness guranteed
        ExecutorService service= Executors.newFixedThreadPool(10);//thread pool size 5
        IntStream.range(1,101).forEach(i->service.execute(new Task(semaphore))); //100 copies of task submitted
        //service.execute() doesn't return anything it can access runnable
        //service.submit() return value it can access callable
       // IntStream.range(1,101).forEach(i->System.out.println(i));

        service.shutdown();
        service.awaitTermination(1, TimeUnit.MILLISECONDS);
    }

     static class Task implements Runnable{

         private Semaphore semaphore;
         public Task(Semaphore semaphore) {
             this.semaphore=semaphore;
         }

         @Override
        public void run() {

             try {
                 semaphore.acquire();
                 System.out.println("Thread Name  "+Thread.currentThread().getName());
                 System.out.println("Semaphore Queue Length  "+semaphore.getQueueLength());
                 //semaphore.acquireUninterruptibly(); #not want throw exceotion at 4 thread
                 //some processing
                 //instead of 50 concurrent thread only 3 thread will allow concurrently
             } catch (InterruptedException e) {
                 e.printStackTrace();
             } finally {
                 semaphore.release();
                 //release permit,otherwise it will go to hault
             }

         }
    }
}
