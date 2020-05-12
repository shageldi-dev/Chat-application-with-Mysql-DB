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

public class Custom_group_chat extends BaseAdapter {
    LayoutInflater inflater;
    List<group_chat_down> airportList=new ArrayList<>();
    Context context;

    public String id1;

    public Custom_group_chat(List<group_chat_down> _airportList, Context _context) {
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
        inflater=LayoutInflater.from(context);
        View view;
        group_chat_down grp=airportList.get(position);
        if(grp.getG_name().equals(readuser())) {
            if (readid().equals(grp.getUser_id())) {
                view = inflater.inflate(R.layout.message_group, null);
                TextView textmessage = (TextView) view.findViewById(R.id.textmessage);
                TextView date = (TextView) view.findViewById(R.id.date);
                textmessage.setText(grp.getMsg());
                date.setText(grp.getDate());
                return view;
            } else {
                view = inflater.inflate(R.layout.rec_message_lyot_group, null);
                TextView textmessage = (TextView) view.findViewById(R.id.rec);
                TextView sender_name = (TextView) view.findViewById(R.id.sender_name);
                TextView date = (TextView) view.findViewById(R.id.date);
                textmessage.setText(grp.getMsg());
                date.setText(grp.getDate());
                sender_name.setText(grp.getName());
                return view;

            }
        }
        else {
            View satir=inflater.inflate(R.layout.bosh,null);
            return satir;
        }

    }

    private String readid(){
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
    private String readuser(){
        String result="";

        try{
            InputStream inputStream = context.openFileInput("selected_group.txt");


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
