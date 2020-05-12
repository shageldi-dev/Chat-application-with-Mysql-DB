package com.example.oguzhanchat;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Notif extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    ip ip=new ip();
    Notif(Context ctx){
        context=ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String login_url="http://"+ip.ip+"/notification.php";
        if(type.equals("login")){
            try {
                String id=params[1];
                String sene=params[2];


                URL url=new URL(login_url);
                HttpURLConnection httpsURLConnection= (HttpURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                OutputStream outputStream=httpsURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+"&"
                        +URLEncoder.encode("sene","UTF-8")+"="+URLEncoder.encode(sene,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpsURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
//        alertDialog=new AlertDialog.Builder(context).create();
//        alertDialog.setTitle("Blocked Status");
    }

    @Override
    protected void onPostExecute(String result) {
        if(result != null){

            Notif_Parser p=new Notif_Parser(context, result);
            p.execute();
        }
        else
        {
            // Toast.makeText(c,"Maglumatlary ýükläp bolmady!!!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
