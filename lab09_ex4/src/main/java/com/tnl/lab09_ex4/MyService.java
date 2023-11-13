package com.tnl.lab09_ex4;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {

    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    public interface MyServiceCallback {
        void onCounterChanged(int value);
    }

    private MyServiceCallback connectedActivity;
    private boolean stillLoop = true;


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

        looping();

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("TAG", "My service onBind called");
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("TAG", "My service onUnbind called");
        return super.onUnbind(intent);
    }

    public int add(int a, int b) {
        return a + b;
    }

    public void setCallback(MyServiceCallback activity) {
        this.connectedActivity = activity;
    }

    public void stopLoop() {
        stillLoop = false;
    }

    private void looping() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while (stillLoop) {
                    count++;
                    Log.e("TAG", "Looping " + count);

                    if (count % 5 == 0) {
                        if (connectedActivity != null) {
                            connectedActivity.onCounterChanged(count);
                        }
                    }

                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
