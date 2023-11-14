package com.example.tripsmodule.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tripsmodule.Domains.TripsDomain;
import com.example.tripsmodule.R;
import com.example.tripsmodule.dao.TripsDao;
import com.example.tripsmodule.database.AppDatabase;

public class FormActivity extends AppCompatActivity {
    EditText t1,t2,t3,t4,t5,t6,t7,t8,t9;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        t5=findViewById(R.id.t5);
        t6=findViewById(R.id.t6);
        t7=findViewById(R.id.t7);
        t8=findViewById(R.id.t8);
        t9=findViewById(R.id.t9);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new bgthread().start();

            }
        });
    }
    class bgthread extends Thread {
        public void run() {
            super.run();
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "room_db").allowMainThreadQueries().build();
            TripsDao tripsDao = db.tripsDao();
            tripsDao.insertrecord(new TripsDomain(
                    t1.getText().toString(),
                    t2.getText().toString(),
                    t3.getText().toString(),
                    Integer.parseInt(t4.getText().toString()),
                    Boolean.parseBoolean(t5.getText().toString()),
                    Double.parseDouble(t6.getText().toString()),
                    t7.getText().toString(),
                    Boolean.parseBoolean(t8.getText().toString()),
                    Integer.parseInt(t9.getText().toString())
            ));

            // Mises à jour de l'interface utilisateur doivent être effectuées sur le thread principal
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                    t6.setText("");
                    t7.setText("");
                    t8.setText("");
                    t9.setText("");
                    Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                }
            });

            b2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    startActivity(new Intent(getApplicationContext(), fetchdata.class));
                }
            });
        }
    }}
