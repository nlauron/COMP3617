package com.example.nikolauron.workinprogress.Classes;

import java.io.Serializable;

/**
 * Created by Niko Lauron on 7/8/2017.
 */

public class Task implements Serializable{

    private int id;
    private String task;
    private String user;
    private String project;
    private String creator;
    private int complete;

    public Task (int id, String task, String user, String project, String creator, int complete) {
        this.id = id;
        this.task = task;
        this.user = user;
        this.project = project;
        this.creator = creator;
        this.complete = complete;
    }

    public void setId(int id) {this.id = id;}
    public void setTask(String task) {this.task = task;}
    public void setUser(String user) {this.user = user;}
    public void setProject(String project) {this.project = project;}
    public void setCreator(String creator) {this.creator = creator;}
    public void setComplete(int complete) {this.complete = complete;}

    public int getId() {return this.id;}
    public String getTask() {return this.task;}
    public String getUser() {return this.user;}
    public String getProject() {return this.project;}
    public String getCreator() {return this.creator;}
    public int getComplete() {return this.complete;}
}
