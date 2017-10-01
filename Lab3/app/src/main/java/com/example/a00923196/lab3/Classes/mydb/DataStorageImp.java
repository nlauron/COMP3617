package com.example.a00923196.lab3.Classes.mydb;

/**
 * Created by A00923196 on 2017-09-19.
 */

public class DataStorageImp {
    public String state = null;
    public void saveState (String content) {
        this.state = content;
    }

    public String getState() {return state;}
}
