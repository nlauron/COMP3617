package com.example.nikolauron.workinprogress.Classes;

import java.io.Serializable;

/**
 * Created by Niko Lauron on 7/8/2017.
 */

public class Task implements Serializable{

    private int id;
    private String task;
    private String user;
    private String creator;

    public Task (int id, String task, String user, String creator) {
        this.id = id;
        this.task = task;
        this.user = user;
        this.creator = creator;
    }

    public void setId(int id) {this.id = id;}
    public void setTask(String task) {this.task = task;}
    public void setUser(String user) {this.user = user;}
    public void setCreator(String creator) {this.creator = creator;}

    public int getId() {return this.id;}
    public String getTask() {return this.task;}
    public String getUser() {return this.user;}
    public String getCreator() {return this.creator;}
}
