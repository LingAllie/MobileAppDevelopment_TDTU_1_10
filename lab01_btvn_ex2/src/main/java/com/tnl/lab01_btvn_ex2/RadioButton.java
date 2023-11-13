package com.tnl.lab01_btvn_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioButton extends AppCompatActivity {

    private RadioGroup rgOS;
    private Button btnResults;
    private TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_buttons);

        rgOS = findViewById(R.id.rgOS);
        btnResults = findViewById(R.id.btnResults);
        tvResults = findViewById(R.id.tvResults);

        btnResults.setOnClickListener(v -> tvResults.setText("The following were selected..." +
                "\nAndroid : " + (rgOS.getCheckedRadioButtonId() == R.id.rbAndroid) +
                "\niOS : " + (rgOS.getCheckedRadioButtonId() == R.id.rbiOS) +
                "\nWindows : " + (rgOS.getCheckedRadioButtonId() == R.id.rbWindows) +
                "\nRIM : " + (rgOS.getCheckedRadioButtonId() == R.id.rbRIM)));
    }
}