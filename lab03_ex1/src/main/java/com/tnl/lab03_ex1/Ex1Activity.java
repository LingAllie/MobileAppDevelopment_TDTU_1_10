package com.tnl.lab03_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Ex1Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<String> students;
    private ListView list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);

        list = findViewById(R.id.list);

        students = new ArrayList<>();
        for (int i = 1; i < 26; i++) {
            students.add("TDT Students " + i);
        }

        adapter = new ArrayAdapter<>(this, R.layout.my_template, students);
        list.setAdapter(adapter);

        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String studentPosition = students.get(position);
        Toast.makeText(this, studentPosition, Toast.LENGTH_SHORT).show();
    }
}