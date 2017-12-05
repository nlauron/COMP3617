package com.example.nikolauron.workinonit.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.nikolauron.workinonit.Classes.DBHelper;
import com.example.nikolauron.workinonit.Classes.Project;
import com.example.nikolauron.workinonit.Classes.Task;
import com.example.nikolauron.workinonit.Classes.User;
import com.example.nikolauron.workinonit.R;

import java.util.ArrayList;

public class CreateTaskActivity extends AppCompatActivity {

    private DBHelper db;
    private String username;
    private String projectName;
    private Task task;
    private EditText taskName;
    private EditText notes;
    private Spinner userspin;
    private String[] users;
    private ArrayList<String> usersLists;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        db = new DBHelper(this);
        username = getIntent().getStringExtra("user");
        projectName = getIntent().getStringExtra("project");
        userspin = (Spinner) findViewById(R.id.userList);
        usersLists = new ArrayList<>();

        ArrayList<User> tempUserList = db.getAllUsers();
        for (User temp : tempUserList) {
            usersLists.add(temp.getUsername());
        }
        users = usersLists.toArray(new String[0]);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userspin.setAdapter(adapter);
    }

    public int getUserId(String user) {
        int id = 0;
        ArrayList<User> users = db.getAllUsers();
        for (User temp : users) {
            if (temp.getUsername().equals(user)) {
                id = temp.getId();
            }
        }
        return id;
    }

    public int getProjectId(String project) {
        int id = 0;
        ArrayList<Project> projects = db.getAllProjects();
        for (Project temp : projects) {
            if (temp.getProject().equals(project)) {
                id = temp.getId();
            }
        }
        return id;
    }

    public boolean validate(String taskname, String username) {
        boolean valid;
        if (taskname.equals("") || username.equals("")){
            valid = false;
        } else {
            valid = true;
        }

        return valid;
    }

    public void backCreateTask(View view) {
        finish();
    }

    public void createTask(View view) {
        taskName = (EditText) findViewById(R.id.taskNameET);
        notes = (EditText) findViewById(R.id.taskNotesET);
        userspin = (Spinner) findViewById(R.id.userList);
        String name = taskName.getText().toString();
        String taskNotes = notes.getText().toString();
        String user = userspin.getSelectedItem().toString();
        int userId = getUserId(user);
        int projectId = getProjectId(projectName);

        if (!validate(name, user)) {
            taskName.setError("Invalid Task Name or Selected User");
        } else {
            task = new Task(0, name, taskNotes, userId, projectId, username, 0);
            db.addTask(task);
            finish();
        }
    }
}
