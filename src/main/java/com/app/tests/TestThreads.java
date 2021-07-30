package com.app.tests;

import com.app.runnables.AppThread;

public class TestThreads {
    public static void main(String[] args) {
        AppThread thread1 = new AppThread(); // State is New when a thread instance created
        AppThread thread2 = new AppThread();
        AppThread thread3 = new AppThread();

        thread1.start(); // Runnable State when start
        thread2.start();
        thread3.start();

        // execute run // Running
        // Job completed - Terminated/Dead
        // Blocked State / Waiting State
    }
}
