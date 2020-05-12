package com.example.oguzhanchat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
private Button login,create_acount;
Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_click();
        loadLocale();
        btn_servis();
        if(readfromfile()!=null && readfromfile().toString().equals("1")){

            startActivity(new Intent(MainActivity.this,Main2Activity.class));
            finish();
        }
//        startActivity(new Intent(MainActivity.this,Main2Activity.class));
//        finish();

    }



    public void btn_click(){
        login=(Button)findViewById(R.id.loginbutton);


            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,Login.class));
                }
            });



    }

    private String readfromfile(){
        String result="";

        try{
            InputStream inputStream = openFileInput("file.txt");

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

    public boolean Serviscal(){
        ActivityManager servisYoneticisi=(ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo servis:servisYoneticisi.getRunningServices(Integer.MAX_VALUE)){
            if(context.getPackageName().equals(servis.service.getPackageName())){
                return true;
            }
        }
        return false;
    }


    public void btn_servis(){
        if(Serviscal()){
            stopService(new Intent(context,Servis.class));

        }else {
            startService(new Intent(context,Servis.class));
        }
    }

    private void setLocale(String lang){
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration config=new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getResources().getDisplayMetrics());


        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();


    }

    public void loadLocale(){
        SharedPreferences prefs=getSharedPreferences("Settings",MODE_PRIVATE);
        String language=prefs.getString("My_Lang","");

        setLocale(language);
    }

    @SuppressWarnings("deprecation")
    private void updateConfiguration(Configuration config) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getBaseContext().createConfigurationContext(config);
        } else {
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
    }
}
