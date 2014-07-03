package com.homenet.threading;

import com.homenet.threading.impl.FibCalcImpl;
import com.homenet.threading.impl.PerformanceTesterImpl;
import com.homenet.threading.impl.ResultsHolder;

/**
 * @author denis.bilyk.
 * Main class to run performance test
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

    /**
     * Class to run performance test in thread.
     */
    final static class TestTask implements Runnable {
        private int fibonacciNumber;
        private ResultsHolder holder = ResultsHolder.getInstance();

        TestTask(int fibonacciNumber) {
            this.fibonacciNumber = fibonacciNumber;
        }

        @Override
        public void run() {
            FibCalc calc = new FibCalcImpl();
            long start = System.nanoTime();
            calc.fib(fibonacciNumber);
            long time = System.nanoTime() - start;
            holder.setResult(time);
        }
    }
}
