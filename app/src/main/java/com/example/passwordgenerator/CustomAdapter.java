package com.example.passwordgenerator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    ArrayList<String> title;
    ArrayList<String> data,Id;
    Context context;

    CustomAdapter(Context context,ArrayList<String> title, ArrayList<String> data,ArrayList<String> Id)
    {
        this.title=title;
        this.data=data;
        this.Id=Id;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cardview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.title.setText(title.get(position));
        holder.password.setText(data.get(position));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper db=new MyDatabaseHelper(context);
                db.deleteOneRow(Id.get(position));
                Intent intent=new Intent(context,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder  extends RecyclerView.ViewHolder
    {
        TextView title,password;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.cardview_title);
            password=itemView.findViewById(R.id.cardview_data);
            delete=itemView.findViewById(R.id.delete);
        }
    }

}
