package com.example.oguzhanchat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hatlar_Parser extends AsyncTask<Void,Integer,Integer> {

    Context c;
    ListView lv;
    String data;
    List<String> d=new ArrayList<>();
    ArrayList<Hatlar> airports=new ArrayList<>();
    ArrayList<String> hat=new ArrayList<>();
    private static String URL_REGIST="http://10.102.10.20/mobile/hatlar.php";
    ProgressDialog pd;

    public Hatlar_Parser(Context c, ListView lv, String data) {
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


            Custom_Hatlar adapter=new Custom_Hatlar(airports,c);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Hatlar airport=airports.get(position);
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


            airports.clear();

            for(int i=0;i<ja.length();i++){

                jo=ja.getJSONObject(i);

                    if (jo.getString("status").equals("1"))
                        airports.add(new Hatlar(jo.getString("name"),jo.getString("surname"), jo.getString("id"), jo.getString("username"), jo.getString("degree")
                                , jo.getString("department"), jo.getString("faculty"), (R.drawable.image41), (R.drawable.status_online_sm),jo.getString("level"),readid()));
                    else
                        airports.add(new Hatlar(jo.getString("name"),jo.getString("surname"), jo.getString("id"), jo.getString("username"), jo.getString("degree")
                                , jo.getString("department"), jo.getString("faculty"), (R.drawable.image41), (R.drawable.status_away_sm),
                                jo.getString("level"),readid()));




            }



            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
    private void Regist(){




        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject=new JSONObject(response);

                                hat.add(jsonObject.getString("sender_id"));


                            //Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();




                        }catch (JSONException e) {
                            e.printStackTrace();
                            //Toast.makeText(getApplicationContext(),"Logout error!",Toast.LENGTH_LONG).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(),"Logout error!",Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();

                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(c);
        requestQueue.add(stringRequest);


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
