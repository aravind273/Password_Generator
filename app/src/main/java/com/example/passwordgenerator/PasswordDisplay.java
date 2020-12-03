package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

import maes.tech.intentanim.CustomIntent;

public class PasswordDisplay extends AppCompatActivity {

    TextView password_textview;
    Button save,copy,editext_save;
    EditText editText_title;
    SQLiteDatabase db;
    //LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_display);
        password_textview=findViewById(R.id.password_textview);
        save=findViewById(R.id.save);
        copy=findViewById(R.id.copy);
        editext_save=findViewById(R.id.edittext_save);
        editText_title=findViewById(R.id.edittext_title);
        showPassword();
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyPassword();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePassword();
            }
        });
    }

    private void savePassword() {
        final String password=password_textview.getText().toString();
        CustomDialog2 customDialog2=new CustomDialog2(this,password);
        customDialog2.CreateCustomDialog2();

    }

    public void showPassword()
    {
        String s1=getIntent().getStringExtra("length");
        long n=Integer.parseInt(s1);
        Random random=new Random();
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        String res="";
        res+=capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        res+=specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        res+=lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        res+=numbers.charAt(random.nextInt(numbers.length()));
        for(int i=4;i<n;i++)
        {
            res+=combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        password_textview.setText(res);

    }
    public void copyPassword()
    {

        ClipboardManager clipboardManager=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData=ClipData.newPlainText("Text",password_textview.getText().toString());
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(getApplicationContext(),"copied",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        CustomIntent.customType(this,"fadein-to-fadeout");

    }
}
