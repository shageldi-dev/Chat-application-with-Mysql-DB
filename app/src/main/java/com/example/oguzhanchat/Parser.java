package com.example.oguzhanchat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

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

public class Parser extends AsyncTask<Void,Integer,Integer> {

    Context c;
    ListView lv;
    String data;
    List<String> d=new ArrayList<>();
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> players=new ArrayList<>();
    ArrayList<Airport> airports=new ArrayList<>();
    ArrayList<String> passwords=new ArrayList<>();
    ProgressDialog pd;

    public Parser(Context c, ListView lv, String data) {
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


            CustomAdapter adapter=new CustomAdapter(airports,c);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Airport airport=airports.get(position);
                   // Toast.makeText(c,airport.getAt()+" "+airport.getFamilya(),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(c,Chat.class);
                    intent.putExtra("name",airport.getAt()+" "+airport.getFamilya());
                    intent.putExtra("level",airport.getLevel());
                    writeselecteduser(airport.getId());
                    c.startActivity(intent);



                }
            });




        }else
        {
            //Toast.makeText(c,"Ýalňyşlyk ýüze çykdy!!!",Toast.LENGTH_SHORT).show();
        }
        if(airports.toString().equals("[]")){

        }

        pd.dismiss();
    }
    public void startact(){

    }

    private int parse(){
        try{
            JSONArray ja=new JSONArray(data);
            JSONObject jo=null;

            String s=readblock();
            String grp = "";

            arrayList.clear();


            for(int i=0;i<s.length();i++){
                if(String.valueOf(s.charAt(i)).equals("[") || String.valueOf(s.charAt(i)).equals("]"))
                {

                }
                else
                {

                    if(String.valueOf(s.charAt(i)).equals(",")){
                        arrayList.add(grp);
                        grp="";
                    }else
                    {
                        grp+=String.valueOf(s.charAt(i));
                    }






                }

            }
            arrayList.add(grp);

            airports.clear();

            for(int i=ja.length()-1;i>=0;i--){

                jo=ja.getJSONObject(i);
                if(jo.getString("tassyklananmy").equals("1")) {
                    if (jo.getString("id").equals(readid())) {

                    } else {
                        if (jo.getString("status").equals("1"))
                            airports.add(new Airport(jo.getString("id"), jo.getString("name"), jo.getString("surname"), jo.getString("username"), jo.getString("degree")
                                    , jo.getString("department"), jo.getString("faculty"), (R.drawable.image41), (R.drawable.status_online_sm), jo.getString("level")));
                        else
                            airports.add(new Airport(jo.getString("id"), jo.getString("name"), jo.getString("surname"), jo.getString("username"), jo.getString("degree")
                                    , jo.getString("department"), jo.getString("faculty"), (R.drawable.image41), (R.drawable.status_away_sm),
                                    jo.getString("level")));


                    }
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
