package com.tnl.lab09_ex3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {

    public MyService() {
        Log.e("TAG", "MyService Created");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "MyService onCreate Called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "MyService ondestroy Called");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("TAG", "MyService onStartCommand Called");

        new Thread(new Runnable() {
            @Override
            public void run() {
                log();
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    private void log() {
        for (int i = 1; i <= 10; i++) {
            Log.e("TAG", Thread.currentThread().getName() + ": Service is running: " + i);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
