package com.example.projectmobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ImageDetailsEt;
    TextView Descriptiontxt;
    private ImageView objectimageView;
    private static  final int PICK_IMAGE_Requst=100;
    Uri ImageFilePath;
    private Bitmap Imagetostore;
    Customer_db_helper cus;

    db_helper objectDataBaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String username = getIntent().getStringExtra("passed_username");

        Toast.makeText(this, "hello "+username, Toast.LENGTH_LONG).show();
        System.out.println("welecome "+username);
        ImageDetailsEt=findViewById(R.id.edit_name_text);
         objectimageView =findViewById(R.id.image_edit);
         Descriptiontxt=(TextView)findViewById(R.id.edit_Description_field);
        Descriptiontxt.setInputType(InputType.TYPE_CLASS_NUMBER );

         objectDataBaseHandler=new db_helper(this);
        cus=new Customer_db_helper(this);
        Button logout=(Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cus.remember_me_delete();
                startActivity(new Intent(getApplicationContext(),LodinActivty.class));
            }
        });


    }
    public void chooseImage(View objectView) {
        try {
            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");
            objectIntent.setAction( Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent, PICK_IMAGE_Requst);
        } catch (Exception e) {

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try{
            super.onActivityResult(requestCode, resultCode, data);
           if(requestCode==PICK_IMAGE_Requst&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null)
            {
                ImageFilePath=data.getData();
                Imagetostore= MediaStore.Images.Media.getBitmap(getContentResolver(),ImageFilePath);
                objectimageView.setImageBitmap(Imagetostore);
            }
        }
        catch (Exception e){

        }

    }

    public void storeImage(View view){
        Toast.makeText(this,"se7a",Toast.LENGTH_LONG).show();

        try{
            Toast.makeText(this,"sucess",Toast.LENGTH_LONG).show();

                objectDataBaseHandler.storeImage(new ModelClass("",ImageDetailsEt.getText().toString(),Descriptiontxt.getText().toString(),Imagetostore,"",""));
System.out.println("image stored");


        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();

        }
    }
public void movetoshowactivty(View view){
        startActivity(new Intent(this,Shower_Image_prodcuts.class));
}

}