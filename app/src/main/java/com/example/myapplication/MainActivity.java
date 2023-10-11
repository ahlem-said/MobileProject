package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.Adapters.BlogAdapters;
import com.example.myapplication.Domains.BlogDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterBlog;
    private RecyclerView recyclerViewBlogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blog_list_item);
        initRecyclerView();
    }

    private void initRecyclerView() {
        ArrayList<BlogDomain> items = new ArrayList<>();
        items.add(new BlogDomain("Mar caible,avendia lago",
                " It is well-known that setting up and managing finances a "
                ,"blogimage"

               ));
        items.add(new BlogDomain("Mar caible,avendia lago"," It is well-known that setting up and ma "
                ,"blogimage"
        ));
        items.add(new BlogDomain("Mar caible,avendia lago"," It is well-known that setting up and managi"
              ,"blogimage"
        ));
        recyclerViewBlogs = findViewById(R.id.view_blog);
        recyclerViewBlogs.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapterBlog=new BlogAdapters(items);
        recyclerViewBlogs.setAdapter(adapterBlog);





    }
}