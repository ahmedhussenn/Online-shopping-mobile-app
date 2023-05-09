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
            sqLiteDatabase.execSQL("create table Categories (Categoryid integer primary key autoincrement,"+ " categoryname text not null)");
            sqLiteDatabase.execSQL("create table Products (id integer primary key autoincrement,"+ " name text not null ,"+"quantity integer not null, Image BLOB,"+ " price integer not null,"+" Category_id integer References Categories (Categoryid) )"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("drop table if exists Products");
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
            objContentValues.put("price", "5");
            objContentValues.put("Category_id", "1");
            ContentValues ayhaga = new ContentValues();

            Cursor ObjectCursor = objectSqliteDatabase.rawQuery("select * from Categories", null);

            System.out.println("sad"+ObjectCursor.getCount());
            if(ObjectCursor.getCount()==0) {
                ayhaga.put("categoryname", "anything");
                long q = objectSqliteDatabase.insert("Categories", null, ayhaga);
            }

            long d = objectSqliteDatabase.insert("Products", null, objContentValues);
            if (d != 0) {

                objectSqliteDatabase.close();
            }


        } catch (Exception e) {

        }
    }

    public void delete_image(String namee,String Descriptionn){
        objectSqliteDatabase = this.getWritableDatabase();
        objectSqliteDatabase.delete("Products","name=? and quantity=?",new String[]{namee,Descriptionn});
        objectSqliteDatabase.close();
    }

    public void update_image(String id,String old_name,String old_description,String namee,String Descriptionn, byte[] imageinbytes){
        objectSqliteDatabase = this.getWritableDatabase();

        System.out.println(namee);
        System.out.println(Descriptionn);

        ContentValues cv = new ContentValues();

        cv.put("name",namee);
        cv.put("quantity",Descriptionn );
        cv.put("Image", imageinbytes);
        objectSqliteDatabase.update("Products", cv, "name=? and quantity=?", new String[]{old_name,old_description});
        objectSqliteDatabase.close();
    }

    public ArrayList<ModelClass> getAllImageData() {
       // Toast.makeText(context, "se7", Toast.LENGTH_LONG).show();
        try {
          //  Toast.makeText(context, "get all image", Toast.LENGTH_LONG).show();

            SQLiteDatabase objectSqlLiteDatabase = this.getReadableDatabase();
            ArrayList<ModelClass> objectmodelclassList = new ArrayList<>();
            Cursor ObjectCursor = objectSqlLiteDatabase.rawQuery("select * from Products", null);
                  System.out.println("test 2");

            if (ObjectCursor.getCount() != 0) {
             //   Toast.makeText(context, "aiwa el image", Toast.LENGTH_LONG).show();
                System.out.println("tamam");

                while (ObjectCursor.moveToNext()) {

                    System.out.println(ObjectCursor.getString(1));
                    String prod_id=ObjectCursor.getString(0);
                    String nameOfImage = ObjectCursor.getString(1);
                    String description_of_image = ObjectCursor.getString(2);
                    byte[] imagebytes = ObjectCursor.getBlob(3);
                    Bitmap objecbitmap = BitmapFactory.decodeByteArray(imagebytes, 0, imagebytes.length);

                    String quantity=ObjectCursor.getString(4);
                    String category_id=ObjectCursor.getString(5);

                    int tester=Integer.parseInt(description_of_image);


                    objectmodelclassList.add(new ModelClass(prod_id,nameOfImage, description_of_image,objecbitmap,quantity,category_id));
                }
                return objectmodelclassList;
            } else {
                System.out.println("error");

                //  Toast.makeText(context, "not exists", Toast.LENGTH_LONG).show();
                return null;

            }
        } catch (Exception e) {
           // Toast.makeText(context, "failed to fetch", Toast.LENGTH_LONG).show();
            return null;
        }
    }
    }


