package com.tnl.lab04_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

public class Ex1Activity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> data;
    private CustomAdapter adapter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);

        context = this;

        initViews();
        initData();
    }

    private void initViews() {
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String item = data.get(position);

            Toast.makeText(Ex1Activity.this, item, Toast.LENGTH_SHORT).show();
        });
    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            data.add("Item " + i);
        }

        adapter = new CustomAdapter(this, R.layout.custom_row, data);
        listView.setAdapter(adapter);
    }
}