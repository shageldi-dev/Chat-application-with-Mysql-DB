package com.example.oguzhanchat;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;


public class FragmentHome extends Fragment {
    String url="http://10.102.10.20/mobile/regist.php";
    String url1="http://10.102.10.20/mobile/blok_down.php";
    private static String URL_REGIST="http://10.102.10.20/mobile/logout.php";
    private static String ur="http://110.102.10.20/mobile/login.php";
    Context thiscontext;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view =inflater.inflate(R.layout.home,container,false);
        thiscontext = container.getContext();

        final ListView lv=(ListView) view.findViewById(R.id.lv);
        final block_downloader d1=new block_downloader(thiscontext,url1,lv);

        d1.execute();

        final Downloader d=new Downloader(thiscontext,url,lv);

        d.execute();






        return view;
    }


    private void writetofile(String mydata){
        try {

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(thiscontext.openFileOutput("file.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(mydata);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("",e.toString());
        }
    }

    private void Regist(){


        final String name=readf().trim();
        final String password=readpass().trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            String success=jsonObject.getString("success");
                            String id=jsonObject.getString("id");
                            //Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
                            if(success.equals("1")){

                                //Toast.makeText(getApplicationContext(),"Logout success!",Toast.LENGTH_LONG).show();

                            }



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
                params.put("name",name);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(thiscontext);
        requestQueue.add(stringRequest);


    }


    private String readf(){
        String result="";

        try{
            InputStream inputStream = thiscontext.openFileInput("login.txt");


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
            InputStream inputStream = thiscontext.openFileInput("pass.txt");


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

    private void Registin(){


        final String name=readf().trim();
        final String password=readpass().trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, ur,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            String success=jsonObject.getString("success");
                            String id=jsonObject.getString("id");
                            //Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
                            if(success.equals("1")){
                                //Toast.makeText(getApplicationContext(),"Register success!",Toast.LENGTH_LONG).show();
                            }



                        }catch (JSONException e) {
                            e.printStackTrace();
                            //Toast.makeText(getApplicationContext(),"Register error!",Toast.LENGTH_LONG).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(thiscontext.getApplicationContext(),"Register error!",Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("name",name);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(thiscontext);
        requestQueue.add(stringRequest);


    }


}




