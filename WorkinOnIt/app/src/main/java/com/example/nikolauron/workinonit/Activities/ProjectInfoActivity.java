package com.example.nikolauron.workinonit.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nikolauron.workinonit.Classes.DBHelper;
import com.example.nikolauron.workinonit.Classes.Project;
import com.example.nikolauron.workinonit.R;

import java.util.ArrayList;

public class ProjectInfoActivity extends AppCompatActivity {

    private DBHelper db;
    private int numOfTasks;
    private Project proj;
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
                proj = db.getProject(Integer.toString(temp.getId()));
            }
        }

    }

    public void updateProject(View view) {
        Project oldProject = proj;
        String newNotes = description.getText().toString();
        proj.setDescription(newNotes);
        db.updateProject(Integer.toString(oldProject.getId()), proj);
        finish();
    }

    public void backProjectInfo(View view) {
        finish();
    }
}
