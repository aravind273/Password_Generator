package com.example.passwordgenerator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import maes.tech.intentanim.CustomIntent;

public class PasswordStrengthChecker extends AppCompatActivity {
    EditText editText_pschecker;
    Button pschecker;
    String hints="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_password_strength_checker);
        editText_pschecker=findViewById(R.id.editext_password);
        pschecker=findViewById(R.id.button_check);
        pschecker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                String password=editText_pschecker.getText().toString().trim();
                if(password.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter the password",Toast.LENGTH_SHORT).show();

                }
                else
                {

                    long uppercase=0,lowercase=0,symbols=0,digits=0;
                    double uppercasePer=0,lowercasePer=0,symbolsPer=0,digitsPer=0;
                    for(int i=0;i<password.length();i++)
                    {
                        if(Character.isLowerCase(password.charAt(i)))
                        {
                            lowercase++;
                            if(lowercase>=2)
                            {
                                lowercasePer=25;
                            }
                            else
                            {
                                lowercasePer+=12.5;
                            }
                        }
                        else if(Character.isUpperCase(password.charAt(i)))
                        {
                            uppercase++;
                            if(uppercase>=2)
                            {
                                uppercasePer=25;
                            }
                            else
                            {
                                uppercasePer+=12.5;
                            }

                        }
                        else if(Character.isDigit(password.charAt(i)))
                        {
                            digits++;
                            if(digits>=2)
                            {
                                digitsPer=25;
                            }
                            else
                            {
                                digitsPer+=12.5;
                            }
                        }
                        else
                        {
                            symbols++;
                            if(symbols>=2)
                            {
                                symbolsPer=25;
                            }
                            else
                            {
                                symbolsPer+=12.5;
                            }

                        }
                    }
                    double totalpercentage=uppercasePer+lowercasePer+symbolsPer+digitsPer;
                    if(((int)totalpercentage)==100)
                    {
                        Toast.makeText(getApplicationContext(),"100% Strong Password",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        hints = "";
                        if (password.length() < 8) {
                            hints += "-> Include " + (8 - password.length()) + " more characters\n";
                        }
                        if (symbols <= 2) {
                            hints += "-> Include " + (2 - symbols) + " more symbols\n";
                        }
                        if (lowercase <= 2) {
                            hints += "-> Include " + (2 - lowercase) + " more lower case characters\n";
                        }
                        if (uppercase <= 2) {
                            hints += "-> Include " + (2 - uppercase) + " more upper case characters\n";
                        }
                        if (digits <= 2) {
                            hints += "-> Include " + (2 - digits) + " more digits\n";
                        }
                       hints+="-> "+totalpercentage+"% Strong";
                        AlertDialog();

                    }



                }
            }
        });
    }
    public void closeKeyboard()
    {
        View view=this.getCurrentFocus();
        if(view!=null)
        {
            InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
    public void AlertDialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        final AlertDialog alertDialog=builder.create();
        builder.setTitle("Suggestions");
        builder.setMessage(hints);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CustomIntent.customType(this,"fadein-to-fadeout");
    }
}
