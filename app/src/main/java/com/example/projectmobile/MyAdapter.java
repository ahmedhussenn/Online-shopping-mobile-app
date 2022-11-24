package com.example.projectmobile;

import android.database.sqlite.SQLiteDatabase;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter <MyAdapter.RVViewFolderClass>{

    ArrayList<ModelClass> ojModelClassesList;
    private onItemClickListener mitemclicklistner;
    private List<Call.Details>detailsList;

    public MyAdapter(ArrayList<ModelClass>ojModelClassesList) {
       // this.mitemclicklistner=mitemclicklistner;
        this.ojModelClassesList = ojModelClassesList;
        System.out.println("se7a");

    }


    @NonNull
    @Override
    public MyAdapter.RVViewFolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyAdapter.RVViewFolderClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_row,parent,false),mitemclicklistner);
    }

public interface onItemClickListener{
       void onItemClick(int postion,String type);
}
public void setOnItemclicklistner(onItemClickListener click){
    mitemclicklistner=click;
}

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.RVViewFolderClass holder, int position) {
        ModelClass objectModelClass=ojModelClassesList.get(position);
        holder.myname.setText(objectModelClass.getImageName());
        holder.mydescription.setText(objectModelClass.getDescription());
        holder.objectImageView.setImageBitmap(objectModelClass.getImage());

    }

    @Override
    public int getItemCount() {
        return ojModelClassesList.size();
    }

    public static class RVViewFolderClass extends RecyclerView.ViewHolder
    {
        TextView myname,mydescription;
        ImageView objectImageView;
        ImageButton dlt_btn;
        ImageButton edit_btn;
        public RVViewFolderClass(@NonNull View itemView, onItemClickListener listner) {
            super(itemView);
            myname=itemView.findViewById(R.id.name_in_card);
            mydescription=itemView.findViewById(R.id.Description_in_card);
            objectImageView=itemView.findViewById(R.id.Image_in_card);
            dlt_btn= itemView.findViewById(R.id.delete_btn);
            edit_btn= itemView.findViewById(R.id.Edit_btn);
            dlt_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listner.onItemClick(getAdapterPosition(),"delete");
                }
            });
            edit_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listner.onItemClick(getAdapterPosition(),"edit");
                }
            });

        }
    }
}
