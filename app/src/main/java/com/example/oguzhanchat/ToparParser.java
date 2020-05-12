package com.example.oguzhanchat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ToparParser extends AsyncTask<Void,Integer,Integer> {

    Context c;
    ListView lv;
    String data;
    List<String> d=new ArrayList<>();

    ArrayList<String> players=new ArrayList<>();
    ArrayList<Topar> airports=new ArrayList<>();
    ArrayList<String> passwords=new ArrayList<>();
    ArrayList<String> arrayList=new ArrayList<>();
    ProgressDialog pd;

    public ToparParser(Context c, ListView lv, String data) {
        this.c = c;
        this.lv = lv;
        this.data = data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Maglumatlar goşulýar!!!");
        pd.setMessage("Maglumatlar goşulýar...");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if(integer == 1)
        {


            Custom_Topar adapter=new Custom_Topar(airports,c);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Topar topar=airports.get(position);
                    Intent intent=new Intent(c,Group_Chat.class);
                    intent.putExtra("name",topar.getG_name());
                    intent.putExtra("group_id",topar.getG_name_id());
                    intent.putExtra("id",topar.getId());
                    writeselecteduser(topar.getG_name_id());
                    c.startActivity(intent);




                }
            });





        }else
        {
            //Toast.makeText(c,"Ýalňyşlyk ýüze çykdy!!!",Toast.LENGTH_SHORT).show();
        }

        pd.dismiss();
    }
    public void startact(){

    }

    private int parse(){
        try{
            JSONArray ja=new JSONArray(data);
            JSONObject jo=null;

            airports.clear();

            for(int i=ja.length()-1;i>=0;i--){

                jo=ja.getJSONObject(i);
                if(jo.getString("username").equals(readf())) {
                    airports.add(new Topar(jo.getString("id"),jo.getString("g_name"),jo.getString("g_name_id"),jo.getString("creator"),
                            jo.getString("username")));
               }



            }

            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String readf(){
        String result="";

        try{
            InputStream inputStream = c.openFileInput("login.txt");


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
    private String readpass(){
        String result="";

        try{
            InputStream inputStream = c.openFileInput("pass.txt");


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
    private void writeselecteduser(String user){
        try {

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(c.openFileOutput("selected_group.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(user);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("",e.toString());
        }
    }
    private String readid(){
        String result="";

        try{
            InputStream inputStream = c.openFileInput("id.txt");


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
