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
                "Birthdate text not null," + "Job text not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists movie");
        onCreate(sqLiteDatabase);
    }

    public String store_customer_info(String customername, String Username, String Passwordd, String gender, String Birthdatee, String job) {
        objectSqliteDatabase = getWritableDatabase();
        ContentValues objContentValues = new ContentValues();
        objContentValues.put("CustName", customername);
        objContentValues.put("Username", Username);
        objContentValues.put("Password", Passwordd);
        objContentValues.put("Gender", gender);
        objContentValues.put("Birthdate", Birthdatee);
        objContentValues.put("Job", job);
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

    public String login_checker( String Username, String Passwordd) {

        objectSqliteDatabase = getReadableDatabase();
        Cursor ObjectCursor = objectSqliteDatabase.rawQuery("select * from Customers", null);
        if (ObjectCursor.getCount() != 0) {

            while (ObjectCursor.moveToNext()) {

                String email_of_customer = ObjectCursor.getString(2);
                String password = ObjectCursor.getString(3);

                if (Objects.equals(email_of_customer, Username)&&Objects.equals(password, Passwordd))
                {
                    System.out.println("Sucessefully");
                    objectSqliteDatabase.close();
                    return "true";
                }
                else {

                }
            }
            return "false";
        }
        else {
            objectSqliteDatabase.close();
            return "false";
        }
    }

}
