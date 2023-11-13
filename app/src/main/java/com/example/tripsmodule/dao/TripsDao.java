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

    @Query("SELECT * FROM trips")
    List<TripsDomain> getAll();

    @Query("DELETE FROM trips WHERE id=:id")
    void deleteById(int id);


    @Query("UPDATE trips SET title = :ftitle, location = :flocation, description = :fdescription, " +
            "bed = :fbed, guide = :fguide, score = :fscore, pic = :fpic, wifi = :fwifi, price = :fprice WHERE id = :id")
    void updateById(int id, String ftitle, String flocation, String fdescription, int fbed, boolean fguide,
                    double fscore, String fpic, boolean fwifi, int fprice);





}
