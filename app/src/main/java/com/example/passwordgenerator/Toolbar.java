package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Toolbar extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar myToolbar;
    boolean check=false;
    int backres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
    }
    public void changeBackground(int id)
    {
        androidx.appcompat.widget.Toolbar toolbar=findViewById(R.id.toolbar_test);
        check=true;
        backres=id;
        toolbar.setBackgroundResource(id);
    }
    public void fun(androidx.appcompat.widget.Toolbar toolbar)
    {
        if(check) {
            toolbar.setBackgroundResource(backres);

        }

    }

}
