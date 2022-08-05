package com.example.oguzhanchat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Group_Chat extends AppCompatActivity {
    private EditText message;
    private Button sendMessageButton;

    TextView txt_reciver_name;
    String url="http://192.168.43.121/mobile/g_chat.php";
    String url1="http://192.168.43.121/mobile/leave_group.php";
    private static String uuu="http://192.168.43.121/mobile/sent_grp.php";
    private ImageButton leave_group,refresh;
    ListView listView;
    Context context=this;
    String msg="";
    String group_id;
    ImageView grp_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_chat);
        Intent intent=getIntent();
        String reciver_name=intent.getStringExtra("name");
        group_id=intent.getStringExtra("group_id");
        listView=findViewById(R.id.lst);
        txt_reciver_name=(TextView)findViewById(R.id.reciver_name);
        txt_reciver_name.setText(reciver_name);
        sendMessageButton=(Button)findViewById(R.id.send_btn);
        sendMessageButton.setEnabled(false);
        sendMessageButton.setBackgroundResource(R.drawable.ic_comment_send_dis);
        message=(EditText)findViewById(R.id.habar);
        leave_group=(ImageButton) findViewById(R.id.leave_group);
        grp_btn=(ImageView) findViewById(R.id.grp_btn);
        refresh=findViewById(R.id.refresh);
        grp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Group_Chat.this,Members_group.class);
                intent1.putExtra("id",readuser());
                startActivity(intent1);
            }
        });
        leave_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alert=new AlertDialog.Builder(context);
                alert.setCancelable(false);
                alert.setTitle("Üns Beriň!!!");
                alert.setMessage("Topardan Çykmak Isleýäňizmi?");
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Regist();
                    }
                });
                alert.show();


            }
        });
        final login_downloader d=new login_downloader(context,url,listView);
        d.execute();







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
                              final grp_chat_downloader d=new grp_chat_downloader(context,url,listView);

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
               MsgSender();
                new CountDownTimer(3000, 3000) { // adjust the milli seconds here

                    public void onTick(long millisUntilFinished) {


                    }

                    public void onFinish() {
                        final grp_chat_downloader d=new grp_chat_downloader(context,url,listView);

                        d.execute();
                    }
                }.start();

            }



        });
    }

    private void MsgSender(){
        Calendar calendar=Calendar.getInstance();
        int yyl=calendar.get(Calendar.YEAR);
        int ay=calendar.get(Calendar.MONTH)+1;
        int gun=calendar.get(Calendar.DAY_OF_MONTH);
        int sagat=calendar.get(Calendar.HOUR_OF_DAY);
        int minut=calendar.get(Calendar.MINUTE);
        int sekunt=calendar.get(Calendar.SECOND);
        String s=yyl+"-"+ay+"-"+gun+" "+sagat+":"+minut+":"+sekunt;

        final String sender=dss().trim();
        final String rec=readuser().trim();
        final String msg=this.msg.trim();
        final String date=s.trim();
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
                params.put("user_id",sender);
                params.put("group_name",rec);
                params.put("msg",msg);
                params.put("date",date);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        final login_downloader d=new login_downloader(context,url,listView);

        d.execute();
        message.setText("");


    }



    private void Regist(){

        final String[] ok = new String[1];

        final String galyndy_mocberi = dkk().trim();
        final String galyndy_pul = group_id.trim();
        final String id = dss().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    String success=jsonObject.getString("success");
                    //  Toast.makeText(getApplicationContext(),success,Toast.LENGTH_SHORT).show();
                    if(success.equals("1")){
                        ok[0] ="Maglumatlar baza ugradyldy!";
                        finish();
                    }



                }catch (JSONException e) {
                    e.printStackTrace();
                    ok[0] ="Maglumatlar baza ugradylmady!"+e.toString();

                }
                finally {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ok[0] ="Maglumatlar baza ugradylmady!"+error.toString();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("id", galyndy_mocberi);
                params.put("b_id", galyndy_pul);
                params.put("id1", id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


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
            InputStream inputStream = openFileInput("login.txt");


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

    private String readuser(){
        String result="";

        try{
            InputStream inputStream = context.openFileInput("selected_group.txt");


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
