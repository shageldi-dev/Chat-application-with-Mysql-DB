package com.example.oguzhanchat;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class degree_block_downloader extends AsyncTask<Void,Integer,String> {

    Context c;
    String adress;
    ListView lv;

    ProgressDialog pd;

    public degree_block_downloader(Context c, String adress, ListView lv) {
        this.c = c;
        this.adress = adress;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Maglumatlar ýüklenýär");
        pd.setMessage("Maglumatlar ýüklenýär...");
        pd.show();

    }

    @Override
    protected String doInBackground(Void... strings) {
        String data=downloadData();
        return data;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        pd.dismiss();
        if(s != null){

            Degree_Block_Parser p=new Degree_Block_Parser(c, lv, s);
            p.execute();
        }
        else
        {
          //  Toast.makeText(c,"Maglumatlary ýükläp bolmady!!!",Toast.LENGTH_SHORT).show();
        }
    }

    private String downloadData() {
        InputStream is = null;
        String line = null;
        try {
            URL url = new URL(adress);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(con.getInputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

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
