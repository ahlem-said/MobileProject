package com.example.tripsmodule.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.tripsmodule.R;
import com.example.tripsmodule.dao.TripsDao;
import com.example.tripsmodule.database.AppDatabase;

public class updatedata extends AppCompatActivity {

    int id;
    EditText ftitle, flocation,fdescription, fbed,fguide,
    fscore, fpic, fwifi, fprice;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedata);

        ftitle=findViewById(R.id.edittitle);
        flocation=findViewById(R.id.editlocation);
        fdescription=findViewById(R.id.editdescription);
        fbed=findViewById(R.id.editbed);
        fguide=findViewById(R.id.editguide);
        fscore=findViewById(R.id.editscore);
        fpic=findViewById(R.id.editpic);
        fwifi=findViewById(R.id.editwifi);
        fprice=findViewById(R.id.editprice);
        btn=findViewById(R.id.btn);




        ftitle.setText(getIntent().getStringExtra("ufname"));
        flocation.setText(getIntent().getStringExtra("ulocation"));
        fdescription.setText(getIntent().getStringExtra("udescription"));
        fbed.setText(String.valueOf(getIntent().getIntExtra("ubed", 0)));
        boolean uguide = getIntent().getBooleanExtra("uguide", false);
        fguide.setText(String.valueOf(uguide));
        fscore.setText(String.valueOf(getIntent().getDoubleExtra("uscore", 0.0))); // 0.0 est une valeur par défaut si l'attribut n'est pas trouvé
        fpic.setText(getIntent().getStringExtra("upic"));
        boolean uwifi = getIntent().getBooleanExtra("uwifi", false);
        fwifi.setText(String.valueOf(uwifi));
        fprice.setText(String.valueOf(getIntent().getIntExtra("uprice", 0))); // 0 est une valeur par défaut si l'attribut n'est pas trouvé



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "room_db").allowMainThreadQueries().build();
                TripsDao tripsDao = db.tripsDao();
                tripsDao.updateById(id, ftitle.getText().toString(), flocation.getText().toString(),
                        fdescription.getText().toString(), Integer.parseInt(fbed.getText().toString()),
                        uguide, Double.parseDouble(fscore.getText().toString()), fpic.getText().toString(),
                        uwifi, Integer.parseInt(fprice.getText().toString()));


                startActivity(new Intent(getApplicationContext(), fetchdata.class));
                finish();
            }

        });
    }




}