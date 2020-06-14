package com.practice.concurrency.util;

import java.util.concurrent.*;

/**
* A synchronization aid that allows a set of threads to all wait for each other to reach a common barrier point
* The barrier is called cyclic because it can be re-used after the waiting threads are released.
* used to perform particular task repeatedly
* */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        ExecutorService executors= Executors.newFixedThreadPool(4);
        CyclicBarrier barrier=new CyclicBarrier(3);
        executors.submit(new Task(barrier));
        executors.submit(new Task(barrier));
        executors.submit(new Task(barrier));

    }

    public static class Task implements Runnable{

        private CyclicBarrier barrier;

        public Task(CyclicBarrier barrier) {
            this.barrier=barrier;
        }

        @Override
        public void run() {
            while (true){  //repeat the cycle
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                //send message to corresponding system
            }
        }
    }
}
