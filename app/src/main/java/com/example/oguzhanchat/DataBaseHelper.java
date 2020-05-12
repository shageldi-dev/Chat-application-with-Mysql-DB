package com.example.oguzhanchat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATA_BASE_NAME="harytlar.db";
    public static final String Table_name="studengt_table";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          db.execSQL("CREATE TABLE "+Table_name+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,sene TEXT,mashyn TEXT,nokat TEXT,resminama TEXT,olcegbirlik TEXT,baha TEXT,girdeyjimocberi TEXT,girdeyjipul TEXT,cykdayjymocberi TEXT,cykdayjypul TEXT,galyndymocberi TEXT,galyndypul TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("DROP TABLE IF EXISTS "+Table_name);
          onCreate(db);
    }

    public boolean insertData(String sene){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("sene",sene);

       long result= db.insert(Table_name,null,contentValues);
       if(result==-1){
           return false;
       }else{
           return true;
       }
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+Table_name,null);
        return res;
    }
    public boolean updateData(String uytid,String id,String sene,String Mashyn,String Nokat,String Resminama,String Olceg_Birlik,String Baha,String Girdeyji_Mocberi,String Girdeyji_Pul
            ,String Cykdayjy_Mocberi,String Cykdayjy_Pul,String Galyndy_Mocberi,String Galyndy_Pul){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("sene",sene);
        contentValues.put("ID",uytid);
        contentValues.put("mashyn",Mashyn);
        contentValues.put("nokat",Nokat);
        contentValues.put("resminama",Resminama);
        contentValues.put("olcegbirlik",Olceg_Birlik);
        contentValues.put("baha",Baha);
        contentValues.put("girdeyjimocberi",Girdeyji_Mocberi);
        contentValues.put("girdeyjipul",Girdeyji_Pul);
        contentValues.put("cykdayjymocberi",Cykdayjy_Mocberi);
        contentValues.put("cykdayjypul",Cykdayjy_Pul);
        contentValues.put("galyndymocberi",Galyndy_Mocberi);
        contentValues.put("galyndypul",Galyndy_Pul);
        db.update(Table_name,contentValues,"ID=?",new String[]{id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(Table_name,"ID=?",new String[]{id});

    }

}
