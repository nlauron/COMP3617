package com.example.nikolauron.workinprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.nikolauron.workinprogress.Classes.DBHelper;

public class UserActivity extends AppCompatActivity {

    private DBHelper db;
    private String username;
    private int numOfProjects;
    private int numOfTasks;
    private TextView projectNum;
    private TextView taskNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        db = new DBHelper(this);

        username = getIntent().getStringExtra("user");
        numOfProjects = getIntent().getIntExtra("projects", 0);
        numOfTasks = getIntent().getIntExtra("tasks", 0);

        setTitle(username);

        projectNum = (TextView) findViewById(R.id.projectNum);
        taskNum = (TextView) findViewById(R.id.tasksNum);
        projectNum.setText(Integer.toString(numOfProjects));
        taskNum.setText(Integer.toString(numOfTasks));
    }

    public void backUser(View view) {
        finish();
    }
}
