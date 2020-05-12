package com.example.oguzhanchat;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Cusrom_Degree_Block extends BaseAdapter {
    LayoutInflater inflater;
    List<Degree_Block_B> blokList=new ArrayList<>();
    Context context;

    public Cusrom_Degree_Block(List<Degree_Block_B> blokList, Context context) {
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
       final Degree_Block_B degree_block_b=blokList.get(position);
       View v=inflater.inflate(R.layout.degree_block_b,null);
        TextView id=(TextView) v.findViewById(R.id.tb);
        TextView degree=(TextView) v.findViewById(R.id.degree);
        final Button btn=(Button) v.findViewById(R.id.btn_degree_blokla);
        id.setText(degree_block_b.getLevel());
        degree.setText(degree_block_b.getType());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String myid="";
               String will_be_block=degree_block_b.getLevel();
               if(btn.getText().toString().equals("Blokla") || btn.getText().toString().equals("Block") || btn.getText().toString().equals("Заблокировать")) {
                   BackgroundWorkerDegree backgroundWorker = new BackgroundWorkerDegree(context);
                   backgroundWorker.execute("login", readf(), degree_block_b.getLevel());
                   btn.setBackgroundColor(Color.GREEN);
                   btn.setText(R.string.degree_blok1);
               } else if(btn.getText().toString().equals("Blokdan Aç") || btn.getText().toString().equals("Unblock") || btn.getText().toString().equals("Разблокировать")) {
                   BackgroundWorkerUndegree backgroundWorkerUndegree = new BackgroundWorkerUndegree(context);
                   backgroundWorkerUndegree.execute("login", readf(), degree_block_b.getLevel());
                   btn.setBackgroundColor(Color.RED);
                   btn.setText(R.string.degree_blok);
                }
            }
        });
        return v;
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
