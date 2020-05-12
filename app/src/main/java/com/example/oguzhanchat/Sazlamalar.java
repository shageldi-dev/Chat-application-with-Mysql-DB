package com.example.oguzhanchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.oguzhanchat.Helper.LocalHelper;

import java.util.Locale;


public class Sazlamalar extends AppCompatActivity {
    Spinner choose_lang;
    Button save;
    String s = "";
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sazlamalar);
        save = (Button) findViewById(R.id.select_lang);
        choose_lang = (Spinner) findViewById(R.id.dil_sayla);
        choose_lang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s = choose_lang.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s.equals("Türkmen Dili")){
                    setLocale("tm");
                }
                if(s.equals("English")){
                    setLocale("en");
                }
                if(s.equals("Russian")){
                    setLocale("ru");
                }
                startActivity(new Intent(Sazlamalar.this,Main2Activity.class));

            }
        });


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
       // Toast.makeText(context,language,Toast.LENGTH_SHORT).show();
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


    @Override
    public void onBackPressed() {
        startActivity(new Intent(Sazlamalar.this,Main2Activity.class));
    }
}
