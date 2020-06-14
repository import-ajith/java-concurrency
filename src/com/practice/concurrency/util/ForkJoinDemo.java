package com.practice.concurrency.util;

import java.util.concurrent.RecursiveTask;

/*
* ForkJoinPool differs from other kinds of ExecutorService mainly by virtue of employing work-stealing:
* This enables efficient processing when most tasks spawn other subtasks
* ForkJoinPool may be constructed with a given target parallelism level; by default,
* equal to the number of available processors
*
* Ideal cases for  ForkJoinPool
* -------------------------
* Avoid Synchronization
* Do not use shared variable across task
* Do not perform Blocking IO operations
* Are pure  and isolated functions
 * */
public class ForkJoinDemo {
    public static void main(String[] args) {
        System.out.println(new Fibonacci(6).compute());
    }
}

class Fibonacci extends RecursiveTask<Integer>{
    //RecursiveTask is a wrapper of ForkJoinTask
    final int  n;
    Fibonacci(int n){
        this.n = n;
    }

    public Integer compute(){
        if (n<=1)
            return n;
        Fibonacci f1=new Fibonacci(n-1);
        f1.fork();
        /*Fibonacci f2=new Fibonacci(n-2);
        f2.fork();
        return f1.join()+f2.join();*/
        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();
    }


}
