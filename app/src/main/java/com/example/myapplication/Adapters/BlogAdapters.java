package com.example.myapplication.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.myapplication.activities.DetailActivity;
import com.example.myapplication.Domains.BlogDomain;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class BlogAdapters extends RecyclerView.Adapter<BlogAdapters.ViewHolder>{
    ArrayList<BlogDomain> items;
    public BlogAdapters(ArrayList<BlogDomain> items) {
        this.items = items;

    }
    @NonNull
    @Override
    public BlogAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewlayout_blog,parent,false);
        return new ViewHolder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull BlogAdapters.ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());


        int drawableResId=holder.itemView.getResources().getIdentifier(items.get(position).getPic(),
                "drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResId)
                .transform(new CenterCrop(),new GranularRoundedCorners(40,40,40,40))
                .into(holder.pic);

        holder.itemView.setOnClickListener(v ->{
                    Intent intent=new Intent(holder.itemView.getContext(), DetailActivity.class);
                    intent.putExtra("object",items.get(position));
                    holder.itemView.getContext().startActivity(intent);
                }
        );


    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            pic=itemView.findViewById(R.id.picImg);
        }
    }



    public void setItems(ArrayList<BlogDomain> items) {
        this.items = items;
        notifyDataSetChanged();
    }

}