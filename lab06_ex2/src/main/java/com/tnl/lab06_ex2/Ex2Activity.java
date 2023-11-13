package com.tnl.lab06_ex2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Ex2Activity extends AppCompatActivity {

    private static final int REQUEST_NEW = 1;
    private RecyclerView mRecyclerView;
    private EventAdapter mAdapter;
    private List<Event> mData;
    private boolean switchState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2);

        initViews();
        initData();

        registerForContextMenu(mRecyclerView);
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add(new Event("Bao cao do an", "C102", "23/10/2023", "14:00"));
        mData.add(new Event("Bao cao luan van", "C102", "23/10/2023", "14:00"));
        mData.add(new Event("Bao cao bai tap lon", "C102", "23/10/2023", "14:00"));

        mAdapter = new EventAdapter(this, mData);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initViews() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            startActivityForResult(new Intent(this, EditorActivity.class), REQUEST_NEW);
        } else if (item.getItemId() == R.id.menu_remove_all) {
            removeAll();
        } else if (item.getItemId() == R.id.menu_about) {
            aboutThisApp();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.branch_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        Event e = mData.get(position);

        if (item.getItemId() == R.id.delete) {
            mData.remove(position);
        }
        return super.onContextItemSelected(item);
    }

    private void aboutThisApp() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("About");
        builder.setMessage("This app is an example app");
        builder.setPositiveButton("OK", null);

        builder.setCancelable(false);

        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void removeAll() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Remove all events");
        builder.setMessage("Are you sure you want to remove all events ?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            mAdapter.clear();
            mAdapter.notifyDataSetChanged();
            Toast.makeText(Ex2Activity.this, "All events have been removed", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("No", null);
        builder.setCancelable(false);

        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }

        Event event = data.getParcelableExtra("data");
        if (event == null) {
            Toast.makeText(this, "Invalid data received", Toast.LENGTH_SHORT).show();
            return;
        }

        mData.add(0, event);
        mAdapter.notifyItemInserted(0);

    }
}