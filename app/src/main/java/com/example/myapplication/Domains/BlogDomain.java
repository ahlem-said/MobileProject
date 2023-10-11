package com.example.myapplication.Domains;

import java.io.Serializable;

public class BlogDomain implements Serializable {
    private String title ;

    private String description;
    private String pic;

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
