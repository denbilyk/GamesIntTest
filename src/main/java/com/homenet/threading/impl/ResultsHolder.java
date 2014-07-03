package com.homenet.threading.impl;

import com.homenet.threading.PerformanceTestResult;

import java.util.*;

/**
 * @author denis.bilyk.
 * Class is holding test results.
 */
public class ResultsHolder {
    private static ResultsHolder instance;
    private List<Long> res;

    private ResultsHolder() {
        res = new ArrayList<Long>();
    }

    public static synchronized ResultsHolder getInstance() {
        if (instance == null) {
            instance = new ResultsHolder();
        }
        return instance;
    }

    public synchronized void setResult(long res) {
        this.res.add(res);

    }

    public PerformanceTestResult getResult() {
        Collections.sort(res);
        long totalTime = 0;
        for (Long time : res) {
            totalTime += time;
        }
        return new PerformanceTestResult(totalTime, res.get(0), res.get(res.size() - 1));
    }

}
