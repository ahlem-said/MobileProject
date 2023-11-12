package com.example.tripsmodule.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tripsmodule.Domains.TripsDomain;
import com.example.tripsmodule.dao.TripsDao;

@Database(entities = {TripsDomain.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TripsDao tripsDao();
}
