package com.example.oguzhanchat;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class grp_chat_parser extends AsyncTask<Void,Integer,Integer> {

    Context c;
    ListView lv;
    String data;

    ArrayList<String> players=new ArrayList<>();
    ArrayList<group_chat_down> airports=new ArrayList<>();
    ProgressDialog pd;

    public grp_chat_parser(Context c, ListView lv, String data) {
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
            Custom_group_chat adapter=new Custom_group_chat(airports,c);
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

            for(int i=0;i<ja.length();i++) {

                jo = ja.getJSONObject(i);

                airports.add(new group_chat_down(jo.getString("id"), jo.getString("user_id"), jo.getString("msg"), jo.getString("g_name"),
                        jo.getString("date"),jo.getString("username")));

            }


            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
  }

}
