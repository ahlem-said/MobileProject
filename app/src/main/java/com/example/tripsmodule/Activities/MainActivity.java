package com.example.tripsmodule.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.tripsmodule.Adapters.CategoryAdapter;

import com.example.tripsmodule.Adapters.TripsAdapter;
import com.example.tripsmodule.Domains.CategoryDomain;
import com.example.tripsmodule.Domains.TripsDomain;
import com.example.tripsmodule.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterTrips,adapterCat;
    private RecyclerView recyclerViewTrips,recyclerViewCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();

        ImageButton adminButton = findViewById(R.id.adminButton);
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigez vers la page du formulaire (FormActivity) en utilisant une intention (Intent).
                Intent intent = new Intent(getApplicationContext(), FormActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initRecyclerView() {
        ArrayList<TripsDomain> items = new ArrayList<>();
        items.add(new TripsDomain("Island Hopping and Hidden Paradise","Kandari, Indonesia","Une île privée au milieu d'un lac ," +
                "de ski nautique dans le domaine de Hardwick Parks, " +
                "dans l'Oxfordshire. Vous aurez un accès exclusif à The Island." +
                " La vraie maison est immense ! Il peut accueillir jusqu'à 22 personnes." +
                " Il dispose de 8 chambres dont l'une est une chambre de style dortoir pouvant accueillir 8 personnes.",2,true,
                4.8,"pic1",true,1000));
        items.add(new TripsDomain("États-Unis d'Amérique","Hawai ","Une île privée au milieu d'un lac ," +
                "de ski nautique dans le domaine de Hardwick Parks, " +
                "dans l'Oxfordshire. Vous aurez un accès exclusif à The Island." +
                " La vraie maison est immense ! Il peut accueillir jusqu'à 22 personnes." +
                " Il dispose de 8 chambres dont l'une est une chambre de style dortoir pouvant accueillir 8 personnes.",1,false,
                5,"pic2",false,2000));
        items.add(new TripsDomain("Temple Wat Arun ","Thaïlande","Une île privée au milieu d'un lac ," +
                "de ski nautique dans le domaine de Hardwick Parks, " +
                "dans l'Oxfordshire. Vous aurez un accès exclusif à The Island." +
                " La vraie maison est immense ! Il peut accueillir jusqu'à 22 personnes." +
                " Il dispose de 8 chambres dont l'une est une chambre de style dortoir pouvant accueillir 8 personnes.",3,true,
                4,"pic3",true,35000));
        recyclerViewTrips = findViewById(R.id.view_trip);
        recyclerViewTrips.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapterTrips=new TripsAdapter(items);
        recyclerViewTrips.setAdapter(adapterTrips);

        ArrayList<CategoryDomain> catsList =new ArrayList<>();
        catsList.add(new CategoryDomain("Beaches","cat1"));
        catsList.add(new CategoryDomain("Camps","cat2"));
        catsList.add(new CategoryDomain("Desert","cat3"));
        catsList.add(new CategoryDomain("Forest","cat4"));
        catsList.add(new CategoryDomain("Mountain","cat5"));

       recyclerViewCategory=findViewById(R.id.view_cat);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapterCat=new CategoryAdapter(catsList);
        recyclerViewCategory.setAdapter(adapterCat);
    }


}