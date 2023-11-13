package com.tnl.lab03_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Ex2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<String> students;
    private GridView grid;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2);

        grid = findViewById(R.id.grid);

        students = new ArrayList<>();
        for (int i = 1; i < 126; i++) {
            students.add("Students " + i);
        }

        adapter = new ArrayAdapter<>(this, R.layout.my_template, students);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String studentPosition = students.get(position);
        Toast.makeText(this, studentPosition, Toast.LENGTH_SHORT).show();
    }
}