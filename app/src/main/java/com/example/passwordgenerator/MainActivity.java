package com.example.passwordgenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends com.example.passwordgenerator.Toolbar implements NavigationView.OnNavigationItemSelectedListener {
    Button connect;
    AlertDialog alertDialog;
    protected  DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button save;
    ActionBarDrawerToggle toggle;
    EditText passoword_length;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connect=findViewById(R.id.button_generate_password);
        drawerLayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.navigation_view);
        save=findViewById(R.id.save);
        toolbar=findViewById(R.id.toolbar_main);
        passoword_length=findViewById(R.id.editText_length);
        setSupportActionBar(toolbar);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        navigationView.setCheckedItem(R.id.navigation_view);

        final CustomDialog customDialog=new CustomDialog(this);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                if(passoword_length.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter the password Length",Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(passoword_length.getText().toString())<8)
                {
                    Toast.makeText(getApplicationContext(),"The password length should be >=8",Toast.LENGTH_SHORT).show();
                }
                else {
                    customDialog.CreateCustomDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            customDialog.CustomDialogDismiss();
                            Intent intent = new Intent(MainActivity.this, PasswordDisplay.class);
                            intent.putExtra("length", passoword_length.getText().toString());
                            passoword_length.setText("");
                            startActivity(intent);

                        }
                    }, 5000);
                }
            }

        });
        customs();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            moveTaskToBack(true);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId())
        {
            case R.id.Developer:
                menuItem.setCheckable(false);
                Intent intent=new Intent(MainActivity.this,Developer.class);
                   startActivity(intent);
                CustomIntent.customType(this,"fadein-to-fadeout");
                   break;
            case R.id.record:
                menuItem.setCheckable(false);
                Intent intent1=new Intent(MainActivity.this,Record_Activity.class);
                startActivity(intent1);
                CustomIntent.customType(this,"fadein-to-fadeout");

                break;
            case R.id.Pasword_strength_checker:
                menuItem.setCheckable(false);
                Intent intent2=new Intent(MainActivity.this,PasswordStrengthChecker.class);
                startActivity(intent2);
                CustomIntent.customType(this,"fadein-to-fadeout");

                break;
            case R.id.deleteAll:
                menuItem.setCheckable(false);
                confirmDialog();
                break;
            case R.id.AppVersion:
                menuItem.setCheckable(false);
                Toast.makeText(getApplicationContext(),"App Version 1.0",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Share:
                menuItem.setCheckable(false);
                share();
                break;
            case R.id.RateUs:
                menuItem.setCheckable(false);
                rateus();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                CustomIntent.customType(getApplicationContext(),"fadein-to-fadeout");
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        final AlertDialog dialog=builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.black));
                //dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(getResources().getColor(R.color.black));
            }
        });
        dialog.show();


    }
    void closeKeyboard()
    {
        View view=this.getCurrentFocus();
        if(view!=null)
        {
            InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
    public void customs()
    {
        if(getIntent().hasExtra("key_custom1"))
        {
            toolbar.setBackgroundResource(getIntent().getIntExtra("key_custom1",0));
            connect.setBackgroundResource(getIntent().getIntExtra("key_custom1",0));
        }

    }
    public void share()
    {
        int applicationNameId = this.getApplicationInfo().labelRes;
        final String appPackageName =this.getPackageName();
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, this.getString(applicationNameId));
        String text = "Install this cool application: ";
        String link = "https://play.google.com/store/apps/details?id=" + appPackageName;
        i.putExtra(Intent.EXTRA_TEXT, text + " " + link);
        startActivity(Intent.createChooser(i, "Share link:"));
    }
    public void rateus()
    {
        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}