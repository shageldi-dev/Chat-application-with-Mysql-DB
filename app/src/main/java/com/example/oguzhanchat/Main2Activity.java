package com.example.oguzhanchat;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.text.Layout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
   final public static ip ig=new ip();
        String url="http://"+ig.ip+"/regist.php";
    List<Airport> airportList=new ArrayList<>();
    private static String URL_REGISTt = "http://"+ig.ip+"/r.php";
    private static String URL_REGIST="http://"+ig.ip+"/logout.php";
    private static String ur="http://"+ig.ip+"/login.php";
        Context context=this;

        ArrayList<String> arrayList=new ArrayList<>();
        String s="";

    final static int id_poz=Menu.FIRST;
    final static int id_block=Menu.FIRST+1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Registin();
        loadLocale();
        arrayList.add("salam");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,android.R.id.text1,arrayList);



//        final Downloader d=new Downloader(this,url,lv);
//
//        d.execute();



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        FragmentHome fragmentHome=new FragmentHome();
        fragmentTransaction.replace(R.id.content_frame,fragmentHome);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {



            Intent newIntent = new Intent(this,Quit.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newIntent);
            finish();
            Regist();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(Main2Activity.this,Sazlamalar.class));
            finish();

        } else if(id==R.id.profile){
            startActivity(new Intent(Main2Activity.this,Update_Acount.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        int id = item.getItemId();
        if (id == R.id.nav_hatlar) {
            FragmentHatlar fragmentHatlar=new FragmentHatlar();
            fragmentTransaction.replace(R.id.content_frame,fragmentHatlar).commit();
        } else
        if (id == R.id.nav_home) {
            FragmentHome fragmentHome=new FragmentHome();
            fragmentTransaction.replace(R.id.content_frame,fragmentHome).commit();
        } else
        if (id == R.id.nav_tools) {
            FragmentSettings fragmentSettings=new FragmentSettings();
            fragmentTransaction.replace(R.id.content_frame,fragmentSettings).commit();
        } else if (id == R.id.nav_slideshow) {
            FragmentCreateGroup fragmentCreateGroup=new FragmentCreateGroup();
            fragmentTransaction.replace(R.id.content_frame,fragmentCreateGroup).commit();


        } else if (id == R.id.toparlar) {
            FragmentToparlar fragmentToparlar=new FragmentToparlar();
            fragmentTransaction.replace(R.id.content_frame,fragmentToparlar).commit();


        }else if (id == R.id.degree_block) {
            Degree_Block degree_block=new Degree_Block();
            fragmentTransaction.replace(R.id.content_frame,degree_block).commit();


        } else if (id == R.id.bloklananlar) {
            FragmentBlokalananlar fragmentBlokalananlar=new FragmentBlokalananlar();
            fragmentTransaction.replace(R.id.content_frame,fragmentBlokalananlar).commit();

        } else if (id == R.id.nav_share) {
            Intent shareIntent =new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("*/*");
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            Uri uri =Uri.parse("/data/apps/"+getApplicationContext().getPackageName()+".apk");
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            startActivity(Intent.createChooser(shareIntent,"Paýlaş"));

        } else if (id == R.id.nav_send) {
            writetofile("0");
             Regist();
            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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


    private void Regist(){


        final String name=readf().trim();
        final String password=readpass().trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            String success=jsonObject.getString("success");
                            String id=jsonObject.getString("id");
                            //Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
                            if(success.equals("1")){

                                //Toast.makeText(getApplicationContext(),"Logout success!",Toast.LENGTH_LONG).show();

                            }



                        }catch (JSONException e) {
                            e.printStackTrace();
                            //Toast.makeText(getApplicationContext(),"Logout error!",Toast.LENGTH_LONG).show();

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
                params.put("name",name);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


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

    private void Registin(){


        final String name=readf().trim();
        final String password=readpass().trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, ur,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            String success=jsonObject.getString("success");
                            String id=jsonObject.getString("id");
                            //Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
                            if(success.equals("1")){
                                //Toast.makeText(getApplicationContext(),"Register success!",Toast.LENGTH_LONG).show();
                            }



                        }catch (JSONException e) {
                            e.printStackTrace();
                            //Toast.makeText(getApplicationContext(),"Register error!",Toast.LENGTH_LONG).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Toast.makeText(getApplicationContext(),"Register error!",Toast.LENGTH_LONG).show();

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


    private void setLocale(String lang){
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration config=new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getResources().getDisplayMetrics());


        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();


    }

    public void loadLocale(){
        SharedPreferences prefs=getSharedPreferences("Settings",MODE_PRIVATE);
        String language=prefs.getString("My_Lang","");

        setLocale(language);
    }

    @SuppressWarnings("deprecation")
    private void updateConfiguration(Configuration config) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getBaseContext().createConfigurationContext(config);
        } else {
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
    }









}
