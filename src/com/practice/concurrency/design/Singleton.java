package com.practice.concurrency.design;

/**
 * Implement Singleton using Double check locking
 * */
public class Singleton {
}


class ResourceSingleton{

    private static volatile Resource rs=null; //volatile make constructor atomic


    public static Resource getExpensiveResources(){

        if (rs==null) {                       //3.synchronize only if resource  is not initialized
            synchronized (ResourceSingleton.class) {             //2.avoid thread safety problem;not efficient because all thread will enter into this block
                if (rs == null) {             //1.Lazy intialization,but it's not thread safe,two threads can reach at same line
                    rs = new Resource();      //4. 1)constructor called 2) assign to rs 3)call constructor make 3 steps into atomic
                }
            }
        }
        return rs;
    }

    private ResourceSingleton() {            //private constructor
        Object field1 = null;//some cpu intensive logic
        Object field2=null;//some db call or etc

    }
}
