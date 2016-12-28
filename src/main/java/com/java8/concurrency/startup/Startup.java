package com.java8.concurrency.startup;

import com.java8.concurrency.countdownlatch.AppStartupUtil;

public class Startup {

    public static void main(String[] args)
    {
        boolean result = false;
        try {
            result = AppStartupUtil.checkExternalServices();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was :: "+ result);
        System.exit(0);
    }
}