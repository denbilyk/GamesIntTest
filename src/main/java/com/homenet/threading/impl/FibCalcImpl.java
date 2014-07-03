package com.homenet.threading.impl;

import com.homenet.threading.FibCalc;

/**
 * @author denis.bilyk.
 */
public class FibCalcImpl implements FibCalc {
    @Override
    public long fib(int n) {
        long fibonacci = 0;
        long num = 0;
        //TODO maybe nextNum variable name
        long num2 = 1;
        for (int loop = 0; loop < n; loop ++)
        {
            fibonacci = num + num2;
            num = num2;
            num2 = fibonacci;
        }
        return fibonacci;
    }
}
