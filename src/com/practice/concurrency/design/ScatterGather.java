package com.practice.concurrency.design;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * The Scatter-Gather routes a request message to the a number of recipients.
 * It then uses an Aggregator to collect the responses and distill them into a single response message.
 * */

/**
 *  @author Ajithlal
 *  */
public class ScatterGather {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch=new CountDownLatch(3);//why 3 means ,3 calls are happening
        ExecutorService threadPool= Executors.newFixedThreadPool(4);
        String url1="https:// ..... ";
        String url2="https:// ..... ";
        String url3="https:// ..... ";

        int productId=123;
        Set<Integer> prices= Collections.synchronizedSet(new HashSet<>()); //synchronized for thread safety
        threadPool.submit(new Task3(url1,productId,prices,latch));
        threadPool.submit(new Task3(url2,productId,prices,latch));
        threadPool.submit(new Task3(url3,productId,prices,latch));

        latch.await(3, TimeUnit.SECONDS); //wait countdown to reach zero or timeout,whichever occur first


        //completablefuture implementation
       /* CompletableFuture<Void> task1=CompletableFuture.runAsync(new Task3(url1,productId,prices));
        CompletableFuture<Void> task2=CompletableFuture.runAsync(new Task3(url2,productId,prices));
        CompletableFuture<Void> task3=CompletableFuture.runAsync(new Task3(url3,productId,prices));
        CompletableFuture<Void> alltasks=CompletableFuture.allOf(task1,task2,task3);//wrap all CF
        alltasks.get(3,TimeUnit.SECONDS);*/
    }
}

class Task3 implements Runnable{

    private String url;
    private int productId;
    private Set<Integer> prices;
    private CountDownLatch latch;

    public Task3(String url, int productId, Set<Integer> prices,CountDownLatch latch) {
        this.url = url;
        this.productId = productId;
        this.prices = prices;
        this.latch=latch;
    }

    @Override
    public void run() {
        int price=0;
        //make http call
        prices.add(price) ;//add price to common collection
        latch.countDown();
    }
}