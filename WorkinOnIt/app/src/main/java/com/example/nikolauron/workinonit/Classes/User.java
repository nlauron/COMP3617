package com.example.nikolauron.workinonit.Classes;

import java.io.Serializable;

/**
 * Created by Niko Lauron on 11/27/2017.
 */

public class User  implements Serializable {

    private int id;
    private String username;
    private String password;
    private int projects;
    private int tasks;

    public User (int id, String username, String password, int projects, int tasks) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.projects = projects;
        this.tasks = tasks;
    }

    public void setId(int id) {this.id = id;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setProjects(int projects) {this.projects = projects;}
    public void setTasks(int tasks) {this.tasks = tasks;}

    public int getId() {return this.id;}
    public String getUsername() {return this.username;}
    public String getPassword() {return this.password;}
    public int getProjects() {return this.projects;}
    public int getTasks() {return this.tasks;}
}
