package com.tnl.lab03_ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Ex3Activity extends AppCompatActivity {

    private Spinner spinnerWebsites;
    private List<String> urls;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex3);

        urls = new ArrayList<>();
        urls.add("https://tdtu.edu.vn");
        urls.add("https://vnexpress.vn");
        urls.add("https://it.tdtu.edu.vn");
        urls.add("https://thanhnien.vn");

        adapter = new ArrayAdapter<>(this, R.layout.my_spinner_item, urls);

        spinnerWebsites = findViewById(R.id.spinner);
        spinnerWebsites.setAdapter(adapter);

    }

    public void openWebsite(View view) {
        int index = spinnerWebsites.getSelectedItemPosition();
        String url = urls.get(index);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));

        startActivity(intent);
    }
}