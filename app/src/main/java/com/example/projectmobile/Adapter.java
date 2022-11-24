package com.example.projectmobile;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.PrintStream;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter <Adapter.RVViewFolderClass>{

    ArrayList<ModelClass> ojModelClassesList;

    public Adapter(ArrayList<ModelClass>ojModelClassesList) {
        this.ojModelClassesList = ojModelClassesList;
        System.out.println("se7a");

    }


    @NonNull
    @Override
    public RVViewFolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RVViewFolderClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewFolderClass holder, int position) {
ModelClass objectModelClass=ojModelClassesList.get(position);
holder.imageNameTv.setText(objectModelClass.getImageName());
holder.objectImageView.setImageBitmap(objectModelClass.getImage());
    }

    @Override
    public int getItemCount() {
        return ojModelClassesList.size();
    }

    public static class RVViewFolderClass extends RecyclerView.ViewHolder
    {
         TextView imageNameTv;
        ImageView objectImageView;

        public RVViewFolderClass(@NonNull View itemView) {
            super(itemView);
            imageNameTv=itemView.findViewById(R.id.sr_image_detalis);
            objectImageView=itemView.findViewById(R.id.sr_imageIV);
        }
    }


}
