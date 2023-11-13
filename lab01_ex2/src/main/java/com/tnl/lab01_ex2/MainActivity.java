package com.tnl.lab01_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtMessage;
    private Button btnMessage;
    private TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMessage = findViewById(R.id.txtMessage);
        btnMessage = findViewById(R.id.btnMessage);
        tvResult = findViewById(R.id.tvResult);

        btnMessage.setOnClickListener(v -> {
            String message = txtMessage.getText().toString().trim();
            if (message.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter message", Toast.LENGTH_SHORT).show();
            }
            else {
                tvResult.setText(message);
                txtMessage.setText("");
            }

        });

        txtMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String message = txtMessage.getText().toString().toLowerCase();
                if (message.equals("on")) {
                    btnMessage.setEnabled(true);
                } else if (message.equals("off")) {
                    btnMessage.setEnabled(false);
                }
            }
        });
    } // on create
}