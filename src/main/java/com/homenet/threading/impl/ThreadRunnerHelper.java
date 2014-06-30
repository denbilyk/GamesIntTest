package com.homenet.threading.impl;

import com.homenet.threading.PerformanceTestResult;

import java.util.*;

/**
 * @author denis.bilyk.
 */
public class ThreadRunnerHelper  {
    private List<Long> res;

    public ThreadRunnerHelper() {
        res = new ArrayList<Long>();
    }

    public synchronized void setResult(long res){
         this.res.add(res);

    }

    public PerformanceTestResult getResult(){
        Collections.sort(res);
        long sum = 0;
        for (Long re : res) {
             sum+= re;
        }
        System.out.println(res);
        return new PerformanceTestResult(sum, res.get(0), res.get(res.size()-1));
    }

}
