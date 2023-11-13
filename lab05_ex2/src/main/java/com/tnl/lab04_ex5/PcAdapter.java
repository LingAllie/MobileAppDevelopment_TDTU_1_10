package com.tnl.lab04_ex5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PcAdapter extends RecyclerView.Adapter<PcAdapter.PcHolder> {

    private final List<PC> pcList;
    private  PcClickListener listener;

    public void setPcListener(PcClickListener listener) {
        this.listener = listener;
    }

    public PcAdapter(List<PC> pcList) {
        this.pcList = pcList;
    }

    @NonNull
    @Override
    public PcHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater converter = LayoutInflater.from(parent.getContext());
        View rootView = converter.inflate(R.layout.item_layout, parent, false);
        return new PcHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull PcHolder holder, int position) {
        PC pc = pcList.get(position);
        holder.bind(pc, listener);
    }

    @Override
    public int getItemCount() {
        return pcList.size();
    }

    public static class PcHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView PcName;

        public void bind(PC pc, PcClickListener listener) {
            PcName.setText((pc.getLabel()));
            if (pc.isOn()) {
                icon.setImageResource(R.drawable.on);
            }
            else {
                icon.setImageResource(R.drawable.off);
            }
            itemView.setOnClickListener(v -> {
                if(listener != null) {
                    listener.onPcClicked(pc, getAdapterPosition());
                }
            });
        }

        public PcHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            PcName = itemView.findViewById(R.id.PcName);
        }
    }
}
