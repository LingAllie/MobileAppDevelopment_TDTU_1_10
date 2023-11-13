package com.tnl.lab05_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class Ex1Activity extends AppCompatActivity implements MyOnItemClickListener {

    private RecyclerView recyclerView;
    private List<AppInfo> appList;
    private MyAppAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        appList = AppLoader.loadAppList(this);

        adapter = new MyAppAdapter(appList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AppInfo info) {

    }
}