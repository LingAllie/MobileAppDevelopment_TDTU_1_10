package com.tnl.lab01_e1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsername, txtPassword;
    private CheckBox chbRemember;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        chbRemember = findViewById(R.id.chbRemember);
        btnLogin = findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(v -> handleClickEvent());

        chbRemember.setOnCheckedChangeListener((compoundButton, checked) -> {
            String message = checked ? "Tài khoản của bạn sẽ được ghi nhớ" : "Bạn sẽ cần phải đăng nhập trong các lần tiếp theo";
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        });
    }// on create

    private void handleClickEvent() {
        String username = txtUsername.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
            txtUsername.requestFocus();
        }
        else if (password.isEmpty()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            txtPassword.requestFocus();
        }
        else if (username.equals("admin") && password.equals("123456")) {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);

            finish();
        }
        else {
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
        }
    }
}