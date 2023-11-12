package com.example.myapplication.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.example.myapplication.DAO.BlogDao;
import com.example.myapplication.Domains.BlogDomain;

import com.example.myapplication.R;
import com.example.myapplication.database.Appdatabase;



public class AddBlogActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PERMISSION_REQUEST_CODE = 2;

    EditText titleview, newPostDescription;
    ImageView newPostImage;
    Button newPostBtn, chooseImageButton;
    private String imagePath;  // Chemin de l'image sélectionnée

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_post);

        titleview = findViewById(R.id.titleview);
        newPostDescription = findViewById(R.id.newPostDescription);
        newPostImage = findViewById(R.id.newPostImage);
        newPostBtn = findViewById(R.id.newPostBtn);
        chooseImageButton = findViewById(R.id.chooseImageButton);

        // Vérifier et demander la permission à l'exécution si nécessaire
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }

        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });

        newPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new bgthread().start();
            }
        });
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            imagePath = getRealPathFromURI(uri);
            newPostImage.setImageURI(uri);
        }
    }

    private String getRealPathFromURI(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) return null;

        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    class bgthread extends Thread {
        public void run() {
            super.run();

            Appdatabase db = Room.databaseBuilder(getApplicationContext(),
                    Appdatabase.class, "blog_db").build();
            BlogDao blogDao = db.blogDao();
            blogDao.addBlog(new BlogDomain(
                    titleview.getText().toString(),
                    newPostDescription.getText().toString(),
                    imagePath
            ));

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    titleview.setText("");
                    newPostDescription.setText("");
                    newPostImage.setImageResource(R.drawable.post_placeholder);
                    Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}