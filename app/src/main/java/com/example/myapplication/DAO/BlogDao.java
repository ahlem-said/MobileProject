package com.example.myapplication.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.Domains.BlogDomain;

import java.util.ArrayList;
import java.util.List;


@Dao
public interface BlogDao {
    @Insert
    public void addBlog (BlogDomain blogDomain );
    @Query("Select * from BlogDomain")
    List<BlogDomain> getAll();
}
