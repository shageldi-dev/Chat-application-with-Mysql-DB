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

import javax.net.ssl.HttpsURLConnection;

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    BackgroundWorker (Context ctx){
        context=ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String login_url="http://10.102.10.20/mobile/update.php";
        if(type.equals("login")){
            try {
                String user=params[1];
                String password=params[2];
                String name=params[3];
                String surname=params[4];
                String degree=params[5];

                String level=params[6];
                String id=params[7];

                URL url=new URL(login_url);
                HttpURLConnection httpsURLConnection= (HttpURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                OutputStream outputStream=httpsURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(user,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        +URLEncoder.encode("at","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                        +URLEncoder.encode("familya","UTF-8")+"="+URLEncoder.encode(surname,"UTF-8")+"&"
                        +URLEncoder.encode("degree","UTF-8")+"="+URLEncoder.encode(degree,"UTF-8")+"&"
                        +URLEncoder.encode("level","UTF-8")+"="+URLEncoder.encode(level,"UTF-8")+"&"
                        +URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
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
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
