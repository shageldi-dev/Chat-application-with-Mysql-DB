package com.example.oguzhanchat;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Custom_Profile extends BaseAdapter {
    LayoutInflater inflater;
    List<Profile> blokList=new ArrayList<>();
    Context context;
    public TextView At_fam;
    public TextView At_fam1;
    public TextView degree;
    public TextView faculty;
    public TextView department;
    public TextView username;

    public Custom_Profile(List<Profile> blokList, Context context) {
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
        View view=inflater.inflate(R.layout.activity_profil,null);
        Profile blok=blokList.get(position);
        At_fam=(TextView) view.findViewById(R.id.At_fam1);
        At_fam1=(TextView) view.findViewById(R.id.at_fam);
        degree=(TextView) view.findViewById(R.id.degree_profil);


        username=(TextView) view.findViewById(R.id.username_profil);


        At_fam.setText(blok.getAt()+" "+blok.getFamilya());
        At_fam1.setText(blok.getAt()+" "+blok.getFamilya());
        degree.setText(blok.getDegree());


        username.setText(blok.getUsername());








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
