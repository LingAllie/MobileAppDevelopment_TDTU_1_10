package com.tnl.lab05_ex3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;
import java.util.ArrayList;

public class Ex3Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<String> data;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex3);

        initViews();
        initData();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            data.add("Student " + i);
        }

        adapter = new StudentAdapter(data);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String item = data.get(position);
                Toast.makeText(Ex3Activity.this, item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRemoveClick(int position) {
                data.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
}

