package com.example.oguzhanchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter1 extends ArrayAdapter {
    public CustomAdapter1(@NonNull Context context, ArrayList<CustomItem> customlist) {
        super(context, 0, customlist);
    }

    @NonNull
    @Override
    public View getView(int position,@Nullable View convertView,@NonNull ViewGroup parent){
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_layout,parent,false);

        }
        CustomItem item= (CustomItem) getItem(position);
        ImageView spinnerIV=convertView.findViewById(R.id.ivSpinnerLayout);
        TextView spinnerTv=convertView.findViewById(R.id.tvSpinnerLayout);
        if (item!=null) {
            spinnerIV.setImageResource(item.getSpinnerItemImage());
            spinnerTv.setText(item.getSpinnerItemName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_dropdown_layout,parent,false);

        }
        CustomItem item= (CustomItem) getItem(position);
        ImageView dropDownIV=convertView.findViewById(R.id.ivDropDownLayout);
        TextView dropDownTV=convertView.findViewById(R.id.tvDropDownLayout);
        if (item!=null) {
            dropDownIV.setImageResource(item.getSpinnerItemImage());
            dropDownTV.setText(item.getSpinnerItemName());
        }
        return convertView;
    }
}
