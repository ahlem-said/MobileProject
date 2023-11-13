package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myapplication.DAO.BlogDao;
import com.example.myapplication.R;
import com.example.myapplication.database.Appdatabase;

public class updatedata extends AppCompatActivity {
    int id;
    EditText ftitle,fdescription, fpic;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedata);

        ftitle=findViewById(R.id.edittitle);

        fdescription=findViewById(R.id.editdescription);

        fpic=findViewById(R.id.editpic);

        btn=findViewById(R.id.btn);




        ftitle.setText(getIntent().getStringExtra("ufname"));

        fdescription.setText(getIntent().getStringExtra("udescription"));
        fpic.setText(getIntent().getStringExtra("upic"));



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Appdatabase db = Room.databaseBuilder(getApplicationContext(),
                        Appdatabase.class, "blog_db").allowMainThreadQueries().build();
                BlogDao blogDao = db.blogDao();
                blogDao.updateById(id, ftitle.getText().toString(),
                        fdescription.getText().toString()
                       , fpic.getText().toString()
                       );


                startActivity(new Intent(getApplicationContext(), listblog1.class));
                finish();
            }

        });
    }




}