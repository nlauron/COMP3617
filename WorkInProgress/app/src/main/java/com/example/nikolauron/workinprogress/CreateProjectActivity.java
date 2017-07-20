package com.example.nikolauron.workinprogress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.nikolauron.workinprogress.Classes.DBHelper;
import com.example.nikolauron.workinprogress.Classes.Project;
import com.example.nikolauron.workinprogress.Classes.Task;
import com.example.nikolauron.workinprogress.Classes.User;

import java.util.ArrayList;

public class CreateProjectActivity extends AppCompatActivity {

    private DBHelper db;
    private String user;
    private EditText projectTitle;
    private EditText projectDesc;
    private EditText initTask;
    private EditText notes;
    private Project project;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
        db = new DBHelper(this);
        user = getIntent().getStringExtra("user");
    }

    public boolean validate(String projectName, String description, String taskName, String notes) {
        boolean valid;

        if (projectName.equals("") || description.equals("") || taskName.equals("") || notes.equals("")) {
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }

    public boolean duplicate (String projectName) {
        boolean valid = true;
        ArrayList<Project> temp = db.getAllProjects();

        for (Project tempProject : temp) {
            if (tempProject.getProject().equals(projectName)) {
                valid = false;
            }
        }

        return valid;
    }

    public int getUserId(String username) {
        int id = 0;
        ArrayList<User> temp = db.getAllUsers();

        for (User tempUser : temp) {
            if (tempUser.getUsername().equals(username)) {
                id = tempUser.getId();
            }
        }
        return id;
    }

    public int getProjectId(String projectName) {
        int id = 0;
        ArrayList<Project> temp = db.getAllProjects();

        for (Project tempProject : temp) {
            if (tempProject.getProject().equals(projectName)) {
                id = tempProject.getId();
            }
        }
        return id;
    }

    public void backProject(View view) {
        finish();
    }

    public void createNewProject(View view) {
        projectTitle = (EditText) findViewById(R.id.projectTitleET);
        projectDesc = (EditText) findViewById(R.id.descriptionET);
        initTask = (EditText) findViewById(R.id.taskET);
        notes = (EditText) findViewById(R.id.notesET) ;

        String title = projectTitle.getText().toString();
        String desc = projectDesc.getText().toString();
        String taskName = initTask.getText().toString();
        String taskNotes = notes.getText().toString();

        if (!validate(title, desc, taskName, taskNotes)) {
            projectTitle.setError("Cannot leave fields blank");
        } else {
            if (!duplicate(title)) {
                projectTitle.setError("Project Name Taken");
            } else {
                project = new Project(0, title, desc, 1);
                db.addProject(project);

                int userId = getUserId(user);
                int projectId = getProjectId(title);

                task = new Task(0, taskName, taskNotes, userId, projectId, user, 0);
                db.addTask(task);
                finish();
            }
        }
    }
}
