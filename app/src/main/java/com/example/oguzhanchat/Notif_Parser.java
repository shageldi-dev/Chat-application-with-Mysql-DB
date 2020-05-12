package com.example.oguzhanchat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

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

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.NOTIFICATION_SERVICE;

public class Notif_Parser extends AsyncTask<Void,Integer,Integer> {
    Notification myNotif;
    Context c;

    String data;
    List<String> d=new ArrayList<>();
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> players=new ArrayList<>();
    ArrayList<Airport> airports=new ArrayList<>();
    ArrayList<String> passwords=new ArrayList<>();
    ProgressDialog pd;

    public Notif_Parser(Context c, String data) {
        this.c = c;
        this.data = data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parse();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
       // Airport airport=airports.get(0);


        if(integer == 1)
        {

           for(int i=0;i<airports.size();i++) {
               Airport airport=airports.get(i);
            NotificationManager mNotificationManager;

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(c, "notify_001");
            Intent ii = new Intent(c, Chat.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(c, 0, ii, 0);

            NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
            bigText.bigText("sent new message");
            bigText.setBigContentTitle(airport.getUsername());
            bigText.setSummaryText("Oguz chat");
            mBuilder.setAutoCancel(true);
            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setSmallIcon(R.drawable.logolib);
            mBuilder.setContentTitle("Your Title");
            mBuilder.setContentText("Your text");
            mBuilder.setPriority(Notification.PRIORITY_MAX);
            mBuilder.setStyle(bigText);

            mNotificationManager =
                    (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);

// === Removed some obsoletes
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                String channelId = "Your_channel_id";
                NotificationChannel channel = new NotificationChannel(
                        channelId,
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_HIGH);
                mNotificationManager.createNotificationChannel(channel);
                mBuilder.setChannelId(channelId);
            }

            mNotificationManager.notify(0, mBuilder.build());

        }




        }else
        {
            //Toast.makeText(c,"Ýalňyşlyk ýüze çykdy!!!",Toast.LENGTH_SHORT).show();
        }
        if(airports.toString().equals("[]")){

        }


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



            airports.clear();

            for(int i=ja.length()-1;i>=0;i--){

                jo=ja.getJSONObject(i);
                if(jo.getString("id").equals(readid())) {

                }
                else
                {
                    if (jo.getString("status").equals("1"))
                        airports.add(new Airport(jo.getString("id"),jo.getString("name"), jo.getString("surname"), jo.getString("username"), jo.getString("degree")
                                , jo.getString("department"), jo.getString("faculty"), (R.drawable.image41), (R.drawable.status_online_sm),jo.getString("level")));
                    else
                        airports.add(new Airport(jo.getString("id"),jo.getString("name"), jo.getString("surname"), jo.getString("username"), jo.getString("degree")
                                , jo.getString("department"), jo.getString("faculty"), (R.drawable.image41), (R.drawable.status_away_sm),
                                jo.getString("level")));

                    SharedPreferences.Editor editor=c.getSharedPreferences("notif_date",MODE_PRIVATE).edit();
                    editor.putString("sene",jo.getString("date"));
                    editor.apply();
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

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(c.openFileOutput("selected_user.txt", MODE_PRIVATE));
            outputStreamWriter.write(user);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("",e.toString());
        }
    }

    private void hmm(String user){
        try {

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(c.openFileOutput("hmm.txt", MODE_PRIVATE));
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
