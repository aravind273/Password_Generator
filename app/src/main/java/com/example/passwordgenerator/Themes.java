package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;


public class Themes extends com.example.passwordgenerator.Toolbar {
    Button button;
    Toolbar toolbar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);
        button=findViewById(R.id.button);
        toolbar=findViewById(R.id.toolbar_test);
        toolbar.setTitle("Themes");
        fun(toolbar);
        setSupportActionBar(toolbar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent1=new Intent(getApplicationContext(),MainActivity.class);
//                intent1.putExtra("key_custom1",R.drawable.custom_button_shape1);
//                startActivity(intent1);
//               // Intent intent2=new Intent(getApplicationContext(),Record_Activity.class);
//                //intent2.putExtra("key_custom1",R.drawable.custom_button_shape1);
                changeBackground(R.drawable.custom_button_shape1);

            }
        });
    }
}
