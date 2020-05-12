package com.example.oguzhanchat;

import android.content.Context;
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

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Update_Acount extends AppCompatActivity{
    private EditText name,password,c_password,at,familya;
    private Button btn_regist;

    String dereje,bolum,level,faculty="mm";
    private ProgressBar loading;
    Spinner customSpinner;

    ArrayList<String> customList;
    ArrayList<CustomItem1> arrayList;
    ArrayList<CustomItem1> arrayList1;
    private static String URL_REGIST="http://10.102.10.20/mobile/update.php";
    private static String URL_REGIST1="http://10.102.10.20/mobile/setcookie.php";
    String url="http://10.102.10.20/mobile/degree.php";
    Context context=this;
    String ok;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__acount);
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
               // Toast.makeText(context,dereje,Toast.LENGTH_SHORT).show();

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
                        name.requestFocus();
                        return;

                    }
                        if(at.getText().toString().isEmpty())
                    {
                        at.requestFocus();
                        return;

                    }
                        if(familya.getText().toString().isEmpty())
                        {
                            familya.requestFocus();
                            return;

                        }
                        if(password.getText().toString().isEmpty())
                        {
                            password.requestFocus();
                            return;

                        }
                        if(c_password.getText().toString().isEmpty())
                        {
                            c_password.requestFocus();
                            return;

                        }
                       if(name.getText().toString().length()!=0 && at.getText().toString().length()!=0 && familya.getText().toString().length()!=0 && password.getText().toString().length()!=0){
                           Regist();
                       }

                   // Toast.makeText(getApplicationContext(),habar,Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Passwords aren't same!!!",Toast.LENGTH_SHORT).show();
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



        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute("login",name.getText().toString(),password.getText().toString(),
                at.getText().toString(),familya.getText().toString(),
                dereje,level,readid());





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
