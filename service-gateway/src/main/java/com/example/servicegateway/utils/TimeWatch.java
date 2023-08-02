package com.example.servicegateway.utils;

import java.util.concurrent.TimeUnit;

public class TimeWatch {

    private long starts;

    private TimeWatch() {
        reset();
    }

    public static TimeWatch start() {
        return new TimeWatch();
    }

    public void reset() {
        starts = System.nanoTime();
    }

    public long time() {
        long ends = System.nanoTime();
        return ends - starts;
    }

    public long time(TimeUnit unit) {
        return unit.convert(time(), TimeUnit.NANOSECONDS);
    }

    public String toMinuteSeconds() {
        return String.format("%d min, %d sec", time(TimeUnit.MINUTES), time(TimeUnit.SECONDS) - time(TimeUnit.MINUTES));
    }

    public String toMS() {
        return String.format("%d ms", time(TimeUnit.MILLISECONDS));
    }
}