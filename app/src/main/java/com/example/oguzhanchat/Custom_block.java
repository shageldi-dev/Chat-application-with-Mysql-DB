package com.example.oguzhanchat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Custom_block extends BaseAdapter {
    LayoutInflater inflater;
    List<Blok> blokList=new ArrayList<>();
    Context context;

    public Custom_block(List<Blok> blokList, Context context) {
        this.blokList = blokList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return blokList.size();
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
        View view=inflater.inflate(R.layout.bosh,null);
        Blok blok=blokList.get(position);





        return view;
    }

    private void writeselecteduser(String user){
        try {

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(context.openFileOutput("blocked_users1.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(user);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("",e.toString());
        }
    }
}
