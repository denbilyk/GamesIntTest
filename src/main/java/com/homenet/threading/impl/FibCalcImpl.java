package com.homenet.threading.impl;

import com.homenet.threading.FibCalc;

/**
 * @author denis.bilyk.
 * Implementation of fibonacci calculator without recurcivity.
 */
public class FibCalcImpl implements FibCalc {
    @Override
    public long fib(int n) {
        long fibonacci = 0;
        long num = 0;
        long nextNum = 1;
        for (int loop = 0; loop < n; loop ++)
        {
            fibonacci = num + nextNum;
            num = nextNum;
            nextNum = fibonacci;
        }
        return fibonacci;
    }
}
