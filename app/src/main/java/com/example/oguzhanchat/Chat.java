package com.example.oguzhanchat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mysql.jdbc.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Chat extends AppCompatActivity {
    private EditText message;
    private Button sendMessageButton;
    TextView txt_reciver_name;
    ImageButton blokla_btn,refresh;
    String url="http://192.168.43.121/mobile/chats.php";
    private static String uuu="http://192.168.43.121/mobile/sent.php";
    private static String url2="http://192.168.43.121/mobile/blok.php";
    private static String url3="http://192.168.43.121/mobile/check_block.php";
    private static String url4="http://192.168.43.121/mobile/oka.php";
    String unicode = "?useUnicode=yes&characterEncoding=UTF-8";
    ListView listView;
    Context context=this;
    String msg=new String("UTF-8");
    String lvl;
    SwipeRefreshLayout pullToRefresh;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent=getIntent();
        final String reciver_name=intent.getStringExtra("name");
        lvl=intent.getStringExtra("level");
        listView=findViewById(R.id.lst);
        blokla_btn=findViewById(R.id.blokla_btn);
        refresh=findViewById(R.id.refresh);
        txt_reciver_name=(TextView)findViewById(R.id.reciver_name);
        txt_reciver_name.setText(reciver_name);
        sendMessageButton=(Button)findViewById(R.id.send_btn);
        sendMessageButton.setEnabled(false);
        sendMessageButton.setBackgroundResource(R.drawable.ic_comment_send_dis);
        message=(EditText)findViewById(R.id.habar);

        txt_reciver_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent1=new Intent(Chat.this,Profil.class);
                 intent1.putExtra("id",dkk());
                 startActivity(intent1);
            }
        });
        final login_downloader d=new login_downloader(context,url,listView);
        d.execute();




        if(Integer.parseInt(readfromfile())>Integer.parseInt(lvl)){
              blokla_btn.setEnabled(false);
              blokla_btn.setVisibility(View.GONE);
        }

        Regist();
        read();

         pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final login_downloader d=new login_downloader(context,url,listView);
                d.execute();
                refreshContent();
            }
        });
    listView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
        @Override
        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

        }
    });



        final Thread thread=new Thread(){
          @Override
          public void run(){
              while (!isInterrupted()){
                  try {
                      Thread.sleep(1000);
                      runOnUiThread(new Runnable() {
                          @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                          @Override
                          public void run() {
                              final login_downloader d=new login_downloader(context,url,listView);

                              d.execute();


                             // listView.setSelection(listView.getCount() - 1);

                          }
                      });
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
        };
        thread.start();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final login_downloader d=new login_downloader(context,url,listView);
                d.execute();
            }
        });

        blokla_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert=new AlertDialog.Builder(context);
                alert.setTitle("Üns Beriň!!!");
                alert.setMessage(reciver_name+" bloklanar!!!\nSiz razymy?");
                alert.setCancelable(false);
                alert.setNegativeButton("Ýok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.setPositiveButton("Howwa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                            blokla();
                            finish();


                    }
                });
                alert.setIcon(R.drawable.ic_warning_black_24dp);
                alert.show();
            }
        });


        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(message.getText().toString().equals("")){
                        sendMessageButton.setEnabled(false);
                        sendMessageButton.setBackgroundResource(R.drawable.ic_comment_send_dis);
                    }
                    else {
                        sendMessageButton.setEnabled(true);
                        sendMessageButton.setBackgroundResource(R.drawable.ic_comment_send_nor);
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg=message.getText().toString();
                //Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();

                try {
                    MsgSender();


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                new CountDownTimer(3000, 3000) { // adjust the milli seconds here

                    public void onTick(long millisUntilFinished) {


                    }

                    public void onFinish() {
                        final grp_chat_downloader d=new grp_chat_downloader(context,url,listView);

                        d.execute();
                    }
                }.start();
                read();


            }
        });
    }

    private void refreshContent(){

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                pullToRefresh.setRefreshing(false);
            }
        }, 3000);

    }
    // convert UTF-8 to internal Java String format
    public static String convertUTF8ToString(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

    // convert internal Java String format to UTF-8
    public static String convertStringToUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

    private void Regist(){


        final String id=dss().trim();
        final String b_id=dkk().trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            String success=jsonObject.getString("mg");
                            //Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
                           if(success.equals("1")){

                               blokla_btn.setEnabled(false);
                               blokla_btn.setVisibility(View.GONE);
                               sendMessageButton.setEnabled(false);
                               sendMessageButton.setVisibility(View.GONE);
                               message.setEnabled(false);
                               message.setText("You can't write message!");

                            }



                        }catch (JSONException e) {
                            e.printStackTrace();
                            //Toast.makeText(thiscontext,"error!",Toast.LENGTH_LONG).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(),"Logout error!",Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("id",id);
                params.put("b_id",b_id);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);


    }


    private void read(){


        final String id=dss().trim();
        final String b_id=dkk().trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url4,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            String success=jsonObject.getString("mg");
                            //Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
                            if(success.equals("1")){

                                blokla_btn.setEnabled(false);
                                blokla_btn.setVisibility(View.GONE);
                                sendMessageButton.setEnabled(false);
                                sendMessageButton.setVisibility(View.GONE);
                                message.setEnabled(false);

                                message.setText("You can't write message!");

                            }



                        }catch (JSONException e) {
                            e.printStackTrace();
                            //Toast.makeText(thiscontext,"error!",Toast.LENGTH_LONG).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(),"Logout error!",Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("id",id);
                params.put("b_id",b_id);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);


    }


    private void MsgSender() throws UnsupportedEncodingException {
        Calendar calendar=Calendar.getInstance();
        int yyl=calendar.get(Calendar.YEAR);
        int ay=calendar.get(Calendar.MONTH)+1;
        int gun=calendar.get(Calendar.DAY_OF_MONTH);
        int sagat=calendar.get(Calendar.HOUR_OF_DAY);
        int minut=calendar.get(Calendar.MINUTE);
        int sekunt=calendar.get(Calendar.SECOND);
        String s=yyl+"-"+ay+"-"+gun+" "+sagat+":"+minut+":"+sekunt;

        final String sender=dss().trim();
        final String rec=dkk().trim();
        final String msg= this.msg.trim();
        final String date=s.trim();
        final String read="0";
       //Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();


        StringRequest stringRequest=new StringRequest(Request.Method.POST, uuu,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            String success=jsonObject.getString("mg");
                          //  Toast.makeText(getApplicationContext(),success,Toast.LENGTH_SHORT).show();
                            if(success.equals("1")){
                               // Toast.makeText(getApplicationContext(),"Register success!",Toast.LENGTH_LONG).show();
                            }



                        }catch (JSONException e) {
                            e.printStackTrace();
                           // Toast.makeText(getApplicationContext(),"Register Error!"+e.toString(),Toast.LENGTH_LONG).show();

                        }
                        finally {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(),"Register error!"+error.toString(),Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("sender",sender);
                params.put("rec",rec);
                params.put("msg",msg);
                params.put("date",date);
                params.put("read",read);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        final login_downloader d=new login_downloader(context,url,listView);

        d.execute();
        message.setText("");


    }

    private void blokla(){


        final String object=dss().trim();
        final String subject=dkk().trim();
        final String subject_level=lvl.trim();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            String success=jsonObject.getString("mg");
                            //  Toast.makeText(getApplicationContext(),success,Toast.LENGTH_SHORT).show();
                            if(success.equals("1")){
                                // Toast.makeText(getApplicationContext(),"Register success!",Toast.LENGTH_LONG).show();
                            }



                        }catch (JSONException e) {
                            e.printStackTrace();
                            // Toast.makeText(getApplicationContext(),"Register Error!"+e.toString(),Toast.LENGTH_LONG).show();

                        }
                        finally {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(),"Register error!"+error.toString(),Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("object",object);
                params.put("subject",subject);
                params.put("subject_level",lvl);

                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        final login_downloader d=new login_downloader(context,url,listView);

        d.execute();
        message.setText("");


    }

    private String dss(){
        String result="";

        try{
            InputStream inputStream = openFileInput("id.txt");


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
    private String dkk(){
        String result="";

        try{
            InputStream inputStream = openFileInput("selected_user.txt");


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

    private String readfromfile(){
        String result="";

        try{
            InputStream inputStream = openFileInput("level.txt");

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
