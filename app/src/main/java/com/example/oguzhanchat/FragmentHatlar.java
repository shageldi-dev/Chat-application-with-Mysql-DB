package com.example.oguzhanchat;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
import java.util.HashMap;
import java.util.Map;

public class FragmentHatlar extends Fragment {
    Context thiscontext;
    String url="http://10.102.10.20/mobile/distinct.php";
    String url2="http://10.102.10.20/mobile/texttosuser.php";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.hatlar,container,false);
        thiscontext = container.getContext();

        //Regist();
        final ListView lv=(ListView) view.findViewById(R.id.lv);
        Hatlar_BackgroundWorker backgroundWorker = new Hatlar_BackgroundWorker(thiscontext,url,lv);
        backgroundWorker.execute("login", readf());

        return view;
    }


    private void Regist(){


        final String id=readf().trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url2,
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
                params.put("id",id);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(thiscontext);
        requestQueue.add(stringRequest);


    }

    private String readf(){
        String result="";

        try{
            InputStream inputStream = thiscontext.openFileInput("id.txt");


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
