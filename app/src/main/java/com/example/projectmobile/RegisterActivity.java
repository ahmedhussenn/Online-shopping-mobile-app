package com.example.projectmobile;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.DatePicker;

import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    Customer_db_helper customer_db_helper;
    String gender="e";
    String Customername,Customerusername,password,birthdatestring,job,password_code;
   DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         DatePickerDialog.OnDateSetListener mDateSetListener;
         System.out.println("we are before db _helper customer");
        customer_db_helper=new Customer_db_helper(this);

        TextView Customer_name_field, pass_recovery,Customer_username_field,Customer_password_field,Customer_job_field,Customerbirthdate;
        Customer_name_field=(TextView)findViewById(R.id.Customername);
        Customer_username_field=(TextView)findViewById(R.id.inputUsername);
        pass_recovery=(TextView)findViewById(R.id.password_recovery_code);
        pass_recovery.setInputType(InputType.TYPE_CLASS_NUMBER );

        Customer_password_field=(TextView)findViewById(R.id.inputPassowrd);

        Customer_job_field=(TextView)findViewById(R.id.input_job);
        Customerbirthdate=(TextView) findViewById(R.id.Birthdate);




        ImageButton calendear=(ImageButton)findViewById(R.id.calendear_icon);
        TextView date=(TextView)findViewById(R.id.Birthdate);
        Calendar calendar = Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
         int day=calendar.get(Calendar.DAY_OF_MONTH);
         TextView to_login=(TextView)findViewById(R.id.already_have_account);

         to_login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(getApplicationContext(),LodinActivty.class));
             }
         });


        calendear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customerbirthdate.setText("");
                System.out.println("in birthdate");
               DatePickerDialog datePickerDialog=new DatePickerDialog(
                       RegisterActivity.this,setListener,year, month,day
               );
               datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
               datePickerDialog.show();
            }
        });
      setListener=new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
             int month=i1+1;
              String date=i2+"/"+month+"/"+i;
              Customerbirthdate.setText(date);

          }
      };
        RadioGroup genderRadioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);
        final String[] gender = {""};
        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.maleRadioButton: {
                        gender[0] ="m";
                        break;
                    }
                    case R.id.femaleRadioButton: {
                        gender[0] ="f";
                        break;
                    }
                }
            }
        });

        Button reg=(Button) findViewById(R.id.login_btn_activity);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customername = Customer_name_field.getText().toString();
                Customerusername = Customer_username_field.getText().toString();
                password = Customer_password_field.getText().toString();
                job = Customer_job_field.getText().toString();
                String recover_code=pass_recovery.getText().toString();

                if (Customername.length() == 0 || Customername.length() == 0||password.length()==0) {
                    Toast.makeText(getApplicationContext(), "not all fields is filled", Toast.LENGTH_LONG).show();
                }
                else if (job.length() == 0 ||  gender[0].length() == 0||Customerbirthdate.getText().toString().length()==0) {
                    Toast.makeText(getApplicationContext(), "not all fields is filled", Toast.LENGTH_LONG).show();
                }
                else {
                    String return_to_login = customer_db_helper.store_customer_info(Customername, Customerusername, password, gender[0], Customerbirthdate.getText().toString(), job,recover_code);
                    if (return_to_login == "Succseful") {
                        startActivity(new Intent(getApplicationContext(), LodinActivty.class));
                    } else {
                        Toast.makeText(getApplicationContext(), return_to_login + "is invalid", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });




    }
}