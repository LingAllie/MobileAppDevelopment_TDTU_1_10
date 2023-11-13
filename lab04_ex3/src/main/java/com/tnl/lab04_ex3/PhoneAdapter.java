package com.tnl.lab04_ex3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PhoneAdapter extends ArrayAdapter<Phone> {

    private final Context context;
    private final int layout;
    private final List<Phone> data;

    public PhoneAdapter(Context context, int resource, List<Phone> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layout = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View prevView, @NonNull ViewGroup parent) {

        View view;
        Holder holder;

        if (prevView == null) {
            view = LayoutInflater.from(context).inflate(layout, parent, false);

            holder = new Holder();

            holder.icon = view.findViewById(R.id.imgIcon);
            holder.PName = view.findViewById(R.id.tvPName);
            holder.enable = view.findViewById(R.id.chbEnable);

            view.setTag(holder);
        }
        else {
            view = prevView;

            holder = (Holder) view.getTag();
        }

        Phone phone = data.get(position);

        holder.PName.setText(phone.getName());
        holder.icon.setImageResource(phone.getIcon());
        holder.enable.setChecked(phone.isChecked());

        holder.enable.setOnClickListener(v -> phone.setChecked(holder.enable.isChecked()));

        return view;
    }

    static class Holder {
        ImageView icon;
        TextView PName;
        CheckBox enable;
    }
}
