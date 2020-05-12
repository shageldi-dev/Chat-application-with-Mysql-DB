package com.example.oguzhanchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import static androidx.core.content.ContextCompat.startActivity;

public class Login extends AppCompatActivity {
private Button login,button;
private static String URL_REGIST="http://10.102.10.20/mobile/login.php";
private EditText name,password;
Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.loginbuttonforlogin);
        name=(EditText)findViewById(R.id.at);
        password=(EditText)findViewById(R.id.parol);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //Toast.makeText(context,"Salam",Toast.LENGTH_SHORT).show();
                    Regist();
                } else {
                    Regist();
                }
            }
        });
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(Login.this,Barada.class));
            }
        });



    }


    private void Regist(){


        final String name=this.name.getText().toString().trim();
        final String password=this.password.getText().toString().trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            String success=jsonObject.getString("success");
                            String id=jsonObject.getString("id");
                            String level=jsonObject.getString("level");
                            //Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
                            if(success.equals("1")){
                               // Toast.makeText(getApplicationContext(),"Register success!",Toast.LENGTH_LONG).show();
                                writetofile("1");
                                writef(name);
                                writepass(password);
                                writeid(id);
                                writelevel(level);
                               // Toast.makeText(getApplicationContext(),level,Toast.LENGTH_SHORT).show();
                                finish();

                                startActivity(new Intent(Login.this,Main2Activity.class));
                            }



                        }catch (JSONException e) {
                            e.printStackTrace();
                           // Toast.makeText(getApplicationContext(),"Register errorgfhgfgh!",Toast.LENGTH_LONG).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Toast.makeText(getApplicationContext(),"Register error!"+error.toString(),Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("name",name);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    private void writetofile(String mydata){
        try {

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(openFileOutput("file.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(mydata);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("",e.toString());
        }
    }

    private void writef(String username){
        try {

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(openFileOutput("login.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(username);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("",e.toString());
        }
    }
    private void writelevel(String username){
        try {

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(openFileOutput("level.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(username);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("",e.toString());
        }
    }
    private void writepass(String password){
        try {

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(openFileOutput("pass.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(password);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("",e.toString());
        }
    }
    private void writeid(String id){
        try {

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(openFileOutput("id.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(id);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("",e.toString());
        }
    }

    private String readfromfile(){
        String result="";

        try{
            InputStream inputStream = openFileInput("file.txt");

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
    private String readf(){
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
    private String readpass(){
        String result="";

        try{
            InputStream inputStream = openFileInput("pass.txt");


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

    private String readid(){
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





}
