package com.example.oguzhanchat;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class login_add extends AsyncTask<Void,Integer,Integer> {

    Context c;
    ListView lv;
    String data;

    ArrayList<String> players=new ArrayList<>();
    ArrayList<chats_down> airports=new ArrayList<>();
    ProgressDialog pd;

    public login_add(Context c, ListView lv, String data) {
        this.c = c;
        this.lv = lv;
        this.data = data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

//        pd=new ProgressDialog(c);
//        pd.setTitle("Parser");
//        pd.setMessage("Parsing...");
//        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parse();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if(integer == 1)
        {
            Custom_chats adapter=new Custom_chats(airports,c);
            lv.setAdapter(adapter);




        }else
        {
            //Toast.makeText(c,"Ýalňyşlyk ýüze çykdy!!!",Toast.LENGTH_SHORT).show();
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

            for(int i=0;i<ja.length();i++){

                jo=ja.getJSONObject(i);

             if(jo.getString("oka").equals("0")) {
                 airports.add(new chats_down(jo.getString("msg").replace("&nbsp;"," "),
                         jo.getString("sender_id"),
                         jo.getString("rec_id"),
                         jo.getString("oka"),
                         jo.getString("date"),
                         (R.drawable.image41),
                         (R.drawable.image41),
                         (R.drawable.delivered)));
             }else{
                 airports.add(new chats_down(jo.getString("msg").replace("&nbsp;"," "),
                         jo.getString("sender_id"),
                         jo.getString("rec_id"),
                         jo.getString("oka"),
                         jo.getString("date"),
                         (R.drawable.image41),
                         (R.drawable.image41),
                         (R.drawable.ic_message_state_seen)));
             }


            }

            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
  }

}
