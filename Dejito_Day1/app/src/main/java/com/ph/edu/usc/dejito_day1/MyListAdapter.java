package com.ph.edu.usc.dejito_day1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] courses;
    private final String[] topics;
    private final Integer[] imgid;

    public MyListAdapter(Activity context, String[] courses, String[] topics, Integer[] imgid) {
        super(context, R.layout.mylistlayout, courses);
        this.context = context;
        this.courses = courses;
        this.topics = topics;
        this.imgid = imgid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylistlayout, null, true);

        ImageView img = rowView.findViewById(R.id.icon);
        TextView head = rowView.findViewById(R.id.header);
        TextView sub = rowView.findViewById(R.id.subtext);

        img.setImageResource(imgid[position]);
        head.setText(courses[position]);
        sub.setText(topics[position]);

        return rowView;
    }
}
