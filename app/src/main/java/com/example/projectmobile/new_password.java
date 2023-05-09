package com.example.projectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class new_password extends AppCompatActivity {
    Customer_db_helper customer_db_helper;
String pass,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        customer_db_helper=new Customer_db_helper(this);
        TextView pass1 = (TextView) findViewById(R.id.new_pass);
        TextView pass2 = (TextView) findViewById(R.id.new_pass_reconfirm);
        Button btn=(Button)  findViewById(R.id.change_password);

        username = getIntent().getStringExtra("passed_username");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pass1.getText().toString().equals(pass2.getText().toString())&&pass1.getText().toString().length()!=0) {
                    customer_db_helper.update_password(username, pass2.getText().toString());
                    startActivity(new Intent(getApplicationContext(),LodinActivty.class));
                }

                else {

                    Toast.makeText(getApplicationContext(),"Passwords are not correct",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}