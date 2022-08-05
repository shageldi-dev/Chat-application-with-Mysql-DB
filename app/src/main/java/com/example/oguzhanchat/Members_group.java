package com.example.oguzhanchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class Members_group extends AppCompatActivity {
    Context context=this;
    String url="http://192.168.43.121/mobile/grp_members.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_group);
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        ListView lv=(ListView) findViewById(R.id.grp_members);



        Members_BackgroundWorker backgroundWorker = new Members_BackgroundWorker(context,url,lv);
        backgroundWorker.execute("login", id);
    }
}
