package com.example.projectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LodinActivty extends AppCompatActivity {
    String password,Customerusername;
    Customer_db_helper customer_db_helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lodin_activty);
        customer_db_helper=new Customer_db_helper(this);
        TextView  Customer_username_field,Customer_password_field;
        Customer_username_field=(TextView)findViewById(R.id.logininputUsername);
        Customer_password_field=(TextView)findViewById(R.id.logininputPassowrd);


        Button login=(Button) findViewById(R.id.login_btn_activity);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customerusername = Customer_username_field.getText().toString();
                password = Customer_password_field.getText().toString();


                System.out.println(Customerusername+"  "+ password);
                if(Customerusername.length()==0||password.length()==0)
                    Toast.makeText(getApplicationContext(),"not all fields are filled", Toast.LENGTH_LONG).show();
                else {
                    String return_to_login = customer_db_helper.login_checker(Customerusername, password);
                    if (return_to_login == "true")
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    else {
                        Toast.makeText(getApplicationContext(), "invalid email or password", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        TextView go_to_signup=(TextView)findViewById(R.id.go_to_sign_up);
        go_to_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

    }
}