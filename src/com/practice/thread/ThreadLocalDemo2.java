package com.practice.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo2 {

    private static ExecutorService threadPool1=Executors.newFixedThreadPool(2);
    public List<User> userList =new ArrayList<>();

    public static void main(String[] args) {
       new Thread(()->{
            new ServiceSet().process("A");
            new ServiceGet().process();
      }).start();

        new Thread(()->{
            new ServiceSet().process("B");
            new ServiceGet().process();
        }).start();

    }

    public  ThreadLocalDemo2(){
        User user1=new User("A",21);
        userList.add(user1);
        User user2=new User("B",22);
        userList.add(user2);
 }


}

class ServiceSet{
    public void process(String name){
        ThreadLocalDemo2 th=new ThreadLocalDemo2();
        User user=new User(name,21);
        UserContextHolder.holder.set(user);
        System.out.println("ServiceSet   : "+user+" Thread  "+Thread.currentThread().getName());
    }
}

class ServiceGet{
    public void process(){
       User user= UserContextHolder.holder.get();
        System.out.println("ServiceGet   : "+user +" Thread  "+Thread.currentThread().getName());
     }
}




 class UserContextHolder {
    public static ThreadLocal<User> holder=new ThreadLocal<>();
}

 class User{
    private String name;
    private Integer age;


    public  String getName() {
          return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name,Integer age) {
        this.name = name;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}