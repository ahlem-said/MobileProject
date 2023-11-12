package com.example.myapplication.database;

import android.content.Context;


import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.myapplication.DAO.BlogDao;
import com.example.myapplication.Domains.BlogDomain;

@Database(entities = {BlogDomain.class}, version = 1)
public abstract class Appdatabase extends RoomDatabase {
    public abstract BlogDao blogDao();
}
