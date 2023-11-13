package com.tnl.lab04_ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Ex3Activity extends AppCompatActivity implements View.OnClickListener{

    private ListView mListView;
    private List<Phone> mData;
    private PhoneAdapter mAdapter;
    private EditText mPhoneName;
    private Button mBtnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex3);

        initViews();
        initData();
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add(new Phone(R.drawable.apple, "Apple"));
        mData.add(new Phone(R.drawable.old, "Classic Phone"));
        mData.add(new Phone(R.drawable.spotlight, "Spotlight"));
        mData.add(new Phone(R.drawable.nokia, "Nokia"));
        mData.add(new Phone(R.drawable.television, "Television"));
        mData.add(new Phone(R.drawable.oppo, "Oppo"));
        mData.add(new Phone(R.drawable.hp, "HP"));
        mData.add(new Phone(R.drawable.mac, "MAC"));
        mData.add(new Phone(R.drawable.smartphone, "Smartphone"));
        mData.add(new Phone(R.drawable.battery, "Battery"));
        mData.add(new Phone(R.drawable.headphones, "Headphones"));
        mData.add(new Phone(R.drawable.samsung, "Samsung"));
        mData.add(new Phone(R.drawable.microphone, "Microphone"));
        mData.add(new Phone(R.drawable.mouse, "Mouse"));
        mData.add(new Phone(R.drawable.laptop, "Laptop"));
        mData.add(new Phone(R.drawable.radio, "Radio"));

        mAdapter = new PhoneAdapter(this, R.layout.custom_row, mData);
        mListView.setAdapter(mAdapter);
    }

    private void initViews() {

        mListView = findViewById(R.id.listView);
        mPhoneName = findViewById(R.id.txtName);
        mBtnAdd = findViewById(R.id.btnAdd);

        mBtnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = mPhoneName.getText().toString();
        mPhoneName.setText("");

        Phone phone = new Phone(R.drawable.battery, name);
        mData.add(0, phone);

        mAdapter.notifyDataSetChanged();
    }
}