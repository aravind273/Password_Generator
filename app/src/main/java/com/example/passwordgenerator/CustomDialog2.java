package com.example.passwordgenerator;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomDialog2 {
    Activity activity;
    AlertDialog alertDialog;
    EditText title;
    Button save;
    String password;
    CustomDialog2(Activity activity,String Password)
    {
        this.activity=activity;
        this.password=Password;
    }
    public void CreateCustomDialog2()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        View view=activity.getLayoutInflater().inflate(R.layout.custom_dialog2,null);
        builder.setView(view);
        alertDialog=builder.create();
        builder.show();
        title=view.findViewById(R.id.edittext_title);
        save=view.findViewById(R.id.edittext_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title1=title.getText().toString();
                MyDatabaseHelper mydb=new MyDatabaseHelper(activity);
                mydb.insertValues(title1,password);
            }
        });

    }
    public void DismissCustomDialog2()
    {
        alertDialog.dismiss();
    }
}