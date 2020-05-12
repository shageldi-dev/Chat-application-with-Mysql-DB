package com.example.oguzhanchat;

import android.app.Application;
import android.content.Context;

import com.example.oguzhanchat.Helper.LocalHelper;

public class MainApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocalHelper.onAttach(base,"en"));
    }
}
