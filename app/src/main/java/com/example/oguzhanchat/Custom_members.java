package com.example.oguzhanchat;

import android.content.Context;
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

public class Custom_members extends BaseAdapter {
    LayoutInflater inflater;
    List<Members> blokList=new ArrayList<>();
    Context context;


    public Custom_members(List<Members> blokList, Context context) {
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

        Members blok=blokList.get(position);
        View satir=inflater.inflate(R.layout.members_design,null);
        ImageView imageView= (ImageView) satir.findViewById(R.id.imageUsers);
        ImageView status= (ImageView) satir.findViewById(R.id.On_off);
        TextView name_surname=(TextView) satir.findViewById(R.id.name_surname);
        TextView username_degree=(TextView) satir.findViewById(R.id.username_degree);
        TextView FacultyTxt=(TextView) satir.findViewById(R.id.FacultyTxt);
        TextView departmenttxt=(TextView) satir.findViewById(R.id.departmenttxt);
        if(blok.getStatus().equals("1")) {
            status.setImageResource(R.drawable.status_online_sm);
        } else {
            status.setImageResource(R.drawable.status_away_sm);
        }

        name_surname.setText(blok.getAt()+" "+blok.getFamilya());
        username_degree.setText(blok.getUsername()+"-"+blok.getDegree());
        FacultyTxt.setText(blok.getFaculty());
        departmenttxt.setText(blok.getDepartment());
        return satir;
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
