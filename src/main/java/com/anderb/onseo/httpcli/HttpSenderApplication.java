package com.anderb.onseo.httpcli;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by andreb on 27.06.17.
 */
public class HttpSenderApplication {

    public static void main(String[] args) throws Exception {

        Properties properties = PropertiesReader.getProperties();
        System.out.println(properties);
        String url = properties.getProperty("url");
        int requestPerSecond = Integer.parseInt(properties.getProperty("request_per_second"));
        int duration = Integer.parseInt(properties.getProperty("duration"));

        ExecutorService executor = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        long requestCount = 0;
        long period = 1000/requestPerSecond;
        System.out.println(period);

        while((System.currentTimeMillis() - start) < duration*1000){

            Runnable worker = new HttpSenderThread(url);
            executor.submit(worker);
            Thread.sleep(period);
            requestCount++;
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        long finishedTime = System.currentTimeMillis() - start;
        System.out.println("Finished in: " + finishedTime + " milliseconds");
        System.out.println("Total requests: " + requestCount + ".");
        System.out.println(String.format("%s request per seconds", (double) requestCount / (double)(finishedTime / 1000) ));
    }
}
