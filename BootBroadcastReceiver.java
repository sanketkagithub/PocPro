package com.example.a10580.pocpro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver {
    static final String ACTION = "android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        // BOOT_COMPLETED” start Service
        if (intent.getAction().equals(ACTION)) {
            //Service

            System.out.println("bootCompleted => pocpro");

            Intent serviceIntent = new Intent(context, HearBeat.class);
            context.startService(serviceIntent);
        }
    }
}