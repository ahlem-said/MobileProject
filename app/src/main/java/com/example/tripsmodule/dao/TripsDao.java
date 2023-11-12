package com.example.tripsmodule.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tripsmodule.Domains.TripsDomain;

import java.util.List;

@Dao
public interface TripsDao {
    @Insert
    void insertrecord(TripsDomain tripsDomain);
    @Delete
    void delete(TripsDomain tripsDomain);
    @Query("SELECT * FROM trips")
    List<TripsDomain> getAll();
}
