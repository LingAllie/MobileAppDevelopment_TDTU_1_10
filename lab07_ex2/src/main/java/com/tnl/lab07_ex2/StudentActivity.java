package com.tnl.lab07_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Random;
import java.util.SimpleTimeZone;

public class StudentActivity extends AppCompatActivity {

    private EditText txtName, txtEmail;
    private Student student;
    private Random rand = new Random();

    private int studentPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);


        student = getIntent().getParcelableExtra("student");
        studentPosition = getIntent().getIntExtra("position", -1);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);

        if (student != null) {
            txtName.setText(student.getName());
            txtEmail.setText(student.getEmail());
            setTitle("Điều chỉnh sinh viên");
        }
        else {
            setTitle("Tạo sinh viên mới");
        }
    }

    public void saveStudent(View view) {

        String name = txtName.getText().toString();
        String email = txtEmail.getText().toString();

        Intent result = new Intent();

        if ( student == null) {
            int level = rand.nextInt(3) + 1;
            student = new Student( name, email, level);

        } else {
            student.setName(name);
            student.setEmail(email);

            result.putExtra("position", studentPosition);
        }

        result.putExtra("student", student);
        setResult(RESULT_OK, result);
        finish();
    }
}