package com.java8.concurrency.countdownlatch.checkers;

import com.java8.concurrency.countdownlatch.checkers.website.Sites;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;

public class WebsiteHealthChecker extends BaseHealthChecker {

    public WebsiteHealthChecker(CountDownLatch latch) {
        super("Website Service ", latch);
    }

    @Override
    public void verifyService() {
        for (Sites site : Sites.values()){
            System.out.println("Checking " + this.getServiceName() + ": " + site.toString());
            System.out.print("site is up?? .... ===>>> ");
            System.out.print(ping(site.toString()));
            System.out.println();

        }
    }

    private static boolean ping(String host) {
        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

        ProcessBuilder processBuilder = new ProcessBuilder("ping", isWindows? "-n" : "-c", "1", host);
        Process proc = null;
        try {
            proc = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int returnVal = 0;
        try {
            returnVal = proc.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnVal == 0;
    }


}
