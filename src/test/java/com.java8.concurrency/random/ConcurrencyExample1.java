package com.java8.concurrency.random;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by alex on 12/26/16.
 */
public class ConcurrencyExample1 {

    @Test
    public void runnableTest(){
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

        Thread t1 = new Thread(runnable);
        t1.start();

    }


}
