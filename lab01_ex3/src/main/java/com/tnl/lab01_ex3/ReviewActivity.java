package com.tnl.lab01_ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewActivity extends AppCompatActivity {

    private TextView tvFullname, tvGender, tvLike,
            tvEmail, tvAddress, tvHobbies,
            tvSA, tvToeic, tvReceive;
    private Button btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        tvFullname = findViewById(R.id.tvFullname);
        tvGender = findViewById(R.id.tvGender);
        tvLike = findViewById(R.id.tvLike);
        tvEmail = findViewById(R.id.tvEmail);
        tvAddress = findViewById(R.id.tvAddress);
        tvHobbies = findViewById(R.id.tvHobbies);
        tvSA = findViewById(R.id.tvSA);
        tvToeic = findViewById(R.id.tvToeic);
        tvReceive = findViewById(R.id.tvReceive);
        btnClose = findViewById(R.id.btnClose);

        Intent intent = getIntent();

        String fullName = intent.getStringExtra("name");
        boolean isMale = intent.getBooleanExtra("gender", false);
        boolean likeMale = intent.getBooleanExtra("like", false);
        boolean likeBoth = intent.getBooleanExtra("both", false);
        String email = intent.getStringExtra("email");
        String address = intent.getStringExtra("address");
        boolean soccer = intent.getBooleanExtra("soccer", false);
        boolean swim = intent.getBooleanExtra("swim", false);
        boolean jog = intent.getBooleanExtra("jog", false);
        String star = intent.getStringExtra("star");
        String score = intent.getStringExtra("score");
        boolean receive = intent.getBooleanExtra("receive", false);

//        name
        tvFullname.setText(fullName);


//        gender
        if (isMale) {
            tvGender.setText("Male");
        } else {
            tvGender.setText("Female");
        }

//        like
        if (likeMale) {
            tvLike.setText("Male");
        } else if (likeBoth){
            tvLike.setText("Male and Female");
        } else {
            tvLike.setText("Female");
        }

//        email
        tvEmail.setText(email);


//        address
        tvAddress.setText(address);


//        hobbies
        if(soccer) {
            tvHobbies.setText("Soccer");
            if(swim) {
                tvHobbies.setText("Soccer, swimming");
                if(jog) {
                    tvHobbies.setText("Soccer, swimming, jogging");
                }
            } else if (jog) {
                tvHobbies.setText("Soccer, jogging");
            }
        } else if (swim) {
            tvHobbies.setText("Swimming");
            if (jog) {
                tvHobbies.setText("Swimming, jogging");
            }
        } else if (jog) {
            tvHobbies.setText("Jogging");
        } else {
            tvHobbies.setText("Another sports");
        }

//        Swimming ability
        if (star == null) {
            tvSA.setText("0/5");
        } else {
            tvSA.setText(star+"/5");
        }

//        Toeic score
        tvToeic.setText(score);


//        Receive
        if (receive) {
            tvReceive.setText("Yes");
        } else {
            tvReceive.setText("No");
        }

//        close app button
        btnClose.setOnClickListener(v -> finish());
    }// on create
}