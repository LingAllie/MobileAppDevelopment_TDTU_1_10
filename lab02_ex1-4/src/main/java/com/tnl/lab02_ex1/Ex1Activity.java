package com.tnl.lab02_ex1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Ex1Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText num1, num2;
    private RadioGroup operation;
    private Button btnCalc;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);

        num1 = findViewById(R.id.number1);
        num2 = findViewById(R.id.number2);
        operation = findViewById(R.id.rgOperation);
        btnCalc = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        btnCalc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Integer n1 = 0, n2 = 0;
        try {
            n1 = Integer.parseInt(num1.getText().toString());
            n2 = Integer.parseInt(num2.getText().toString());
        } catch (Exception e) {
            tvResult.setText("Invalid input number");
            tvResult.setTextColor(Color.RED);
            return;
        }

        int operatorId = operation.getCheckedRadioButtonId();
        double result = 0;
        if (operatorId == R.id.add) {
            result = n1 + n2;
        } else if (operatorId == R.id.subtract) {
            result = n1 - n2;
        } else if (operatorId == R.id.multiply) {
            result = n1 * n2;
        } else  if (operatorId == R.id.divide) {
            if (n2 != 0) {
                result = n1 / n2;
            } else {
                tvResult.setText("Invalid operation");
                tvResult.setTextColor(Color.RED);
                return;
            }
        } else {
            tvResult.setText("Please select an operation");
            tvResult.setTextColor(Color.RED);
            return;
        }

        tvResult.setText(String.valueOf(result));
        tvResult.setTextColor(Color.GREEN);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        String result = tvResult.getText().toString();
        outState.putString("label", result);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String label = savedInstanceState.getString("label");
        tvResult.setText(label);
    }
}