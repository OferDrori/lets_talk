package com.example.lets_talk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;
public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Message> arrayList;
    private TextView content, name;
    public MyAdapter(Context context, ArrayList<Message> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        content = convertView.findViewById(R.id.massage_text_view);
        name = convertView.findViewById(R.id.name_text_view);
        content.setText(" " + arrayList.get(position).getContent());
        name.setText(arrayList.get(position).getSenderName());
        return convertView;
    }
}