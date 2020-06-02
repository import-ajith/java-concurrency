package com.practice.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo1 {

    private static Map data =new HashMap();
    private static ExecutorService threadPool=Executors.newFixedThreadPool(2);
    //Java 8
    //SimpleDateFormat is not threadsafe
    private static ThreadLocal<SimpleDateFormat> dateFormatter = ThreadLocal.withInitial(()
            ->new SimpleDateFormat("yyyy-MM-dd"));

    //Java -7
  /*  private static ThreadLocal<SimpleDateFormat> dateFormatter =new ThreadLocal<SimpleDateFormat>(){
            @Override
            protected SimpleDateFormat initialValue(){
                return new SimpleDateFormat("yyyy-MM-dd");
            }
            @Override
            public SimpleDateFormat get() {
                return super.get();
            }
    };*/


    public ThreadLocalDemo1(){
        data.put(100,new Date());
        data.put(101,new Date(1220227200));
        data.put(102,new Date(1220228200));
      }

    public static void main(String[] args) {

        for (int i=100;i<111;i++){
            int d=i;
            threadPool.submit(()->{
                String birthDate=new ThreadLocalDemo1().birthDate(d);
                System.out.println(birthDate+ " Thread Name "+Thread.currentThread().getName());
            });
        }


       /* new Thread(()->{
            String birthDate=new ThreadLocalDemo1().birthDate(100);
            System.out.println(birthDate+ " Thread Name "+Thread.currentThread().getName());
        }).start();
        new Thread(()->{
            String birthDate=new ThreadLocalDemo1().birthDate(101);
            System.out.println(birthDate+ " Thread Name "+Thread.currentThread().getName());
        }).start();*/
    }


    public String birthDate(int userId){
        Date birthDate= (Date) data.get(userId);
      //  SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat sf= dateFormatter.get();
        return sf.format(birthDate);
    }


}
