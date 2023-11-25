package com.tnl.lab10_ex3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class Ex3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex3);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new MySettingFragment())
                .commit();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        Log.e("TAG", "Wifi " + pref.getBoolean("wifi", true));
        Log.e("TAG", "Sound " + pref.getBoolean("sound", true));
        Log.e("TAG", "Email " + pref.getString("email", null));
        Log.e("TAG", "Song " + pref.getString("song", null));

    }


}