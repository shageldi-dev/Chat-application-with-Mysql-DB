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

public class CustomAdapter extends BaseAdapter {
    LayoutInflater inflater;
    List<Airport> airportList=new ArrayList<>();
    Context context;

    public CustomAdapter(List<Airport> _airportList, Context _context) {
        this.airportList = _airportList;
        this.context = _context;
    }

    @Override
    public int getCount() {
        return airportList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Airport airport=airportList.get(position);
        inflater=LayoutInflater.from(context);
        View satir=inflater.inflate(R.layout.users,null);
        ImageView imageView= (ImageView) satir.findViewById(R.id.imageUsers);
        ImageView status= (ImageView) satir.findViewById(R.id.On_off);
        TextView name_surname=(TextView) satir.findViewById(R.id.name_surname);
        TextView username_degree=(TextView) satir.findViewById(R.id.username_degree);
        TextView FacultyTxt=(TextView) satir.findViewById(R.id.FacultyTxt);


        imageView.setImageResource(airport.getImgSrc());
        status.setImageResource(airport.getStatus());
        name_surname.setText(airport.getAt()+" "+airport.getFamilya());
        username_degree.setText(airport.getUsername());
        FacultyTxt.setText(airport.getDegree());

        return satir;
    }
}
