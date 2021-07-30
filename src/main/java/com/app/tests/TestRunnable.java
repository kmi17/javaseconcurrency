package com.app.tests;

import com.app.runnables.AppThread;

import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestRunnable {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(new File("/Users/camychhetri/Desktop/Ex_Files_Java_EE_Concurrency/Exercise Files/Chapter2/02_03/begin/sample.txt")))) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(Thread.currentThread().getName() + " reading the line: " + line);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AppThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
//
//        Thread thread = new Thread(runnable);
//        thread.start();

        // Use Executor
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(runnable);
    }
}
