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
import java.util.Objects;

public class Customer_db_helper extends SQLiteOpenHelper {
    private static String databasename = "customer database";
    SQLiteDatabase objectSqliteDatabase;

    public Customer_db_helper(Context context) {
        super(context, databasename, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Customers (Cust_ID integer primary key autoincrement," + " " +
                "CustName text not null ," + "Username text not null," + "Password text not null," + "Gender text not null," +
                "Birthdate text not null," + "Job text not null,"+ "recovery_code text not null)");

        sqLiteDatabase.execSQL("create table remember_me (Cust_ID integer primary key autoincrement," + " " +
                 "Username text not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists movie");
        onCreate(sqLiteDatabase);
    }

    public String store_customer_info(String customername, String Username, String Passwordd, String gender, String Birthdatee, String job,String recovery_code) {
        objectSqliteDatabase = getWritableDatabase();
        ContentValues objContentValues = new ContentValues();
        objContentValues.put("CustName", customername);
        objContentValues.put("Username", Username);
        objContentValues.put("Password", Passwordd);
        objContentValues.put("Gender", gender);
        objContentValues.put("Birthdate", Birthdatee);
        objContentValues.put("Job", job);
        objContentValues.put("recovery_code", recovery_code);

        Cursor ObjectCursor = objectSqliteDatabase.rawQuery("select * from Customers", null);

        if (ObjectCursor.getCount() != 0) {

            while (ObjectCursor.moveToNext()) {
                String email_of_customer = ObjectCursor.getString(2);

                if (Objects.equals(email_of_customer, Username)) {

                    objectSqliteDatabase.close();
                    return email_of_customer;
                }
            }
        }

        long q = objectSqliteDatabase.insert("Customers", null, objContentValues);
        objectSqliteDatabase.close();

        return "Succseful";
    }

    public String login_checker(String Username, String Passwordd) {

        objectSqliteDatabase = getReadableDatabase();//data base opened
        Cursor ObjectCursor = objectSqliteDatabase.rawQuery("select * from Customers", null);
        if (ObjectCursor.getCount() != 0) {

            while (ObjectCursor.moveToNext()) {

                String email_of_customer = ObjectCursor.getString(2);
                String password = ObjectCursor.getString(3);


                if (Objects.equals(email_of_customer, Username) && Objects.equals(password, Passwordd)) {
                    System.out.println("Sucessefully");
                    System.out.println(ObjectCursor.getString(0));
                    objectSqliteDatabase.close();
                    return ObjectCursor.getString(0);
                } else {
                    System.out.println(ObjectCursor.getString(0));
                }
            }
            return "false";
        } else {
            objectSqliteDatabase.close();
            return "false";
        }
    }


    public String remember_me_checker() {
        System.out.println("hello saed");
        objectSqliteDatabase = getReadableDatabase();
        try {
            Cursor cr = objectSqliteDatabase.rawQuery("select * from remember_me", null);
            System.out.println(cr.getCount());
            if (cr.getCount() != 0) {
                while (cr.moveToNext()) {
                    System.out.println("checked");
                    System.out.println(cr.getString(0));
                    return cr.getString(1);
                }
            }
            return "f";
        }
    catch (Exception e){
        return "f";

    }

    }
    public void remember_me_update(String cust_user_name) {
        objectSqliteDatabase = getReadableDatabase();
        ContentValues objContentValues = new ContentValues();
        objContentValues.put("Username", cust_user_name);
        long q = objectSqliteDatabase.insert("remember_me", null, objContentValues);
        objectSqliteDatabase.close();
    }

    public void remember_me_delete() {
        objectSqliteDatabase = getReadableDatabase();
        objectSqliteDatabase.delete("remember_me",null,null);
        objectSqliteDatabase.close();
    }

    public String revover_password_checker(String Username, String code) {

        objectSqliteDatabase = getReadableDatabase();
        Cursor ObjectCursor = objectSqliteDatabase.rawQuery("select * from Customers", null);
        if (ObjectCursor.getCount() != 0) {

            while (ObjectCursor.moveToNext()) {

                String email_of_customer = ObjectCursor.getString(2);
                String recovery_code = ObjectCursor.getString(7);


                if (Objects.equals(email_of_customer, Username) && Objects.equals(recovery_code, code)) {
                    System.out.println("Sucessefully");
                    System.out.println(ObjectCursor.getString(0));
                    objectSqliteDatabase.close();
                    return ObjectCursor.getString(1);
                } else {
                    System.out.println(ObjectCursor.getString(0));
                }
            }
            return "false";
        } else {
            objectSqliteDatabase.close();
            return "false";
        }
    }

    public void update_password(String Username,String new_password){
        System.out.println("Hello");
        objectSqliteDatabase = this.getWritableDatabase();
        Cursor ObjectCursor = objectSqliteDatabase.rawQuery("select * from Customers", null);
        if (ObjectCursor.getCount() != 0) {
            while (ObjectCursor.moveToNext()) {
                if (Objects.equals(ObjectCursor.getString(2), Username)) {
                    System.out.println("founded is" + Username);
                    ContentValues objContentValues = new ContentValues();
                    objContentValues.put("CustName", ObjectCursor.getString(1));
                    objContentValues.put("Username", ObjectCursor.getString(2));
                    objContentValues.put("Password", new_password);
                    objContentValues.put("Gender", ObjectCursor.getString(4));
                    objContentValues.put("Birthdate", ObjectCursor.getString(5));
                    objContentValues.put("Job",ObjectCursor.getString(6));
                    objContentValues.put("recovery_code", ObjectCursor.getString(7));
                    objectSqliteDatabase.update("Customers", objContentValues, "Username=? ", new String[]{Username});
                    objectSqliteDatabase.close();
                    break;
                }
            }

        }

    }


}
