package com.example.projectmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class db_helper extends SQLiteOpenHelper {
        private static String databasename="project database";
        SQLiteDatabase movieDataBase;
    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageinbytes;
    SQLiteDatabase objectSqliteDatabase;

        public db_helper(Context context){
            super(context,databasename,null,1);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table Employees (id integer primary key autoincrement,"+ " name text not null ,"+"quantity integer not null, Image BLOB)");

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("drop table if exists movie");
            onCreate(sqLiteDatabase);
        }
    public void storeImage(ModelClass objectModelClass) {
        try {
             objectSqliteDatabase = getWritableDatabase();
            Bitmap imageToStoreBitmap = objectModelClass.getImage();


            objectByteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);

            imageinbytes = objectByteArrayOutputStream.toByteArray();
            ContentValues objContentValues = new ContentValues();
            System.out.println(imageinbytes);
            objContentValues.put("name",objectModelClass.getImageName() );
            objContentValues.put("quantity",Integer.parseInt(objectModelClass.getDescription()));
            objContentValues.put("Image", imageinbytes);
            long q = objectSqliteDatabase.insert("Employees", null, objContentValues);
            if (q != 0) {

                objectSqliteDatabase.close();
            }


        } catch (Exception e) {

        }
    }

    public void delete_image(String namee,String Descriptionn){
        objectSqliteDatabase = this.getWritableDatabase();
        objectSqliteDatabase.delete("Employees","name=? and quantity=?",new String[]{namee,Descriptionn});
        objectSqliteDatabase.close();
    }

    public void update_image(String old_name,String old_description,String namee,String Descriptionn, byte[] imageinbytes){
        objectSqliteDatabase = this.getWritableDatabase();

        System.out.println(namee);
        System.out.println(Descriptionn);

        ContentValues cv = new ContentValues();

        cv.put("name",namee);
        cv.put("quantity",Descriptionn );
        cv.put("Image", imageinbytes);
        objectSqliteDatabase.update("Employees", cv, "name=? and quantity=?", new String[]{old_name,old_description});
        objectSqliteDatabase.close();
    }

    public ArrayList<ModelClass> getAllImageData() {
       // Toast.makeText(context, "se7", Toast.LENGTH_LONG).show();
        System.out.println("se7a");
        try {
          //  Toast.makeText(context, "get all image", Toast.LENGTH_LONG).show();

            SQLiteDatabase objectSqlLiteDatabase = this.getReadableDatabase();
            ArrayList<ModelClass> objectmodelclassList = new ArrayList<>();
            Cursor ObjectCursor = objectSqlLiteDatabase.rawQuery("select * from Employees", null);
            if (ObjectCursor.getCount() != 0) {
             //   Toast.makeText(context, "aiwa el image", Toast.LENGTH_LONG).show();

                while (ObjectCursor.moveToNext()) {
                    String nameOfImage = ObjectCursor.getString(1);
                    String description_of_image = ObjectCursor.getString(2);
                    byte[] imagebytes = ObjectCursor.getBlob(3);
                    Bitmap objecbitmap = BitmapFactory.decodeByteArray(imagebytes, 0, imagebytes.length);

                    int tester=Integer.parseInt(description_of_image);


                    objectmodelclassList.add(new ModelClass(nameOfImage, description_of_image,objecbitmap));
                }
                return objectmodelclassList;
            } else {
              //  Toast.makeText(context, "not exists", Toast.LENGTH_LONG).show();
                return null;

            }
        } catch (Exception e) {
           // Toast.makeText(context, "failed to fetch", Toast.LENGTH_LONG).show();
            return null;
        }
    }
    }


