package com.example.a10580.pocpro;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class HearBeat extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


       // callMul();

        doNothing();


        return START_STICKY;
    }


    void doNothing()
    {
        System.out.println("sdsdsdds");
    }



    void runningTask()
    {
        System.out.println("runnn mul");

    }


    Handler handler;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {


            callMul();
            runningTask();

        }
    };


    void callMul()
    {

        handler = new Handler();
        handler.postDelayed(runnable,3000);


    }





}
