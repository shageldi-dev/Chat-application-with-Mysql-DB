package com.example.oguzhanchat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Custom_group extends BaseAdapter {
    LayoutInflater inflater;
    StringBuffer stringBuffer=null;
    List<Group> groupList=new ArrayList<>();
    ArrayList<String> group_member=new ArrayList<>();
    Context context;

    public Custom_group(List<Group> groupList, Context context) {
        this.groupList = groupList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return groupList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.topar_doretmek_layout,null);
        CheckBox checkBox=(CheckBox) view.findViewById(R.id.member_chek);
        TextView name_surname=(TextView) view.findViewById(R.id.name_surname);
        TextView username_degree=(TextView) view.findViewById(R.id.username_degree);
        TextView FacultyTxt=(TextView) view.findViewById(R.id.FacultyTxt);

        final Group group=groupList.get(position);
        name_surname.setText(group.getAt()+" "+group.getFamilya());
        username_degree.setText(group.getUsername());
        FacultyTxt.setText(group.getDegree());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    group_member.add(group.getUsername());
                    create_file(group_member.toString());

                }else{

                    for(int i=0;i<group_member.size();i++){
                        if(group_member.get(i).equals(group.getUsername()))
                            group_member.remove(i);

                    }

                    create_file(group_member.toString());
                }





            }
        });



        return view;
    }

    public ArrayList<String> getGroup_member() {
        return group_member;
    }

    private void writetofile(String mydata){
        try {

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(context.openFileOutput("create_group.txt", Context.MODE_APPEND));
            outputStreamWriter.write(mydata);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("",e.toString());
        }
    }

    private void create_file(String mydata){
        try {

            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(context.openFileOutput("create_group.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(mydata);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("",e.toString());
        }
    }

    public void addTextToFile(String text) {
        try {
            File log = new File("create_group.txt");

            BufferedWriter bufferedWriter = null;
            if (log.exists() == false) {
                bufferedWriter = new BufferedWriter(new FileWriter(log + "\n"));


            } else {
                bufferedWriter = new BufferedWriter(new FileWriter(log + "\n", true));

            }
            String[] lines = text.split("\n");
            for (String line : lines) {
                bufferedWriter.write(line);

            }
            bufferedWriter.close();

        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

}
