package com.example.oguzhanchat;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Spinner;

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

public class Degree_Block_Parser extends AsyncTask<Void,Integer,Integer> {

    Context c;
    ListView lv;
    String data;
    List<String> d=new ArrayList<>();
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> players=new ArrayList<>();
    ArrayList<Degree_Block_B> airports=new ArrayList<>();
    ArrayList<String> passwords=new ArrayList<>();
    ProgressDialog pd;

    public Degree_Block_Parser(Context c, ListView lv, String data) {
        this.c = c;
        this.lv = lv;
        this.data = data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

//        pd=new ProgressDialog(c);
//        pd.setTitle("Maglumatlar goşulýar!!!");
//        pd.setMessage("Maglumatlar goşulýar...");
//        pd.show();
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


            Cusrom_Degree_Block adapter=new Cusrom_Degree_Block(airports, c);
            lv.setAdapter(adapter);






        }else
        {
            //Toast.makeText(c,"Ýalňyşlyk ýüze çykdy!!!",Toast.LENGTH_SHORT).show();
        }
        if(airports.toString().equals("[]")){

        }

//        pd.dismiss();
    }
    public void startact(){

    }

    private int parse(){
        try{
            JSONArray ja=new JSONArray(data);
            JSONObject jo=null;


            airports.clear();

            for(int i=0;i<=ja.length()-1;i++){

                jo=ja.getJSONObject(i);


                        airports.add(new Degree_Block_B(jo.getString("id"),jo.getString("type"),jo.getString("level")));






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

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(c.openFileOutput("selected_user.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(user);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("",e.toString());
        }
    }

    private void hmm(String user){
        try {

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(c.openFileOutput("hmm.txt", Context.MODE_PRIVATE));
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

    private String readblock(){
        String result="";

        try{
            InputStream inputStream = c.openFileInput("blocked_users1.txt");


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
