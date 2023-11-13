package com.tnl.lab07_ex2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyStudentAdapter extends ArrayAdapter<Student> {

    private Context ctx;
    private List<Student> data;

    public MyStudentAdapter(Context context,
                            int resource,
                            List<Student> objects) {
        super(context, resource, objects);

        this.ctx = context;
        this.data = objects;
    }

    public View getView(int position, View previous, ViewGroup parent) {

        boolean isNull = (previous == null);
//        Log.e("TAG","getView: " + (position + 1) + ", " + isNull + "\t\t");



        Student student = data.get(position);

        LayoutInflater inflater = LayoutInflater.from(ctx);
        View layout = inflater.inflate(R.layout.my_advanced_row, parent, false);

        TextView title = layout.findViewById(R.id.title);
        TextView subtitle = layout.findViewById(R.id.subtitle);
        ImageView avatar = layout.findViewById(R.id.avatar);

        title.setText(student.getName());
        subtitle.setText(student.getEmail());

        int ImageId = R.drawable.ic_a;
        if (student.getLevel() == 2) {
            ImageId = R.drawable.ic_b;
        }
        else if (student.getLevel() == 3) {
            ImageId = R.drawable.ic_baseline_directions_bike_24;
        }

        avatar.setImageResource(ImageId);


        return layout;
    }
}
