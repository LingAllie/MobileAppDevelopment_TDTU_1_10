package com.tnl.lab09_ex4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Ex4Activity extends AppCompatActivity implements MyService.MyServiceCallback {
    private Intent intent;
    private MyService myService;
    private TextView tvInfo;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            myService = binder.getService();
            myService.setCallback(Ex4Activity.this);

            Log.e("TAG", "onServiceConnected called");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("TAG", "onServiceDisconnected called");

            myService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex4);

        tvInfo = findViewById(R.id.info);
        intent = new Intent(this, MyService.class);
    }

    public void startService(View view) {
        startService(intent);
    }

    public void stopService(View view) {
        stopService(intent);
    }

    public void bindService(View view) {
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public void unbindService(View view) {
        unbindService(connection);
    }

    public void callService(View view) {
        if (myService == null) {
            Toast.makeText(this, "Service is not connected", Toast.LENGTH_SHORT).show();
            return;
        }
        int x = myService.add(5, 10);
        Toast.makeText(this, "Result is " + x, Toast.LENGTH_SHORT).show();
    }

    public void stopLoop(View view) {
        if (myService == null) {
            Toast.makeText(this, "Service is not connected", Toast.LENGTH_SHORT).show();
            return;
        }
        myService.stopLoop();
    }

    @Override
    public void onCounterChanged(int value) {
        tvInfo.setText("Value: " + value);
    }
}