package com.java8.concurrency.countdownlatch.checkers.website;

public enum Sites {

    GOOGLE("google.com"),
    YAHOO("yahoo.com"),
    PSNETWORK("playstation.com");

    private final String site;

    private Sites(final String site ) {
        this.site = site;
    }

    @Override
    public String toString() {
        return site;
    }
}
