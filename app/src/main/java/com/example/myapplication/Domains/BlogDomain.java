package com.example.myapplication.Domains;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class BlogDomain implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public  int id ;
    @ColumnInfo
    public String title ;

    @ColumnInfo
    public String description;
    @ColumnInfo
    public String pic;

    public BlogDomain(String title, String description, String pic) {
        this.title = title;
        this.description = description;
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
