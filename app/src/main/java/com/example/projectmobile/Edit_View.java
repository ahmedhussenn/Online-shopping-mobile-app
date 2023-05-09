package com.example.projectmobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class Edit_View extends AppCompatActivity {
    EditText ImageDetailsEt;
    TextView Descriptiontxt;
    private ImageView objectimageView;
    private static  final int PICK_IMAGE_Requst=100;
    Uri ImageFilePath;
    private Bitmap Imagetostore;
    String Image_name;
    String Image_desc;
    String image_id;

    db_helper objectDataBaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view);

        ImageDetailsEt=findViewById(R.id.edit_name_text);
        objectimageView =findViewById(R.id.image_edit);
        Descriptiontxt=(TextView)findViewById(R.id.edit_Description_field);
        objectDataBaseHandler=new db_helper(this);
        Descriptiontxt.setInputType(InputType.TYPE_CLASS_NUMBER );
     /*   Intent intent = getIntent();
         Image_name = intent.getStringExtra("passed_name");
    Image_desc = intent.getStringExtra("passed_desc");
       // Bitmap Image_bitmap = intent.ge("passed_image");
        System.out.println(Image_name);
        System.out.println("sadsad");
        System.out.println(Image_desc);
        ImageDetailsEt.setText(Image_name);
        Descriptiontxt.setText(Image_desc);*/

        Image_name = getIntent().getStringExtra("passed_name");
        Image_desc = getIntent().getStringExtra("passed_desc");
        image_id=getIntent().getStringExtra("passed_id");
        byte[] imagebytes=getIntent().getByteArrayExtra("passed_image");
        Bitmap objecbitmap = BitmapFactory.decodeByteArray(imagebytes, 0, imagebytes.length);
    //    Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("passed_image");
        ImageDetailsEt.setText(Image_name);
        Descriptiontxt.setText(Image_desc);
        objectimageView.setImageBitmap(objecbitmap);

        Button update_lis=(Button) findViewById(R.id.update_btn);

        update_lis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] imageinbytes;
                Bitmap imageToStoreBitmap = objecbitmap;
                ByteArrayOutputStream objectByteArrayOutputStream = new ByteArrayOutputStream();
                imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);

                imageinbytes = objectByteArrayOutputStream.toByteArray();
                objectDataBaseHandler.update_image(image_id,Image_name,Image_desc,
                        ImageDetailsEt.getText().toString(),Descriptiontxt.getText().toString(),imageinbytes);
            }
        });
    }

    public void chooseImage(View objectView) {
        try {
         /* Intent objectIntent = new Intent();
            objectIntent.setType("image/*");
            objectIntent.setAction( Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent, PICK_IMAGE_Requst);*/
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
        System.out.println(Image_name);
        System.out.println("sadsad");
        System.out.println(Image_desc);
    }
    public void movetoshowactivty(View view){
        startActivity(new Intent(this,Shower_Image_prodcuts.class));
    }
    }
