package com.java8.concurrency;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by alex on 12/26/16.
 */
public class ConcurrencyExample1 {

    public void callRunnable(){

    }

    @Test
    public void runnableTest(){
        System.out.println("hi");
        Runnable runnable = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("Foo " + name);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Bar " + name);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);

    }


}
