package com.example.oguzhanchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Profil extends AppCompatActivity {

    ImageView update_acount;
    Context context=this;
    String url="http://10.102.10.20/mobile/profile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        ListView lv=(ListView) findViewById(R.id.lv_profile);



        Profile_BackgroundWorker backgroundWorker = new Profile_BackgroundWorker(context,url,lv);
        backgroundWorker.execute("login", id);



    }

    private String readf(){
        String result="";

        try{
            InputStream inputStream = this.openFileInput("id.txt");


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
