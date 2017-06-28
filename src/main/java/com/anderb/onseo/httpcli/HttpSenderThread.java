package com.anderb.onseo.httpcli;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by andreb on 27.06.17.
 */
public class HttpSenderThread implements Runnable {

    private String url;

    public HttpSenderThread(String url){
        this.url =url;
    }

    public void run() {
        long start = System.currentTimeMillis();
        int code = sendGet(url);
        System.out.println(Thread.currentThread().getName()+" - " + (System.currentTimeMillis() - start) + " milliseconds result code: " + code);
    }

    // HTTP GET request
    private int sendGet(String url) {

        int returnCode = 0;
        try{
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            //get responseCode
            returnCode = con.getResponseCode();

        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return returnCode;
    }

    @Override
    public String toString(){
        return this.url;
    }
}
