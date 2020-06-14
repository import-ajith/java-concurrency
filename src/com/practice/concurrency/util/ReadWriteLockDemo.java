package com.practice.concurrency.util;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
* A ReadWriteLock maintains a pair of associated locks, one for read-only operations and one for writing.
* The read lock may be held simultaneously by multiple reader threads, so long as there are no writers.
*  The write lock is exclusive.
*  ReentrantReadWriteLock does not impose a reader or writer preference ordering for lock access.
* This lock allows both readers and writers to reacquire read or write locks in the style of a ReentrantLock.
* Allow write owner thread to also acquire read lock
* Good for frequent reads and infrequent writes
* */
public class ReadWriteLockDemo {

    private ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock=lock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock=lock.writeLock();

    private void readResource(){
        readLock.lock();
        //read the resource
        readLock.unlock();
    }

    private void writeResource(){
        writeLock.lock();
        //update resource
        writeLock.unlock();
    }

    public static void main(String[] args) {
        Thread t1=new Thread(()->new ReadWriteLockDemo().readResource());t1.start();
        Thread t2=new Thread(()->new ReadWriteLockDemo().readResource());t2.start();
        Thread t3=new Thread(()->new ReadWriteLockDemo().writeResource());t3.start();
        Thread t4=new Thread(()->new ReadWriteLockDemo().writeResource());t4.start();
     }
}
