package com.example.oguzhanchat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Custom_Hatlar extends BaseAdapter {
    List<Hatlar> hatlarList=new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    public Custom_Hatlar(List<Hatlar> hatlarList, Context context) {
        this.hatlarList = hatlarList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return hatlarList.size();
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
        Hatlar airport=hatlarList.get(position);
        inflater=LayoutInflater.from(context);
        View satir;

            satir = inflater.inflate(R.layout.hatlar_users, null);
            ImageView imageView = (ImageView) satir.findViewById(R.id.imageUsers);
            ImageView status = (ImageView) satir.findViewById(R.id.On_off);
            TextView name_surname = (TextView) satir.findViewById(R.id.name_surname);
            TextView username_degree = (TextView) satir.findViewById(R.id.username_degree);
            TextView FacultyTxt = (TextView) satir.findViewById(R.id.FacultyTxt);


            imageView.setImageResource(airport.getImgSrc());
            status.setImageResource(airport.getStatus());
            name_surname.setText(airport.getAt() + " " + airport.getFamilya());
            username_degree.setText(airport.getUsername());
            FacultyTxt.setText(airport.getDegree());

            return satir;


    }

    private String readf(){
        String result="";

        try{
            InputStream inputStream = context.openFileInput("id.txt");


            if(inputStream!=null){
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String temstring="";
                StringBuilder stringBuilder=new StringBuilder();
                while((temstring=bufferedReader.readLine())!=null){
                    stringBuilder.append(temstring);
                }
                inputStream.close();
                result=stringBuilder.toString();
            }


        }catch (FileNotFoundException e){
            Log.v("",e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }
}
