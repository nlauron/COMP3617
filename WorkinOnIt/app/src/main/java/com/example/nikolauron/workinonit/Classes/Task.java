package com.example.nikolauron.workinonit.Classes;

import java.io.Serializable;

/**
 * Created by Niko Lauron on 11/27/2017.
 */

public class Task implements Serializable {

    private int id;
    private String task;
    private String notes;
    private int userId;
    private int projectId;
    private String creator;
    private int complete;

    public Task (int id, String task, String notes, int userId, int projectId, String creator, int complete) {
        this.id = id;
        this.task = task;
        this.notes = notes;
        this.userId = userId;
        this.projectId = projectId;
        this.creator = creator;
        this.complete = complete;
    }

    public void setId(int id) {this.id = id;}
    public void setTask(String task) {this.task = task;}
    public void setNotes(String notes) {this.notes = notes;}
    public void setUser(int userId) {this.userId = userId;}
    public void setProject(int projectId) {this.projectId = projectId;}
    public void setCreator(String creator) {this.creator = creator;}
    public void setComplete(int complete) {this.complete = complete;}

    public int getId() {return this.id;}
    public String getTask() {return this.task;}
    public String getNotes() {return this.notes;}
    public int getUserId() {return this.userId;}
    public int getProjectId() {return this.projectId;}
    public String getCreator() {return this.creator;}
    public int getComplete() {return this.complete;}
}