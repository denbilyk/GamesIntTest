package com.homenet.threading.impl;

import com.homenet.threading.PerformanceTestResult;
import com.homenet.threading.PerformanceTester;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;

/**
 * @author denis.bilyk.
 */
public class PerformanceTesterImpl implements PerformanceTester {

    @Override
    public PerformanceTestResult runPerformanceTest(final Runnable task, int executionCount, int threadPoolSize) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < executionCount; i++) {
           Future f = executorService.submit(task);
           list.add(f);
        }

        executorService.shutdown();
        for (Future future : list) {
            while (!future.isDone()){}
        }
        return ThreadRunnerHelper.getInstance().getResult();
    }


}
