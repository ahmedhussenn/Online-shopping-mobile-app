package com.example.projectmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Shower_Image_prodcuts extends AppCompatActivity {
    db_helper objectDataBaseHandler;
    RecyclerView objectRecylerView;
    ListView listView;
    MyAdapter objevtrvadatper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shower_image_prodcuts);
        System.out.println("hello 1");
        try{
            objectRecylerView=findViewById(R.id.recview);
            objectDataBaseHandler=new db_helper(this);

            ArrayList<ModelClass> x=objectDataBaseHandler.getAllImageData();

            objevtrvadatper = new MyAdapter(x);

            ImageButton Delete=(ImageButton)findViewById(R.id.delete_btn);

            // Toast.makeText(this,y,Toast.LENGTH_LONG).show();
            //  Toast.makeText(this, "b3d yala nget el data", Toast.LENGTH_LONG).show();
            objectRecylerView.setAdapter(objevtrvadatper);
            objectRecylerView.setHasFixedSize(true);
            objectRecylerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            objevtrvadatper.setOnItemclicklistner(new MyAdapter.onItemClickListener() {
                @Override
                public void onItemClick(int postion,String type) {
                   System.out.println(x.get(postion).ImageName);
                   if(type=="delete") {
                       System.out.println("the deleted item id is "+x.get(postion).product_id);
                       objectDataBaseHandler.delete_image(x.get(postion).ImageName, x.get(postion).Description);
                       x.remove(postion);
                       objevtrvadatper.notifyItemRemoved(postion);
                       System.out.println("Delete");
                   }
                    if(type=="edit") {
                        System.out.println("Edit");
                        System.out.println("the Edit item id is "+x.get(postion).product_id);

                        Intent intent = new Intent(getApplicationContext(), Edit_View.class);
                       System.out.println(x.get(postion).Image);
                        intent.putExtra("passed_id",x.get(postion).product_id);

                        intent.putExtra("passed_name",x.get(postion).ImageName);
                       intent.putExtra("passed_desc",x.get(postion).Description);
                         byte[] imageinbytes;
                        Bitmap imageToStoreBitmap = x.get(postion).Image;
                         ByteArrayOutputStream  objectByteArrayOutputStream = new ByteArrayOutputStream();
                        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);
                        imageinbytes = objectByteArrayOutputStream.toByteArray();
                       intent.putExtra("passed_image", imageinbytes);
                       startActivity(intent);
                       // startActivity(new Intent(this,Edit_View.class));
                    }

                    if(type=="v") {
                        System.out.println("view");
                        //  showToast(postion+" clicked");
                        Intent intent = new Intent(getApplicationContext(), productDetails.class);
                        System.out.println(x.get(postion).getImage());
                        intent.putExtra("passed_name",x.get(postion).getImageName());
                        intent.putExtra("passed_desc",x.get(postion).getDescription());
                        intent.putExtra("passed_price",x.get(postion).getPrice());
                        //start
                        byte[] imageinbytes;
                        Bitmap imageToStoreBitmap = x.get(postion).getImage();
                        ByteArrayOutputStream  objectByteArrayOutputStream = new ByteArrayOutputStream();
                        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);
                        imageinbytes = objectByteArrayOutputStream.toByteArray();
                        intent.putExtra("passed_image", imageinbytes);
                        //end
                        startActivity(intent);

                    }

                }
            });
        }
        catch (Exception e){

        }


    }
}