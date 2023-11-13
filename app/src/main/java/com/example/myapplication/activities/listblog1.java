package com.example.myapplication.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.myapplication.Adapters.BlogAdapters;
import com.example.myapplication.Adapters.myadapter;
import com.example.myapplication.DAO.BlogDao;
import com.example.myapplication.Domains.BlogDomain;
import com.example.myapplication.R;
import com.example.myapplication.database.Appdatabase;

import java.util.ArrayList;
import java.util.List;

public class listblog1 extends AppCompatActivity {

    private RecyclerView recview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_blog);
        getroomdata();
    }

    private void getroomdata() {
        Appdatabase db = Room.databaseBuilder(getApplicationContext(),
                Appdatabase.class, "blog_db").allowMainThreadQueries().build();
        BlogDao blogDao = db.blogDao();
        recview=findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this ));
        List<BlogDomain> blogDomains=blogDao.getAll();
        myadapter adapter=new myadapter(blogDomains);
        recview.setAdapter(adapter);

    }
}