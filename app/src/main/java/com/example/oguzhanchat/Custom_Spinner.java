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

public class Custom_Spinner extends ArrayAdapter {
    public Custom_Spinner(@NonNull Context context, ArrayList<CustomItem1> customlist) {
        super(context, 0, customlist);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_layout,parent,false);

        }
        CustomItem1 item= (CustomItem1) getItem(position);
        ImageView spinnerIV=convertView.findViewById(R.id.ivSpinnerLayout);
        TextView spinnerTv=convertView.findViewById(R.id.tvSpinnerLayout);
        if (item!=null) {
            spinnerIV.setImageResource(item.getItemSurat());
            spinnerTv.setText(item.getItemName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_dropdown_layout,parent,false);

        }
        CustomItem1 item= (CustomItem1) getItem(position);
        ImageView dropDownIV=convertView.findViewById(R.id.ivDropDownLayout);
        TextView dropDownTV=convertView.findViewById(R.id.tvDropDownLayout);
        if (item!=null) {
            dropDownIV.setImageResource(item.getItemSurat());
            dropDownTV.setText(item.getItemName());
        }
        return convertView;
    }
}
