package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import maes.tech.intentanim.CustomIntent;

public class Developer extends AppCompatActivity {
    ImageView linkedin,instagram;
    LottieAnimationView lottieAnimationView_linkedin,lottieAnimationView_instagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        lottieAnimationView_instagram=findViewById(R.id.animationview_instagram);
        lottieAnimationView_linkedin=findViewById(R.id.animationview_linkedin);
        lottieAnimationView_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("https://www.instagram.com/_arvind_manu_/");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        lottieAnimationView_linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("https://www.linkedin.com/in/aravinderamalla");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CustomIntent.customType(this,"fadein-to-fadeout");
    }
}
