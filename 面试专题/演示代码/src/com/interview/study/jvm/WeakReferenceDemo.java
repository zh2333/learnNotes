package com.interview.study.jvm;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);

        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.out.println(weakReference.get());

        System.gc();

        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
