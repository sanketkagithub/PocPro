package com.example.a10580.pocpro;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceCallMull();
        printRunningBaseUrl();
        b1 = (Button) findViewById(R.id.button1);

        restartServiceAfterEveryFixedSec();

        getApppps();
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startAlert();
            }
        });

        saveDataWorldReadable();
    }


    void saveDataWorldReadable() {

        SharedPreferences sp = getSharedPreferences("wread", MODE_WORLD_READABLE);
        SharedPreferences.Editor spe = sp.edit();
        spe.putString("myName", "Sanket is in Sp");
        spe.apply();

        if (spe.commit()) {
            Toast.makeText(this, "comitted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT).show();
        }


    }


    public void startAlert() {
        EditText text = (EditText) findViewById(R.id.time);
        int i = Integer.parseInt(text.getText().toString());
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (i * 1000), pendingIntent);
        Toast.makeText(this, "Alarm set in " + i + " seconds", Toast.LENGTH_LONG).show();
    }

    void printRunningBaseUrl() {
        String androidId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        WifiManager m_wm = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        String m_wlanMacAdd = m_wm.getConnectionInfo().getMacAddress();

        // System.out.println("androidIdShow   " +   androidId );
        Log.i("androidIdShow => ", androidId);
        Log.i("m_wlanMacAdd => ", m_wlanMacAdd);
    }

    void restartServiceAfterEveryFixedSec() {
        Intent ishintent = new Intent(this, HearBeat.class);
        PendingIntent pintent = PendingIntent.getService(this, 0, ishintent, 0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
           alarm.cancel(pintent);
        // alarm.setRepeating(AlarmManager.RTC_WAKEUP, 2, 2, pintent);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, 2, 2, pintent);
    }




    void startServiceCallMull() {
        Intent intent = new Intent(this, HearBeat.class);
        startService(intent);
    }


    public static final String TAG = "appp";

    @RequiresApi(api = Build.VERSION_CODES.N)
    void getApppps() {
        final PackageManager pm = getPackageManager();
//get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);


        for (ApplicationInfo packageInfo : packages) {
            Log.d(TAG, "Installed package :" + packageInfo.packageName);
            Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
            Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
        }

        List<String> packNamesList = new ArrayList<>();

        for (ApplicationInfo applicationInfo :
                packages) {
            packNamesList.add(applicationInfo.packageName);
        }


        String n4ePackName = "com.nischinttechnologies.n4e";
        System.out.println("MyPackageNames " + packNamesList);

        if (packNamesList.contains(n4ePackName)) {
            Toast.makeText(this, "N4E is Available", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "N4E is not Available ", Toast.LENGTH_SHORT).show();
        }

        List<Car> carList = new ArrayList<>();

        carList.add(new Car("lambo", "blue"));
        carList.add(new Car("tarzan", "yellow"));
        carList.add(new Car("skoda", "red"));

        Car car = new Car();
        car.setColor("blue");
        car.setName("lambo");


        if (carList.contains(car.hashCode())) {
            Toast.makeText(this, "Yes ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Noooooo", Toast.LENGTH_SHORT).show();
        }


        Car car1 = new Car();
        car1.setName("nnn");
        car1.setColor("rrr");


        Car car2 = new Car();
        car2.setName("nnn");
        car2.setColor("rrr");

        String s1, s2;
        s1 = "sanket";
        s2 = "sanket";

        if (car1.hashCode() == car2.hashCode()) {
            Toast.makeText(this, "same hashcode", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "different hashcode", Toast.LENGTH_SHORT).show();
        }
        if (s1.hashCode() == s2.hashCode()) {
            Toast.makeText(this, "s1 same hashcode", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "s1 different hashcode", Toast.LENGTH_SHORT).show();
        }


    }


}

class Car {
    String name, color;

    public Car(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Car() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
