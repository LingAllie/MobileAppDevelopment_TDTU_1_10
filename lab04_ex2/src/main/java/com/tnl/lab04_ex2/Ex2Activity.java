package com.tnl.lab04_ex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Ex2Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tvStatistic;
    private Button btnAdd, btnRemove;
    private UserAdapter adapter;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2);

        recyclerView = findViewById(R.id.recycler);
        tvStatistic = findViewById(R.id.statistic);
        btnAdd = findViewById(R.id.btnAdd);
        btnRemove = findViewById(R.id.btnRemove);

        users = new ArrayList<>();
        adapter = new UserAdapter(this, users);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));

        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> addFiveMore());
        btnRemove.setOnClickListener(v -> removeLastFive());

    }

    private void addFiveMore() {
        int count = users.size();
        for (int i = 1; i <= 5; i++) {
            int id = count++;
            users.add(new User("User " + (id + 1), "user" + (id + 1) + "@gmail.com"));
        }
        tvStatistic.setText("Total user: " + count);
        adapter.notifyDataSetChanged();
    }

    private void removeLastFive() {
        int prevSize = users.size();
        if (prevSize > 0) {
            for (int i = 0; i < 5; i++) {
                users.remove(users.size() - 1);
            }
        }
        if (users.size() == 0) {
            tvStatistic.setText("Total user: 0");
            Toast.makeText(this, "There is no user available", Toast.LENGTH_SHORT).show();
        } else {
            tvStatistic.setText("Total user: " + users.size());
        }
        adapter.notifyDataSetChanged();
    }
}
