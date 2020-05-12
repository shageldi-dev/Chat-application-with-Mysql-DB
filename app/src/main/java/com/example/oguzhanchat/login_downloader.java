package com.example.oguzhanchat;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class login_downloader extends AsyncTask<Void,Integer,String> {

    Context c;
    String adress;
    ListView lv;

    ProgressDialog pd;

    public login_downloader(Context c, String adress, ListView lv) {
        this.c = c;
        this.adress = adress;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        pd = new ProgressDialog(c);
//        pd.setTitle("Fetch Data");
//        pd.setMessage("Fetching Data");
//        pd.show();

    }

    @Override
    protected String doInBackground(Void... strings) {
        String data=downloadData();
        return data;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

       // pd.dismiss();
        if(s != null){


            login_add p= null;

                p = new login_add(c, lv,s);

            p.execute();
        }
        else
        {
            //Toast.makeText(c,"Maglumatlary ýükläp bolmady!!!",Toast.LENGTH_SHORT).show();
        }
    }

    private String downloadData() {
        InputStream is = null;
        String line = null;
        try {
            URL url = new URL(adress);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(con.getInputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

            StringBuffer sb = new StringBuffer();

            if (br != null) {
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } else {
                return null;
            }

            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {

                }
            }
        }
        return null;
    }
}
