package com.interview.study.jvm;

import java.util.concurrent.TimeUnit;

public class UnableCreateThreadDemo {
    public static void main(String[] args) {
        for (int i = 1; ; i++) {
            System.out.println("****************i:" + i);

            new Thread(() -> {
                try { TimeUnit.SECONDS.sleep(Integer.MAX_VALUE); } catch (InterruptedException e) { e.printStackTrace(); }
            }, String.valueOf(i)).start();
        }
    }
}
