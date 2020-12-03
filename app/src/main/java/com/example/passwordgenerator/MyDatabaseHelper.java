package com.example.passwordgenerator;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="PASSWORD_GENERATOR.db";
    private static final String TABLE_NAME="PASSWORD_RECORDS";
    private static final String COl_TITLE="PASSWORD_TITLE";
    private static final String COL_ID="PASSWORD_ID";
    private static final String COL_DATA="PASSOWRD";
    private static final int VERSION=1;
    Context context;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context,DB_NAME, null,VERSION);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COl_TITLE + " TEXT, " +
                COL_DATA + " TEXT );";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public void insertValues(String title,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COl_TITLE,title);
        cv.put(COL_DATA,password);
        long res=db.insert(TABLE_NAME,null,cv);
        if(res==-1)
        {
            Toast.makeText(context,"Failed!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"sucessfully saved!",Toast.LENGTH_SHORT).show();
        }
        Intent intent=new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }
    public Cursor readAllData()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        if(db!=null)
        {
            cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        }
        else
        {
            Toast.makeText(context,"empty",Toast.LENGTH_SHORT).show();
        }
        return cursor;
    }
    public void deleteOneRow(String row_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "PASSWORD_ID=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }


}
