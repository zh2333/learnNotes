package com.interview.study.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{ System.out.println("*******召唤神龙");});

        for (int i = 1; i <= 7 ;i++) {
            final int tmpInt = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t 收集" + tmpInt +" 龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
