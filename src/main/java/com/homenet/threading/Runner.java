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

    public static void main(String[] args) throws InterruptedException {
        int threadPool = Integer.valueOf(args[0]);
        int calculationCount = Integer.valueOf(args[1]);
        int fib = Integer.valueOf(args[2]);
        Runnable runnable = new TestTask(fib);
        PerformanceTestResult result = tester.runPerformanceTest(runnable, calculationCount, threadPool);
        System.out.println("Result: " + result);

    }

    //TODO java docs
    final static class TestTask implements Runnable {
        //TODO variables names everywhere
        private int n;
        private ThreadRunnerHelper helper = ThreadRunnerHelper.getInstance();

        TestTask(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            FibCalc calc = new FibCalcImpl();
            long start = System.nanoTime();
            calc.fib(n);
            long time = System.nanoTime() - start;
            helper.setResult(time);
        }
    }
}
