package com.app.tests;

import com.app.dao.UserDao;
import com.app.runnables.AppThread;
import com.app.runnables.UserProcessor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestExecutors {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        String fileName = "/Users/camychhetri/Desktop/Ex_Files_Java_EE_Concurrency/Exercise Files/Chapter3/03_07/begin/javaseconcurrency/new_users.txt";
        List<String> users = getUser(fileName);

        for (String user : users) {
            Future<Integer> futureResult = service.submit(new UserProcessor(user, new UserDao()));
            try {
                System.out.println("Result: " + futureResult.get());
            } catch (InterruptedException e) {
                Logger.getLogger(TestExecutors.class.getName()).log(Level.SEVERE, null, e);
            } catch (ExecutionException e) {
                Logger.getLogger(TestExecutors.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        service.shutdown();
        System.out.println("Main execution over!!!");
    }

    public static List<String> getUser(String fileName){
        List<String> users = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
               users.add(line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestExecutors.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            Logger.getLogger(TestExecutors.class.getName()).log(Level.SEVERE, null, e);
        }

        return users;
    }
}
