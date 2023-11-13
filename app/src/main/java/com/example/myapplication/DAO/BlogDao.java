package com.example.myapplication.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Domains.BlogDomain;


import java.util.List;


@Dao
public interface BlogDao {
    @Insert
    public void addBlog (BlogDomain blogDomain );
    @Query("Select * from BlogDomain")
    List<BlogDomain> getAll();

    @Query("UPDATE blogdomain SET title = :ftitle,  description = :fdescription, " +
            "pic = :fpic  WHERE id = :id")
    void updateById(int id, String ftitle,  String fdescription,
                   String fpic);


    @Query("DELETE FROM blogdomain WHERE id=:id")
    void deleteById(int id);

}

