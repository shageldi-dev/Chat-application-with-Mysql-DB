package com.example.oguzhanchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Custom_Topar extends BaseAdapter {
    LayoutInflater inflater;
    List<Topar> toparList=new ArrayList<>();
    Context context;

    public Custom_Topar(List<Topar> toparList, Context context) {
        this.toparList = toparList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return toparList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.groups_design,null);
        TextView textView=(TextView) view.findViewById(R.id.group_name);
        Topar topar=toparList.get(position);
        textView.setText(topar.getG_name());

        return view;
    }
}
