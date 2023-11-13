package com.tnl.lab01_btvn_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Checkboxes extends AppCompatActivity {

    private CheckBox chbAndroid, chbiOS, chbWindows, chbRIM;

    private Button btnResults;
    private TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_boxes);

        chbAndroid = findViewById(R.id.chbAndroid);
        chbiOS = findViewById(R.id.chbiOS);
        chbWindows = findViewById(R.id.chbWindows);
        chbRIM = findViewById(R.id.chbRIM);

        btnResults = findViewById(R.id.btnResults);

        tvResults = findViewById(R.id.tvResults);

        btnResults.setOnClickListener(v -> tvResults.setText("The following were selected..." +
                "\nAndroid : " + chbAndroid.isChecked() +
                "\niOS : " + chbiOS.isChecked() +
                "\nWindows : " + chbWindows.isChecked() +
                "\nRIM : " + chbRIM.isChecked()));
    }
}