package com.example.lets_talk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.security.acl.Group;
import java.util.ArrayList;

public class AdapterForGroupList extends BaseAdapter {
    private Context context;
    private ArrayList<GruopOfMassages> arrayList;
    private TextView content, name;
    public AdapterForGroupList(Context context, ArrayList<GruopOfMassages> arrayList) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.group_card, parent, false);
        content = convertView.findViewById(R.id.groupCard_message_body);
        name = convertView.findViewById(R.id.groupCard_name_text_view);
        name.setText(" " + arrayList.get(position).getName());
        content.setText(" " +arrayList.get(position).getDescription());
        return convertView;
    }
}
