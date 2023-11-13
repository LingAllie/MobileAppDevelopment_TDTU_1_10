package com.tnl.lab05_ex1;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAppAdapter extends RecyclerView.Adapter<MyAppAdapter.AppHolder> {

    private final List<AppInfo> appList;
    private MyOnItemClickListener listener;

    public MyAppAdapter(List<AppInfo> appList) {
        this.appList = appList;
    }


    @NonNull
    @Override
    public AppHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(R.layout.app_item, parent, false);

        return new AppHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppHolder holder, int position) {
        AppInfo info = appList.get(position);
        holder.bindData(info);
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    public void setListener(MyOnItemClickListener listener) {
        this.listener = listener;
    }

    public static class AppHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title, subtitle, date;


        public AppHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            date = itemView.findViewById(R.id.installedDate);
        }

        public void bindData(AppInfo info) {
            icon.setImageDrawable(info.getIcon());
            title.setText(info.getName());
            subtitle.setText(info.displaySize());
            date.setText(info.getInstalledDate());

            itemView.setOnClickListener((v) -> {
                Log.e("TAG", "DA click vao item: " + info.getName());
            });
        }
    }
}
