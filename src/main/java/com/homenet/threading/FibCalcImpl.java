package com.homenet.threading;

/**
 * @author denis.bilyk.
 */
public class FibCalcImpl implements FibCalc {
    @Override
    public long fib(int n) {
        int tmp = 0;
        int first = 0;
        int second = 1;
        int sum = 0;

        for (int k = 1; k <= n; k++) {
            tmp = first;
            first = second;
            second = tmp + second;
            sum += first;

        }
        return sum;
    }

}
