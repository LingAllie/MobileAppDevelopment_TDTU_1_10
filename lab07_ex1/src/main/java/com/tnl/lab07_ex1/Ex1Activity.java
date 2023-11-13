package com.tnl.lab07_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Ex1Activity extends AppCompatActivity {

    private TextView tvLaunch, tvPause;
    private SharedPreferences pref;

    private  int launchCount, pauseCount;
    private  int color;

    private long lastStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);

        tvLaunch = findViewById(R.id.tvLaunch);
        tvPause = findViewById(R.id.tvPause);

        pref = getPreferences(MODE_PRIVATE);

        launchCount = pref.getInt("launch", 0);
        pauseCount = pref.getInt("pause", 0);
        color = pref.getInt("color", Color.BLACK);

        launchCount++;

        tvLaunch.setText(String.valueOf(launchCount));
        tvPause.setText(String.valueOf(pauseCount));

        tvLaunch.setTextColor(color);
        tvPause.setTextColor(color);

    }

    @Override
    protected void onStop() {
        super.onStop();
        lastStop = System.currentTimeMillis();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        long duration = System.currentTimeMillis() - lastStop;
//        Log.e("TAG", "Duration" + duration);
        if (duration > 1 * 1000) {
            pauseCount++;
            tvPause.setText(String.valueOf(pauseCount));
        }
    }

    @Override
    protected void onDestroy() {

        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("color", tvLaunch.getCurrentTextColor());
        editor.putInt("launch", launchCount);
        editor.putInt("pause", pauseCount);

        editor.apply();

        super.onDestroy();
    }

    public void changeTextColor(View view) {
        ColorDrawable background = (ColorDrawable) view.getBackground();
        int color = background.getColor();
        setTextColor(color);
    }

    private void setTextColor(int color) {
        tvLaunch.setTextColor(color);
        tvPause.setTextColor(color);
    }

}