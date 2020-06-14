package com.practice.concurrency.util;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * A CompletableFuture is an extension to Java's Future API which was introduced in Java 8.
 * It runs a task on a separate thread than the main application thread
 * and notifies the main thread about its progress, completion or failure.
 *
 * In this way, the main thread does not block or wait for the completion of the task.
 * Other tasks execute in parallel.Parallelism improves the performance of the program.
 * */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        //different  task can give  different executor service,default is ForkJoinPool
        ExecutorService cpuBound= Executors.newFixedThreadPool(4);
        ExecutorService ioBound= Executors.newCachedThreadPool();
        //all the operation process asynchronously
        IntStream.range(1, 101).forEach(i ->
                CompletableFuture.supplyAsync(() -> new ServiceTask().Order(),cpuBound)
                        .thenApplyAsync(order -> new ServiceTask().Payment(),ioBound)
                        .exceptionally(e->new  ServiceTask().FailedPayment())//Exception can throw
                        .thenApply(order -> new ServiceTask().Deliver())
                        .thenAccept(order -> new ServiceTask().SentEmail()));
    }

    public static class ServiceTask {

        public Object Order() {
            //Do some order processing
            return null;
        }

        public Object Payment() {
            //Do some  Payment processing
            return null;
        }

        public Object FailedPayment() {
            //Do some  Payment processing
            return null;
        }

        public Object Deliver() {
            //Do some  delivery processing
            return null;
        }

        public Object SentEmail() {
            //Do some  sent email processing
            return null;
        }
    }

}
