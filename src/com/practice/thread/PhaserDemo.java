package com.practice.thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.stream.IntStream;

/*
* A reusable synchronization barrier,
* similar in functionality to CyclicBarrier and CountDownLatch but supporting more flexible usage.
* */
public class PhaserDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(4);
        Phaser phaser = new Phaser(3);
        IntStream.range(1,4).forEach(i -> service.execute(new PhaserDemo.Service(phaser)));

        phaser.awaitAdvance(1); //similar to await
        System.out.println("All Dependents Services are initialized.....");
        //program initialized perform other operation
        phaser.bulkRegister(3);//bulk register later(post constructor),total threads now 7
    }

    public static class Service implements Runnable {

        private Phaser phaser;

        public Service(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            phaser.register();//allows threads to register themself
            //some operations
            phaser.arrive();//similar to countdown
            //continue with other operation
          //  phaser.arriveAndDeregister();//deregister dynamically
        }
    }
}
