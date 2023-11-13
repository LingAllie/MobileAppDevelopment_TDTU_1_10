package com.tnl.lab06_ex2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    private List<Event> data;
    private Context context;

    public EventAdapter(Context context, List<Event> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(this.context).inflate(R.layout.home_row, parent, false);
        return new EventHolder(view);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        Event e = data.get(position);
        holder.name.setText(e.getName());
        holder.place.setText(e.getPlace());
        holder.dateTime.setText(e.getDate() + " " + e.getTime());
        holder.switchUI.setChecked(e.isChecked());
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    class EventHolder extends RecyclerView.ViewHolder {

        Switch switchUI;
        TextView name, place, dateTime;

        public EventHolder(@NonNull View itemView) {
            super(itemView);
            switchUI = itemView.findViewById(R.id.switchUI);
            name = itemView.findViewById(R.id.tvName);
            place = itemView.findViewById(R.id.tvPlace);
            dateTime = itemView.findViewById(R.id.tvDateTime);
        }
    }

}
