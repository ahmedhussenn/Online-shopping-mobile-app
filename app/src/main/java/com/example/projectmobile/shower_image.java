package com.example.projectmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class shower_image extends AppCompatActivity {
    db_helper objectDataBaseHandler;
    RecyclerView objectRecylerView;
    Adapter objevtrvadatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shower_image);

        try{
            objectRecylerView=findViewById(R.id.recycle_viewer);
            objectDataBaseHandler=new db_helper(this);
        }
        catch (Exception e){

        }
        Button show= (Button) findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                  //  Toast.makeText(this, "yala nget el data", Toast.LENGTH_LONG).show();
                    System.out.println("se7a");
                    objevtrvadatper = new Adapter(objectDataBaseHandler.getAllImageData());
                    System.out.println("awl haga");

                    System.out.println(objevtrvadatper.ojModelClassesList.get(0).ImageName);
                    // Toast.makeText(this,y,Toast.LENGTH_LONG).show();
                  //  Toast.makeText(this, "b3d yala nget el data", Toast.LENGTH_LONG).show();

                    objectRecylerView.setHasFixedSize(true);
                    objectRecylerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    objectRecylerView.setAdapter(objevtrvadatper);
                } catch (Exception e) {
                   // Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    }
