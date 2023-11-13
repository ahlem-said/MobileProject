package com.example.myapplication.activities;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.example.myapplication.DAO.BlogDao;
import com.example.myapplication.Domains.BlogDomain;

import com.example.myapplication.R;
import com.example.myapplication.database.Appdatabase;




public class AddBlogActivity extends AppCompatActivity {
    EditText titleview, newPostDescription, newPostImage;
    Button newPostBtn, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_post);

        titleview = findViewById(R.id.titleview);
        newPostDescription = findViewById(R.id.newPostDescription);
        newPostImage = findViewById(R.id.newPostImage);
        newPostBtn = findViewById(R.id.newPostBtn);
        b2 = findViewById(R.id.b2); // Replace with the actual ID

        newPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the bgthread for database operation
                new bgthread().start();
            }
        });

        // Set OnClickListener for b2 outside of bgthread
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    class bgthread extends Thread {
        public void run() {
            // Perform database operation in the background
            Appdatabase db = Room.databaseBuilder(getApplicationContext(),
                    Appdatabase.class, "blog_db").build();
            System.out.println("dddddddddddddd");
            BlogDao blogDao = db.blogDao();
            blogDao.addBlog(new BlogDomain(
                    titleview.getText().toString(),
                    newPostDescription.getText().toString(),
                    newPostImage.getText().toString()
            ));

            // RunOnUiThread to update UI after database operation
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // Your existing code...
                    titleview.setText("");
                    newPostDescription.setText("");
                    newPostImage.setText("");
                    System.out.println("dddddddddddddd");
                    Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();

                    // Start the listblog1 activity
                    Intent intent = new Intent(AddBlogActivity.this, listblog1.class);
                    startActivity(intent);

                    // Finish the current activity
                    finish();
                }
            });
        }
    }
}