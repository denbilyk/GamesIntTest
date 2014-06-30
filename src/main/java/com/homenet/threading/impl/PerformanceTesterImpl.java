package com.homenet.threading.impl;

import com.homenet.threading.PerformanceTestResult;
import com.homenet.threading.PerformanceTester;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;

/**
 * @author denis.bilyk.
 */
public class PerformanceTesterImpl implements PerformanceTester {
    private Set<Future> tasks = new HashSet<Future>();
    private static ThreadRunnerHelper helper = new ThreadRunnerHelper();

    @Override
    public PerformanceTestResult runPerformanceTest(final Runnable task, int executionCount, int threadPoolSize) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);


        for (int i = 0; i< executionCount ; i++){
            Future f = executorService.submit(task);
            tasks.add(f);
        }
        executorService.shutdown();


        return null;

    }



}
