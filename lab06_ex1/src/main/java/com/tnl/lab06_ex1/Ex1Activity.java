package com.tnl.lab06_ex1;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Ex1Activity extends AppCompatActivity {

    private ListView mListView;
    private PhoneAdapter mAdapter;
    private List<Phone> mData;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);

        initViews();
        initData();

        registerForContextMenu(mListView);
    }

    private void initViews() {
        mCheckBox = findViewById(R.id.checkbox);
        mListView = findViewById(R.id.listView);
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add(new Phone(R.drawable.mobile, "Apple"));
        mData.add(new Phone(R.drawable.mobile2, "Samsung"));
        mData.add(new Phone(R.drawable.mobile3, "Nokia"));
        mData.add(new Phone(R.drawable.mobile, "Oppo"));
        mData.add(new Phone(R.drawable.mobile2, "HTC"));
        mData.add(new Phone(R.drawable.mobile3, "Google"));
        mData.add(new Phone(R.drawable.mobile, "Microsoft"));
        mData.add(new Phone(R.drawable.mobile2, "BKav"));
        mData.add(new Phone(R.drawable.mobile3, "VinSmart"));

        mAdapter = new PhoneAdapter(this, R.layout.row_layout, mData);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        int position = info.position;

        Phone p = mData.get(position);

        menu.setHeaderTitle(p.getName());

        if (p.isChecked()) {
            getMenuInflater().inflate(R.menu.context_menu_uncheck, menu);
        } else {
            getMenuInflater().inflate(R.menu.context_menu_check, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        Phone p = mData.get(position);

        int itemId = item.getItemId();
        if (itemId == R.id.check) {
            p.setChecked(true);
        } else if (itemId == R.id.uncheck) {
            p.setChecked(false);
        } else if (itemId == R.id.delete) {
            mData.remove(position);
        }

        mAdapter.notifyDataSetChanged();
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.delete_all) {
            deleteAllItems();
        } else if (itemId == R.id.about) {
            aboutThisApp();
        }
        return super.onOptionsItemSelected(item);
    }

    private void aboutThisApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About");
        builder.setMessage("This app is an example app");
        builder.setPositiveButton("OK", null);

        builder.setCancelable(false);

        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void deleteAllItems() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete all items");
        builder.setMessage("Are you sure you want to delete all items ?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            mData.clear();
            mAdapter.notifyDataSetChanged();
            Toast.makeText(Ex1Activity.this, "All items have been deleted", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("No", null);
        builder.setCancelable(false);

        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
