package com.example.projectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class LodinActivty extends AppCompatActivity {
    String password,Customerusername;
    Customer_db_helper customer_db_helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lodin_activty);
        customer_db_helper=new Customer_db_helper(this);

       String t=customer_db_helper.remember_me_checker();
        System.out.println("t is "+ t);
        if(t!="f")
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("passed_username",t);
            startActivity(intent);
        }

        TextView  Customer_username_field,Customer_password_field;
        Customer_username_field=(TextView)findViewById(R.id.logininputUsername);
        Customer_password_field=(TextView)findViewById(R.id.logininputPassowrd);
        TextView forget=(TextView) findViewById(R.id.forgt_pass);
        CheckBox remember =(CheckBox)findViewById(R.id.remember_me_checkbox);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Forgot_password.class));
            }
        });


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

                    if (return_to_login !="false" ) {
                        if(remember.isChecked()==true)
                        customer_db_helper.remember_me_update(Customerusername);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("passed_username",Customerusername);
                        startActivity(intent);

                    }
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