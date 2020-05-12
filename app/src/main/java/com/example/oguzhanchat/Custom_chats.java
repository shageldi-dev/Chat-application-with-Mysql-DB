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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Custom_chats extends BaseAdapter {
    LayoutInflater inflater;
    List<chats_down> airportList=new ArrayList<>();
    Context context;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> arrayList1=new ArrayList<>();
    ArrayList<String> arrayList2=new ArrayList<>();

    public String id1;

    public Custom_chats(List<chats_down> _airportList, Context _context) {
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
        chats_down airport=airportList.get(position);
        if(airport.getOkaldymy().equals("0")){
            arrayList.add("salam");
            arrayList1.add("Shageldi");
            arrayList2.add("9");
            writetofile(arrayList.toString());
            writetofile1(arrayList1.toString());
            writetofile2(arrayList2.toString());
            arrayList.clear();
            arrayList1.clear();
            arrayList2.clear();
        }
        if((airport.getUgradyjy().equals(readid()) && airport.getKabul_ediji().equals(readuser())) || (airport.getUgradyjy().equals(readuser()) && airport.getKabul_ediji().equals(readid()))) {
            if (airport.getUgradyjy().equals(readid())) {
                View satir = inflater.inflate(R.layout.message, null);
                ImageView msgimg = (ImageView) satir.findViewById(R.id.msgimg);
                ImageView okaldymy = (ImageView) satir.findViewById(R.id.okaldymy);
                TextView textmessage = (TextView) satir.findViewById(R.id.textmessage);
                TextView date = (TextView) satir.findViewById(R.id.date);
                msgimg.setImageResource(airport.getImg_sender());
                okaldymy.setImageResource(airport.getImg_okaldymy());
                textmessage.setText(airport.getMsg());
                date.setText(airport.getWagty());
                return satir;
            }
            if (airport.getUgradyjy().equals(readuser())) {
                View satir = inflater.inflate(R.layout.rec_message_lyot, null);
                ImageView msgimg = (ImageView) satir.findViewById(R.id.sender_img);

                TextView textmessage = (TextView) satir.findViewById(R.id.rec);
                TextView date = (TextView) satir.findViewById(R.id.date);
                msgimg.setImageResource(airport.getImg_sender());

                textmessage.setText(airport.getMsg());
                date.setText(airport.getWagty());
                return satir;
            }
            else {
                View satir=inflater.inflate(R.layout.bosh,null);
                return satir;
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
            InputStream inputStream = context.openFileInput("selected_user.txt");


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
    private void writetofile(String mydata){
        try {

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(context.openFileOutput("msg.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(mydata);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("",e.toString());
        }
    }

    private void writetofile1(String mydata){
        try {

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(context.openFileOutput("ugradyjy.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(mydata);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("",e.toString());
        }
    }

    private void writetofile2(String mydata){
        try {

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(context.openFileOutput("lvl.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(mydata);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("",e.toString());
        }
    }
}
