package com.example.oguzhanchat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Servis extends Service {
    Notification myNotifiaction;
    NotificationManager nManager;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> arrayList1=new ArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    Timer zamanlayji;
    Handler yardimci;

    @Override
    public void onCreate() {
        super.onCreate();
        zamanlayji=new Timer();
        yardimci=new Handler(Looper.getMainLooper());
        zamanlayji.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                bilgiVer();
            }
        },0,2000);
    }
    public void bilgiVer(){
        long zaman=java.lang.System.currentTimeMillis();
        SimpleDateFormat bilgi=new SimpleDateFormat("dd MMMM yyyy, EEEE / hh:mm");
        final String sonuc=bilgi.format(new Date(zaman));
        yardimci.post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                SharedPreferences prefs=getSharedPreferences("notif_date",MODE_PRIVATE);
                String sene=prefs.getString("sene","");


                Notif notif = new Notif(Servis.this);
                notif.execute("login", readf(),sene);



            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        zamanlayji.cancel();
    }

    public void loadLocale(){





    }

    private String readf(){
        String result="";

        try{
            InputStream inputStream = Servis.this.openFileInput("id.txt");


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

    public void arr(){
        String s=readf();
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
    }



    private String readf1(){
        String result="";

        try{
            InputStream inputStream = Servis.this.openFileInput("ugradyjy.txt");


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

    public void arr1(){
        String s=readf1();
        String grp = "";

        arrayList1.clear();

        for(int i=0;i<s.length();i++){
            if(String.valueOf(s.charAt(i)).equals("[") || String.valueOf(s.charAt(i)).equals("]"))
            {

            }
            else
            {

                if(String.valueOf(s.charAt(i)).equals(",")){
                    arrayList1.add(grp);
                    grp="";
                }else
                {
                    grp+=String.valueOf(s.charAt(i));
                }






            }

        }

        arrayList1.add(grp);
    }
}
