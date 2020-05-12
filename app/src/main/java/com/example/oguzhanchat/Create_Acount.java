package com.example.oguzhanchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Create_Acount extends AppCompatActivity{
    private EditText name,password,c_password,at,familya;
    private Button btn_regist;
    private Spinner f;
    String dereje,bolum,level,faculty="mm";
    private ProgressBar loading;
    Spinner customSpinner;
    Spinner department;
    ArrayList<String> customList;
    ArrayList<CustomItem1> arrayList;
    ArrayList<CustomItem1> arrayList1;
    private static String URL_REGIST="http://10.102.10.20/mobile/register.php";
    String url="http://10.102.10.20/mobile/degree.php";
    Context context=this;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__acount);
        btn_regist=(Button)findViewById(R.id.createacount);
        loading=(ProgressBar) findViewById(R.id.progressBar);
        name=(EditText)findViewById(R.id.username);
        at=(EditText)findViewById(R.id.ady);
        familya=(EditText)findViewById(R.id.familiya);
        password=(EditText)findViewById(R.id.passwordspace);
        c_password=(EditText)findViewById(R.id.confirm_password);

        customSpinner=findViewById(R.id.customIconSpinner);
        arrayList=getCustomList();
        arrayList1=getCustomList1();
        final Degree_Downloader d=new Degree_Downloader(context,url,customSpinner);

        d.execute();
        customSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CustomItem item=(CustomItem)parent.getSelectedItem();
                dereje=item.getSpinnerItemName();
                level=item.getSpinnerItemLevel();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        //customList=getCustomList();
//        CustomAdapter1 adapter1=new CustomAdapter1(this,customList);
//        if (customSpinner!=null) {
//            customSpinner.setAdapter(adapter1);
//            customSpinner.setOnItemSelectedListener(this);
//        }



        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuffer habar=new StringBuffer();
                if(password.getText().toString().equals(c_password.getText().toString()))
                {
                    if(name.getText().toString().isEmpty())
                    {
                        habar.append("Write Your Username!!!\n");

                    }
                        if(at.getText().toString().isEmpty())
                    {
                        habar.append("Write Your Name!!!\n");

                    }
                        if(familya.getText().toString().isEmpty())
                        {
                            habar.append("Write Your Surname!!!\n");

                        }
                        if(password.getText().toString().isEmpty())
                        {
                            habar.append("Write Your Password!!!\n");

                        }
                        if(c_password.getText().toString().isEmpty())
                        {
                            habar.append("Write Your Confirm Password!!!\n");

                        }
                       if(name.getText().toString().length()!=0 && at.getText().toString().length()!=0 && familya.getText().toString().length()!=0 && password.getText().toString().length()!=0){
                           Regist();
                       }

                    Toast.makeText(getApplicationContext(),habar,Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wrong Password!!!",Toast.LENGTH_SHORT).show();
                }





            }
        });

    }

//    private ArrayList<CustomItem> getCustomList() {
//        customList=new ArrayList<>();
//        customList.add(new CustomItem("Android",R.drawable.ic_account_box_black_24dp));
//        customList.add(new CustomItem("IPhone",R.drawable.ic_block_black_24dp));
//        return customList;
//    }

    private void Regist(){

        loading.setVisibility(View.VISIBLE);
        btn_regist.setVisibility(View.GONE);
        final String name=this.name.getText().toString().trim();
        final String at=this.at.getText().toString().trim();
        final String familya=this.familya.getText().toString().trim();
        final String password=this.password.getText().toString().trim();
        final String c_password=this.c_password.getText().toString().trim();
        final String degree=this.dereje;
        final String level=this.level;


        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       try{
                           JSONObject jsonObject=new JSONObject(response);
                           String success=jsonObject.getString("success");

                           if(success.equals("1")){
                               Toast.makeText(getApplicationContext(),"Acount Created.Welcome to Oguzhan Messenger!!!",Toast.LENGTH_LONG).show();
                               loading.setVisibility(View.GONE);
                               btn_regist.setVisibility(View.VISIBLE);


                               finish();
                           }



                       }catch (JSONException e) {
                           e.printStackTrace();
                           Toast.makeText(getApplicationContext(),"Register Error!"+e.toString(),Toast.LENGTH_LONG).show();
                           loading.setVisibility(View.GONE);
                           btn_regist.setVisibility(View.VISIBLE);
                       }
                       finally {
                           loading.setVisibility(View.GONE);
                           btn_regist.setVisibility(View.VISIBLE);
                       }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Register error!"+error.toString(),Toast.LENGTH_LONG).show();
                        loading.setVisibility(View.GONE);
                        btn_regist.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("name",name);
                params.put("password",password);
                params.put("at",at);
                params.put("familya",familya);
                params.put("degree",degree);
                params.put("level",level);

                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
    private ArrayList<CustomItem1> getCustomList() {
        arrayList=new ArrayList<>();

        arrayList.add(new CustomItem1("Faculty",R.drawable.logolib));
        arrayList.add(new CustomItem1("Computer science and information technologies",R.drawable.logolib));
        arrayList.add(new CustomItem1("Biology and ecology",R.drawable.logolib));
        arrayList.add(new CustomItem1("Awtomatika",R.drawable.logolib));
        arrayList.add(new CustomItem1("Innowation",R.drawable.logolib));
        arrayList.add(new CustomItem1("Chemistry and Nanomaterial",R.drawable.logolib));
        return arrayList;
    }

    private ArrayList<CustomItem1> getCustomList1() {
        arrayList1=new ArrayList<>();

        arrayList1.add(new CustomItem1("Department",R.drawable.logolib));
        arrayList1.add(new CustomItem1("Computer",R.drawable.logolib));
        arrayList1.add(new CustomItem1("Biology",R.drawable.logolib));
        arrayList1.add(new CustomItem1("Awtomatika",R.drawable.logolib));
        arrayList1.add(new CustomItem1("Innowation",R.drawable.logolib));
        arrayList1.add(new CustomItem1("Chemistry",R.drawable.logolib));
        return arrayList1;
    }




//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            CustomItem item=(CustomItem)parent.getSelectedItem();
//        Toast.makeText(this, item.getSpinnerItemName(), Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }



}
