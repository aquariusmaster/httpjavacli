package com.anderb.onseo.httpcli;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by andreb on 27.06.17.
 */
public class HttpSenderApplication {

    public static void main(String[] args) throws Exception {

        Properties properties = PropertiesReader.getProperties();
        System.out.println(properties);
        int poolSize = Integer.parseInt(properties.getProperty("pool_size"));
        String url = properties.getProperty("url");
        int requestPerSecond = Integer.parseInt(properties.getProperty("request_per_second"));

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(poolSize);

//        for (int i = 0; i < requestPerSecond; i++){
//            Runnable worker = new HttpSenderThread(url);
//            executor.execute(worker);
////            Thread.sleep(1000/requestPerSecond);
//        }
        Runnable worker = new HttpSenderThread(url);
        executor.scheduleAtFixedRate(worker, 0, 1000/requestPerSecond, TimeUnit.MILLISECONDS);
//        executor.shutdown();
//        while (!executor.isTerminated()) {
//        }
//        System.out.println("Finished all threads");
    }

}
