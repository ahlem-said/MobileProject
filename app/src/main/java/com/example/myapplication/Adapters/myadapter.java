package com.example.myapplication.Adapters;


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

import com.example.myapplication.DAO.BlogDao;
import com.example.myapplication.Domains.BlogDomain;
import com.example.myapplication.R;
import com.example.myapplication.activities.updatedata;
import com.example.myapplication.database.Appdatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder> {
        List<BlogDomain> blogDomains;

      public myadapter(List<BlogDomain> blogDomains) {
        this.blogDomains = blogDomains;
        }

@NotNull
@Override
public myviewholder onCreateViewHolder(@NotNull @NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.blog1,parent,false);
        return new myviewholder(view);
        }



    @Override
public void onBindViewHolder(@NotNull @NonNull myviewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(blogDomains.get(position).getTitle());

        holder.description.setText(blogDomains.get(position).getDescription());

        holder.pic.setText(blogDomains.get(position).getPic());

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        Appdatabase db = Room.databaseBuilder(holder.itemView.getContext(),
                Appdatabase.class, "blog_db").allowMainThreadQueries().build();
        BlogDao blogDao = db.blogDao();
    blogDao.deleteById(blogDomains.get(position).getId());
    blogDomains.remove(position);
        notifyDataSetChanged();
        }
        });

        holder.editbtn.setOnClickListener(new View.OnClickListener(){

@Override
public void onClick(View view) {
        Intent intent=new Intent(new Intent(holder.editbtn.getContext(), updatedata.class));

        // Passer l'ID de l'élément à mettre à jour en tant qu'extra
        intent.putExtra("id", blogDomains.get(position).getId());

        // Passer les autres attributs
        intent.putExtra("utitle", blogDomains.get(position).getTitle());

        intent.putExtra("udescription", blogDomains.get(position).getDescription());

        intent.putExtra("upic", blogDomains.get(position).getPic());

        holder.editbtn.getContext().startActivity(intent);
        }
        });

        }


@Override
public int getItemCount() {
        return blogDomains.size();
        }

// ArrayList<TripsDomain> items;

class myviewholder extends RecyclerView.ViewHolder{

    TextView title,description,pic;

    ImageButton deletebtn,editbtn;
    public myviewholder(@NotNull @NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.title);

        description=itemView.findViewById(R.id.description);

        pic=itemView.findViewById(R.id.pic);

        deletebtn=itemView.findViewById(R.id.deleteBtn);
        editbtn=itemView.findViewById(R.id.editbtn);

    }
}



}