package com.practice.thread;

/**
* A thread is a thread of execution in a program.
* The Java Virtual Machine allows an application to have multiple threads of execution running concurrently.
* There are two ways to create a new thread of execution. One is to declare a class to be a subclass of Thread.
*  This subclass should override the run method of class Thread. An instance of the subclass can then be allocated and started
*
* That class then implements the run method.
*  An instance of the class can then be allocated, passed as an argument when creating Thread, and started
* */
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
