package com.example.nikolauron.workinprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nikolauron.workinprogress.Classes.DBHelper;
import com.example.nikolauron.workinprogress.Classes.Project;

import java.util.ArrayList;

public class ProjectInfoActivity extends AppCompatActivity {

    private DBHelper db;
    private int numOfTasks;
    private String projectName;
    private TextView tasksNum;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_info);
        db = new DBHelper(this);
        numOfTasks = getIntent().getIntExtra("tasks", 0);
        projectName = getIntent().getStringExtra("project");
        setTitle(projectName);

        tasksNum = (TextView) findViewById(R.id.projectTasksNum);
        description = (EditText) findViewById(R.id.projectNotes);

        tasksNum.setText(Integer.toString(numOfTasks));

        ArrayList<Project> tempList = db.getAllProjects();
        for (Project temp : tempList) {
            if (temp.getProject().equals(projectName)) {
                description.setText(temp.getDescription());
            }
        }

    }

    public void backProjectInfo(View view) {
        finish();
    }
}
