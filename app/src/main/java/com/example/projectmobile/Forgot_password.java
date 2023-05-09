package com.example.projectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Forgot_password extends AppCompatActivity {
    Customer_db_helper customer_db_helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        customer_db_helper=new Customer_db_helper(this);

        TextView usrname = (TextView) findViewById(R.id.user_name_recovery);
        TextView recovery_code = (TextView) findViewById(R.id.code_recovery);
        Button btn=(Button)  findViewById(R.id.Recover_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valid_change_password= customer_db_helper.revover_password_checker(usrname.getText().toString(),recovery_code.getText().toString());
                if(valid_change_password!="false") {

                    Intent intent = new Intent(getApplicationContext(), new_password.class);
                    intent.putExtra("passed_username",usrname.getText().toString());
                    startActivity(intent);

                }
                else
                {
                    System.out.println("false");
                }
            }
        });

    }
}