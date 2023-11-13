package com.example.tripsmodule.Adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.tripsmodule.Activities.updatedata;
import com.example.tripsmodule.Domains.TripsDomain;
import com.example.tripsmodule.R;
import com.example.tripsmodule.dao.TripsDao;
import com.example.tripsmodule.database.AppDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder> {
    List<TripsDomain> tripsDomain;

    public myadapter(List<TripsDomain> tripsDomain) {
        this.tripsDomain = tripsDomain;
    }

    @NotNull
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NotNull @NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
        return new myviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NotNull @NonNull myadapter.myviewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(tripsDomain.get(position).getTitle());
        holder.location.setText(tripsDomain.get(position).getLocation());
        holder.description.setText(tripsDomain.get(position).getDescription());
        holder.bed.setText(String.valueOf(tripsDomain.get(position).getBed()));
        holder.guide.setText(tripsDomain.get(position).isGuide() ? "Guide Available" : "Guide Not Available");
        holder.score.setText(String.valueOf(tripsDomain.get(position).getScore()));
        holder.pic.setText(tripsDomain.get(position).getPic());
        holder.wifi.setText(tripsDomain.get(position).isWifi() ? "WiFi Available" : "WiFi Not Available");
        holder.price.setText(String.valueOf(tripsDomain.get(position).getPrice()));

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = Room.databaseBuilder(holder.itemView.getContext(),
                        AppDatabase.class, "room_db").allowMainThreadQueries().build();
                TripsDao tripsDao = db.tripsDao();
                tripsDao.deleteById(tripsDomain.get(position).getId());
                tripsDomain.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.editbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(new Intent(holder.editbtn.getContext(), updatedata.class));

                // Passer l'ID de l'élément à mettre à jour en tant qu'extra
                intent.putExtra("id", tripsDomain.get(position).getId());

                // Passer les autres attributs
                intent.putExtra("utitle", tripsDomain.get(position).getTitle());
                intent.putExtra("ulocation", tripsDomain.get(position).getLocation());
                intent.putExtra("udescription", tripsDomain.get(position).getDescription());
                intent.putExtra("ubed", tripsDomain.get(position).getBed());
                intent.putExtra("uguide", tripsDomain.get(position).isGuide());
                intent.putExtra("uscore", tripsDomain.get(position).getScore());
                intent.putExtra("upic", tripsDomain.get(position).getPic());
                intent.putExtra("uwifi", tripsDomain.get(position).isWifi());
                intent.putExtra("uprice", tripsDomain.get(position).getPrice());
                holder.editbtn.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return tripsDomain.size();
    }

    // ArrayList<TripsDomain> items;

    class myviewholder extends RecyclerView.ViewHolder{

        TextView title,location,description,bed,guide,score,pic,wifi,price;

        ImageButton deletebtn,editbtn;
        public myviewholder(@NotNull @NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            location=itemView.findViewById(R.id.location);
            description=itemView.findViewById(R.id.description);
            bed=itemView.findViewById(R.id.bed);
            guide=itemView.findViewById(R.id.guide);
            score=itemView.findViewById(R.id.score);
            pic=itemView.findViewById(R.id.pic);
            wifi=itemView.findViewById(R.id.wifi);
            price=itemView.findViewById(R.id.price);
            deletebtn=itemView.findViewById(R.id.deleteBtn);
            editbtn=itemView.findViewById(R.id.editbtn);

        }
    }



}
