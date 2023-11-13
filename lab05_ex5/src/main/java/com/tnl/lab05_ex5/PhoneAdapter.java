package com.tnl.lab05_ex5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder> {

    private Context context;
    private int layout;
    private List<Phone> data;

    public PhoneAdapter(Context context, int resource, List<Phone> objects) {
        this.context = context;
        this.layout = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Phone phone = data.get(position);

        holder.PName.setText(phone.getName());
        holder.icon.setImageResource(phone.getIcon());
        holder.enable.setChecked(phone.isChecked());

        holder.enable.setOnCheckedChangeListener((buttonView, isChecked) -> phone.setChecked(isChecked));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView PName;
        CheckBox enable;

        ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.imgIcon);
            PName = itemView.findViewById(R.id.tvPName);
            enable = itemView.findViewById(R.id.chbEnable);
        }
    }
}
