package com.example.oguzhanchat;


import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringJoiner;

public class FragmentCreateGroup extends Fragment {
    Context thiscontext;
    String url="http://192.168.43.121/mobile/regist.php";
    private static String URL_REGIST="http://192.168.43.121/mobile/create_group.php";
    ArrayList<String> arrayList=new ArrayList<>();
    List<Group> aaa=new ArrayList<>();
    String string[];
    Button crt_btn;
    EditText topar_ady;
    Random r = new Random();
    int i1;
    int j=0;
    StringBuilder builder = new StringBuilder();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.create_group,container,false);
        final ListView lv=(ListView) view.findViewById(R.id.group_members);
        crt_btn=(Button) view.findViewById(R.id.crt_btn);
        topar_ady=(EditText) view.findViewById(R.id.topar_ady);
        thiscontext = container.getContext();

        final GroupDownloader d=new GroupDownloader(thiscontext,url,lv);

        d.execute();
        crt_btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

               String s=readf();
               String grp = "";

                    arrayList.clear();

               for(int i=0;i<s.length();i++){
                   if(String.valueOf(s.charAt(i)).equals("[") || String.valueOf(s.charAt(i)).equals("]"))
                   {

                   }
                   else
                   {

                        if(String.valueOf(s.charAt(i)).equals(",")){
                            for(int r=0;r<arrayList.size();r++)
                            {
                                if(arrayList.get(r).equals(grp)){
                                    arrayList.remove(r);
                                }
                            }
                            arrayList.add(grp);
                            grp="";
                        }else
                        {
                            grp+=String.valueOf(s.charAt(i));
                        }






                   }

               }
                i1 = (r.nextInt(8980) + 6575)*234+r.nextInt(2398);
                arrayList.add(grp);
                if(topar_ady.getText().toString().isEmpty())
                {
                    Toast.makeText(thiscontext,"Topar adyny giriziň!!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(arrayList.toString().equals("[]")){
                    Toast.makeText(thiscontext,"Topar agzalaryny saýlamadyňyz!!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                arrayList.add(readlogin());


               for(int k=0;k<arrayList.size();k++){
                   Regist();
                   j++;
               }
               //Toast.makeText(thiscontext,arrayList.toString(),Toast.LENGTH_SHORT).show();
                writetofile("");
                final GroupDownloader d=new GroupDownloader(thiscontext,url,lv);

                d.execute();
                topar_ady.setText("");
            }
        });
        return view;


    }

    private String readf(){
        String result="";

        try{
            InputStream inputStream = thiscontext.openFileInput("create_group.txt");


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
    private void writetofile(String mydata){
        try {

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(thiscontext.openFileOutput("create_group.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(mydata);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("",e.toString());
        }
    }


    private void Regist(){


        final String name=topar_ady.getText().toString().trim();
        final String grp_id=String.valueOf(i1).trim();
        final String creater=readlogin().trim();
        final String username=arrayList.get(j).trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            String success=jsonObject.getString("success");

                            if(success.equals("1")){
                                Toast.makeText(thiscontext,"Topar Döredildi!",Toast.LENGTH_LONG).show();
                            }



                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(thiscontext,"Topar Döredip bolmady!",Toast.LENGTH_LONG).show();

                        }
                        finally {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(thiscontext,"Topar Döredip bolmady!",Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("name",name);
                params.put("grp_id",grp_id);
                params.put("creater",creater);
                params.put("username",username);

                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(thiscontext);
        requestQueue.add(stringRequest);


    }

    private String readid(){
        String result="";

        try{
            InputStream inputStream = thiscontext.openFileInput("id.txt");


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

    private String readlogin(){
        String result="";

        try{
            InputStream inputStream = thiscontext.openFileInput("login.txt");


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
