package com.homenet.threading.impl;

import com.homenet.threading.FibCalc;

/**
 * @author denis.bilyk.
 */
public class FibCalcImpl implements FibCalc {
    @Override
    public long fib(int n) {
        if (n <= 1) return n;
        else return fib(n - 1) + fib(n - 2);
    }
}
