package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import maes.tech.intentanim.CustomIntent;

public class Record_Activity extends com.example.passwordgenerator.Toolbar {
    RecyclerView recyclerView;
    MyDatabaseHelper myDatabaseHelper;
    Toolbar toolbar_record;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myDatabaseHelper=new MyDatabaseHelper(getApplicationContext());
        toolbar_record=findViewById(R.id.toolbar_record);
        Cursor cursor=myDatabaseHelper.readAllData();
        ArrayList<String> title=new ArrayList<>();
        ArrayList<String> password=new ArrayList<>();
        ArrayList<String> Id=new ArrayList<>();
        while(cursor.moveToNext())
        {
            Id.add(cursor.getString(0));
            title.add(cursor.getString(1));
            password.add(cursor.getString(2));
        }
        if(title.size()==0)
        {
            Intent intent=new Intent(getApplicationContext(),EmptyImageDisplay.class);
            startActivity(intent);
            CustomIntent.customType(this,"fadein-to-fadeout");

        }
        else {
            recyclerView.setAdapter(new CustomAdapter(getApplicationContext(), title, password, Id));
        }

    }

}
