package com.example.a00923196.lab3.Classes.mydb;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by A00923196 on 2017-09-19.
 */

public class Preferences {

    public String state = null;

    public void saveState (Context context, String content) {
        SharedPreferences preferences = context.getSharedPreferences("State", 0);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("state", content);
        editor.commit();
    }

    public String getState(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("State", 0);
        String restoredText = prefs.getString("state", null);

        return restoredText;
    }
}
