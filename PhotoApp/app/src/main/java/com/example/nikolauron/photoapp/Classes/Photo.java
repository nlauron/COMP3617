package com.example.nikolauron.photoapp.Classes;

/**
 * Created by A00923196 on 2017-10-03.
 */

public class Photo {

    private Integer id;
    private String title;
    private String date;


    public Photo(Integer id, String title, String date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
