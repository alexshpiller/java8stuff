package com.java8.concurrency.countdownlatch;

import com.java8.concurrency.countdownlatch.checkers.BaseHealthChecker;
import com.java8.concurrency.countdownlatch.checkers.NetworkHealthChecker;
import com.java8.concurrency.countdownlatch.checkers.WebsiteHealthChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppStartupUtil
{
    private static List<BaseHealthChecker> _services;
    private static CountDownLatch _latch;
    private final static AppStartupUtil INSTANCE = new AppStartupUtil();

    private AppStartupUtil()
    {
    }


    public static AppStartupUtil getInstance()
    {
        return INSTANCE;
    }

    public static boolean checkExternalServices() throws Exception
    {
        _latch = new CountDownLatch(2);

        _services = new ArrayList<>();
        _services.add(new NetworkHealthChecker(_latch));
        _services.add(new WebsiteHealthChecker(_latch));

        Executor executor = Executors.newFixedThreadPool(_services.size());

        for(final BaseHealthChecker v : _services)
        {
            executor.execute(v);
        }

        _latch.await();

        for(final BaseHealthChecker v : _services)
        {
            if( ! v.isServiceUp())
            {
                return false;
            }
        }
        return true;
    }
}
