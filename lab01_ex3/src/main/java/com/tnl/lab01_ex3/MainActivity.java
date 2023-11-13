package com.tnl.lab01_ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtFullname;
    private RadioGroup rgGender;
    private RadioGroup rgLike;
    private EditText txtEmail;
    private EditText txtAddress;
    private CheckBox chbSoccer;
    private CheckBox chbSwim;
    private CheckBox chbJogging;
    private RatingBar rbStar;
    private SeekBar sbToeic;
    private Switch sReceive;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFullname = findViewById(R.id.txtFullname);
        rgGender = findViewById(R.id.rgGender);
        rgLike = findViewById(R.id.rgLike);
        txtEmail = findViewById(R.id.txtEmail);
        txtAddress = findViewById(R.id.txtAddress);
        chbSoccer = findViewById(R.id.chbSoccer);
        chbSwim = findViewById(R.id.chbSwim);
        chbJogging = findViewById(R.id.chbJogging);
        rbStar = findViewById(R.id.rbStar);
        sbToeic = findViewById(R.id.sbToeic);
        sReceive = findViewById(R.id.sReceive);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

    }

    private boolean handleClickEvent() {
        String fullName = txtFullname.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String address = txtAddress.getText().toString().trim();

        if (fullName.isEmpty()) {
            Toast.makeText(this, "Please enter fullname", Toast.LENGTH_SHORT).show();
            txtFullname.requestFocus();
            return true;
        }

        else if (email.isEmpty()) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            txtEmail.requestFocus();
            return true;
        } else if (!email.contains("@")) {
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            txtEmail.requestFocus();
            return true;
        }

        else if (address.isEmpty()) {
            Toast.makeText(this, "Please enter address", Toast.LENGTH_SHORT).show();
            txtAddress.requestFocus();
            return true;
        }

        return false;
    }

    @Override
    public void onClick(View v) {

        if (!handleClickEvent()) {
            String fullName = txtFullname.getText().toString();
            boolean isMale = (rgGender.getCheckedRadioButtonId() == R.id.rbMale);
            boolean likeMale = (rgLike.getCheckedRadioButtonId() == R.id.rbLmale);
            boolean likeBoth = (rgLike.getCheckedRadioButtonId() == R.id.rbLboth);
            String email = txtEmail.getText().toString();
            String address = txtAddress.getText().toString();
            boolean soccer = chbSoccer.isChecked();
            boolean swim = chbSwim.isChecked();
            boolean jog = chbJogging.isChecked();
            String star = String.valueOf(rbStar.getRating()) ;
            String score = String.valueOf(sbToeic.getProgress() * 5);
            boolean receive = sReceive.isChecked();


            Intent intent = new Intent(this, ReviewActivity.class);

            intent.putExtra("name", fullName);
            intent.putExtra("gender", isMale);
            intent.putExtra("email", email);
            intent.putExtra("address", address);
            intent.putExtra("like", likeMale);
            intent.putExtra("both", likeBoth);
            intent.putExtra("soccer", soccer);
            intent.putExtra("swim", swim);
            intent.putExtra("jog", jog);
            intent.putExtra("star", star);
            intent.putExtra("score", score);
            intent.putExtra("receive", receive);

            startActivity(intent);

            finish();
        }



    }// on click
}