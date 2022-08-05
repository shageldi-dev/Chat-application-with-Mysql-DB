package com.example.oguzhanchat;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FragmentToparlar extends Fragment {
    Context thiscontext;
    String url="http://192.168.43.121/mobile/topar.php";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.toparlar,container,false);
        thiscontext = container.getContext();
        final ListView lv=(ListView) view.findViewById(R.id.groups);


        final DownloaderTopar d=new DownloaderTopar(thiscontext,url,lv);

        d.execute();
        return view;
    }
}
