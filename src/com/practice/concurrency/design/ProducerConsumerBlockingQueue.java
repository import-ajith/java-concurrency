package com.practice.concurrency.design;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

/**
 * The producer-consumer problem (also known as the bounded-buffer problem ) is a classic Java Example of a multi-process synchronization problem.
 * The problem describes two processes, the producer and the consumer , who share a common,
 * fixed-size buffer used as a queue.
 *
 * */

/**
 *  @author Ajithlal
 *  */
public class ProducerConsumerBlockingQueue {


    public static void main(String[] args) {

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10); //BlockingQueue Handle concurrent thread access
        Integer i=0;
        //Producer
        final Runnable producer = () -> {
            // while (true){   //Infinite Iteration
            try {
                Long threadId=(Long)Thread.currentThread().getId();
                System.out.println("Producer Value  "+createItem(threadId.intValue()));
                queue.put((int) createItem(threadId.intValue()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // }
        };
        IntStream.range(1,3).forEach(k->new Thread(producer).start());;


        //consumer
        final Runnable consumer =() ->{
          //  while (true){  //Infinite Iteration
                try {
                    Integer t=queue.take();
                    process(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

           // }
        };
        IntStream.range(1,3).forEach(k->new Thread(consumer).start());;

    }

    public static long createItem(long id){
         return id;
    }

    public static void process(Integer t){
        System.out.println("Consumer Value  "+t);
    }
}
