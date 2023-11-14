package com.example.tripsmodule.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tripsmodule.Adapters.CategoryAdapter;

import com.example.tripsmodule.Adapters.TripsAdapter;
import com.example.tripsmodule.Domains.CategoryDomain;
import com.example.tripsmodule.Domains.TripsDomain;
import com.example.tripsmodule.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText searchEditText;

    TripsAdapter adapter;

    private RecyclerView.Adapter adapterTrips,adapterCat;
    private RecyclerView recyclerViewTrips,recyclerViewCategory;
    private ArrayList<TripsDomain> allTrips; // Liste complète de voyages
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();

        // Récupérez l'EditText de recherche
        searchEditText = findViewById(R.id.search);
        // Récupérez la liste complète de voyages (elle doit être initialisée dans initRecyclerView)
        allTrips = new ArrayList<>(); // Initialisez cette liste avec vos voyages

        // Associez un TextWatcher pour surveiller les changements de texte
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Non utilisé dans ce cas
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Filtrer les voyages à mesure que l'utilisateur tape
                filterTrips(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Non utilisé dans ce cas
            }
        });

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
    private void filterTrips(String searchText) {
        List<TripsDomain> dataSearchList = new ArrayList<>();

        // Parcourez la liste complète de voyages
        for (TripsDomain trip : allTrips) {
            if (trip.getLocation().toLowerCase().contains(searchText.toLowerCase())) {
                // Si l'emplacement du voyage contient le texte de recherche, ajoutez-le aux voyages filtrés
                dataSearchList.add(trip);
            }
        }
        if (dataSearchList.isEmpty()){
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }



    }

    private void initRecyclerView() {
        allTrips = new ArrayList<>();
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
        allTrips.addAll(items); // Ajoutez tous les voyages à 'allTrips'
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