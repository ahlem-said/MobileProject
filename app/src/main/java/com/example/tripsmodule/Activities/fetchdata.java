package com.example.tripsmodule.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.tripsmodule.Adapters.myadapter;
import com.example.tripsmodule.Domains.TripsDomain;
import com.example.tripsmodule.R;
import com.example.tripsmodule.dao.TripsDao;
import com.example.tripsmodule.database.AppDatabase;

import java.util.List;

public class fetchdata extends AppCompatActivity {

    RecyclerView recview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetchdata);


        getroomdata();
    }
    public void getroomdata(){
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "room_db").allowMainThreadQueries().build();
        TripsDao tripsDao = db.tripsDao();
        recview=findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this ));
        List<TripsDomain> tripsDomain=tripsDao.getAll();
        myadapter adapter=new myadapter(tripsDomain);
        recview.setAdapter(adapter);

    }
}