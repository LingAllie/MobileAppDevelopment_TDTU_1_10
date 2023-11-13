package com.tnl.lab04_ex5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import java.util.List;

public class Ex5Activity extends AppCompatActivity implements PcClickListener {

    private List<PC> pcList;
    private PcAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex5);

        pcList = PC.generate(30);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        adapter = new PcAdapter(pcList);
        adapter.setPcListener(this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onPcClicked(PC pc, int position) {
        pc.changeMode();
        adapter.notifyItemChanged(position);
    }
}