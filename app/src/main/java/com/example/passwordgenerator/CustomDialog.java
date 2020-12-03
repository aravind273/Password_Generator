package com.example.passwordgenerator;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;

public class CustomDialog {
    Activity activity;
    AlertDialog alertDialog;
    CustomDialog(Activity activity)
    {
        this.activity=activity;
    }
    public void CreateCustomDialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        View view=activity.getLayoutInflater().inflate(R.layout.custom_dialog,null);
        builder.setView(view);
        builder.setCancelable(false);
        alertDialog=builder.create();
        alertDialog.show();
    }
    public void CustomDialogDismiss()
    {
        alertDialog.dismiss();
    }

}
