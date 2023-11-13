package com.tnl.lab02_ex6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Ex6Activity extends AppCompatActivity {

    private Button Sqrt, sqrtSym, mod, div, equal, dot, add, sub, mul, divide,
            zero, one, two, three, four, five, six, seven, eight, nine;
    private TextView viewOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex6);

        Sqrt = findViewById(R.id.Sqrt);
        sqrtSym = findViewById(R.id.sqrtSym);
        mod = findViewById(R.id.mod);
        div = findViewById(R.id.div);
        equal = findViewById(R.id.equal);
        dot = findViewById(R.id.dot);
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        mul = findViewById(R.id.mul);
        divide = findViewById(R.id.divide);
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        viewOP = findViewById(R.id.viewOP);


        View.OnClickListener buttonClickListener = v -> {
            if (v instanceof Button) {
                Button clickedButton = (Button) v;
                String buttonText = clickedButton.getText().toString();
                viewOP.append(buttonText + " ");
            }
        };


        Sqrt.setOnClickListener(buttonClickListener);
        sqrtSym.setOnClickListener(buttonClickListener);
        mod.setOnClickListener(buttonClickListener);
        div.setOnClickListener(buttonClickListener);
        equal.setOnClickListener(buttonClickListener);
        dot.setOnClickListener(buttonClickListener);
        add.setOnClickListener(buttonClickListener);
        sub.setOnClickListener(buttonClickListener);
        mul.setOnClickListener(buttonClickListener);
        divide.setOnClickListener(buttonClickListener);
        zero.setOnClickListener(buttonClickListener);
        one.setOnClickListener(buttonClickListener);
        two.setOnClickListener(buttonClickListener);
        three.setOnClickListener(buttonClickListener);
        four.setOnClickListener(buttonClickListener);
        five.setOnClickListener(buttonClickListener);
        six.setOnClickListener(buttonClickListener);
        seven.setOnClickListener(buttonClickListener);
        eight.setOnClickListener(buttonClickListener);
        nine.setOnClickListener(buttonClickListener);
    }
}