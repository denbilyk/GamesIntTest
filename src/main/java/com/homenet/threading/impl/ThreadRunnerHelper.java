package com.homenet.threading.impl;

import com.homenet.threading.PerformanceTestResult;

import java.util.*;

/**
 * @author denis.bilyk.
 */
public class ThreadRunnerHelper {
    private static ThreadRunnerHelper instance;
    private List<Long> res;

    private ThreadRunnerHelper() {
        res = new ArrayList<Long>();
    }

    public static synchronized ThreadRunnerHelper getInstance() {
        if (instance == null) {
            instance = new ThreadRunnerHelper();
        }
        return instance;
    }

    public synchronized void setResult(long res) {
        this.res.add(res);

    }

    public PerformanceTestResult getResult() {
        Collections.sort(res);
        long sum = 0;
        for (Long time : res) {
            sum += time;
        }
        return new PerformanceTestResult(sum, res.get(0), res.get(res.size() - 1));
    }

}
