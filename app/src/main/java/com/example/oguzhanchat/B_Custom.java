package com.example.oguzhanchat;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class B_Custom extends BaseAdapter {
    List<B> bList=new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    String url="http://192.168.43.121//mobile/delete_b.php";

    public B_Custom(List<B> bList, Context context) {
        this.bList = bList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final B b=bList.get(position);

        inflater=LayoutInflater.from(context);
        View satir=inflater.inflate(R.layout.blokk,null);
        final Button blodan_ac=(Button) satir.findViewById(R.id.blokdan_ac);
        TextView name_surname=(TextView) satir.findViewById(R.id.name_surname);
        final ImageView bb=(ImageView) satir.findViewById(R.id.jj);
        TextView username_degree=(TextView) satir.findViewById(R.id.username_degree);
        TextView FacultyTxt=(TextView) satir.findViewById(R.id.FacultyTxt);
        TextView departmenttxt=(TextView) satir.findViewById(R.id.departmenttxt);
        name_surname.setText(b.getAt()+" "+b.getFamilya());
        username_degree.setText(b.getUsername()+"-"+b.getDegree());
        FacultyTxt.setText(b.getFaculty());
        departmenttxt.setText(b.getDepartment());

        blodan_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=b.getId();
                Regist(id);
                blodan_ac.setEnabled(false);
                blodan_ac.setBackgroundColor(Color.parseColor("#07EE10"));
                blodan_ac.setText("Blokdan Acyldy");
                bb.setImageResource(R.drawable.smile);






            }
        });
        return satir;

    }

    private void Regist(String k){


        final String id=readf().trim();
        final String b_id=k.trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            String success=jsonObject.getString("success");
                            String id=jsonObject.getString("id");
                            //Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
                            if(success.equals("1")){

                                //Toast.makeText(context,"success!",Toast.LENGTH_LONG).show();

                            }



                        }catch (JSONException e) {
                            e.printStackTrace();
                           // Toast.makeText(context,"error!",Toast.LENGTH_LONG).show();

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
    private String readf(){
        String result="";

        try{
            InputStream inputStream = context.openFileInput("id.txt");


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
