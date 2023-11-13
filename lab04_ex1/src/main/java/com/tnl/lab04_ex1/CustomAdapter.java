package com.tnl.lab04_ex1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final int layout;
    private final List<String> data;
    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        TextView myTitle = view.findViewById(R.id.tvTitle);
        Button btnRemove = view.findViewById(R.id.btnRemove);

        final String item = data.get(position);
        myTitle.setText(item);

        btnRemove.setOnClickListener(v -> {
            data.remove(position);
            notifyDataSetChanged();
            Log.e("TAG", "Button clicked: " + item);
        });
        return view;
    }
}
