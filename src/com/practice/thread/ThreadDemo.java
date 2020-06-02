package com.practice.thread;

public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        new DemoThread().start();
        new Thread(new Task(), "Thread 1").start();
        new Thread(new Task(),"Thread 2").start();

    }
}

class Task implements  Runnable{

    @Override
    public void run() {
        System.out.println("Runnable Interface...");
    }
}

class DemoThread extends Thread{
    @Override
    public void run(){
        System.out.println("Thread Class...");
    }
}
