package com.homenet.threading;

import com.homenet.threading.impl.FibCalcImpl;
import com.homenet.threading.impl.PerformanceTesterImpl;
import com.homenet.threading.impl.ThreadRunnerHelper;

import java.lang.management.ManagementFactory;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author denis.bilyk.
 */
public class Runner {
    private static PerformanceTester tester = new PerformanceTesterImpl();
    private static ThreadRunnerHelper helper = new ThreadRunnerHelper();

    public static void main(String[] args) throws InterruptedException {
        int threadPool = 1;
        int calculatinCount = 5;
        int fib = 10;
        Runnable runnable = new TestTask(fib);
        tester.runPerformanceTest(runnable, calculatinCount, threadPool);

    }


    final static class TestTask implements Runnable {
        private int n;

        TestTask(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            FibCalc calc = new FibCalcImpl();
            long start = System.nanoTime();
            calc.fib(n);
            long time = System.nanoTime() - start;
            System.out.println("In thread:" + time);
            helper.setResult(time);
        }
    }
}
