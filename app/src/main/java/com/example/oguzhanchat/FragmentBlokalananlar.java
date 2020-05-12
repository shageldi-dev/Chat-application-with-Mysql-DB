package com.example.oguzhanchat;


import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

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
import java.util.Timer;
import java.util.TimerTask;

public class FragmentBlokalananlar extends Fragment {
    Context thiscontext;
    ip ip=new ip();
    String url="http://"+ip.ip+"/blok_down.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.bloklananlar,container,false);
        thiscontext = container.getContext();

        final ListView lv=(ListView) view.findViewById(R.id.lv);
        Block_BackgroundWorker backgroundWorker = new Block_BackgroundWorker(thiscontext,url,lv);
        backgroundWorker.execute("login", readf());
//        Timer timer = new Timer();
//        TimerTask t = new TimerTask() {
//            @Override
//            public void run() {
//                final b_down d=new b_down(thiscontext,url,lv);
//
//
//                d.execute();
//
//            }
//        };
//        timer.scheduleAtFixedRate(t,2000,2000);
        return view;
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
