package com.practice.thread;


public class VolatileDemo {
    private final static int noOfThreads =5;
    public static void main(String[] args) throws InterruptedException {
        VolatileData volatileData=new VolatileData();
        Thread [] threads= new Thread[noOfThreads];
        for (int i = 0; i < noOfThreads; ++i) {
            threads[i]=new VolatileThread(volatileData);
        }
        for (int i = 0; i < noOfThreads; ++i) {
            threads[i].start();
        }
        for (int i = 0; i < noOfThreads; ++i) {
            threads[i].join();
        }
    }
}

class VolatileThread extends Thread{
    private final VolatileData volatileData;

    VolatileThread(VolatileData volatileData) {
        this.volatileData = volatileData;
    }

    public void run(){
        int oldValue=volatileData.getCounter();
        System.out.println("[Thread " + Thread.currentThread().getName() + "]: Old value = " + oldValue);

        volatileData.increaseCounter();
        int newValue = volatileData.getCounter();
        System.out.println("[Thread " + Thread.currentThread().getName()+ "]: New value = " + newValue);



    }
}

class VolatileData{
    private volatile int counter=1;
    public  synchronized int  getCounter() {
        return counter;
    }

    public synchronized void increaseCounter(){
        ++counter;
     }
}
