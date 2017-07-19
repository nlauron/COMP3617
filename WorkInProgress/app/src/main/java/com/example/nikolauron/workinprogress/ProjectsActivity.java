package com.example.nikolauron.workinprogress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nikolauron.workinprogress.Classes.DBHelper;
import com.example.nikolauron.workinprogress.Classes.Project;
import com.example.nikolauron.workinprogress.Classes.Task;
import com.example.nikolauron.workinprogress.Classes.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProjectsActivity extends AppCompatActivity {

    private DBHelper db;
    private User user;
    private Project project;
    private String userLogin;
    private ArrayList<String> projects;
    private String[] projectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        db = new DBHelper(this);

        projects = new ArrayList<>();
        userLogin = getIntent().getStringExtra("login");
        ArrayList<User> tempUser = db.getAllUsers();
        ArrayList<Task> tempTask = db.getAllTasks();

        for (User temp : tempUser) {
            if (temp.getUsername().equals(userLogin)) {
                user = db.getUser(Integer.toString(temp.getId()));
            }
        }

        for (Task temp : tempTask) {
            if (temp.getUserId() == user.getId()) {
                project = db.getProject(Integer.toString(temp.getProjectId()));
                projects.add(project.getProject());
            }
        }

        Set<String> tempList = new HashSet<>();
        tempList.addAll(projects);
        projects.clear();
        projects.addAll(tempList);
        projectList = projects.toArray(new String[0]);

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.listview, projectList);
        ListView listView = (ListView) findViewById(R.id.projectList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProjectsActivity.this, TasksActivity.class);
                intent.putExtra("project", projectList[position]);
                intent.putExtra("user", userLogin);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        db = new DBHelper(this);

        projects = new ArrayList<>();
        userLogin = getIntent().getStringExtra("login");
        ArrayList<User> tempUser = db.getAllUsers();
        ArrayList<Task> tempTask = db.getAllTasks();

        for (User temp : tempUser) {
            if (temp.getUsername().equals(userLogin)) {
                user = db.getUser(Integer.toString(temp.getId()));
            }
        }

        for (Task temp : tempTask) {
            if (temp.getUserId() == user.getId()) {
                project = db.getProject(Integer.toString(temp.getProjectId()));
                projects.add(project.getProject());
            }
        }

        Set<String> tempList = new HashSet<>();
        tempList.addAll(projects);
        projects.clear();
        projects.addAll(tempList);
        projectList = projects.toArray(new String[0]);

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.listview, projectList);
        ListView listView = (ListView) findViewById(R.id.projectList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProjectsActivity.this, TasksActivity.class);
                intent.putExtra("project", projectList[position]);
                intent.putExtra("user", userLogin);
                startActivity(intent);
            }
        });
    }

    public void logout(View view) {
        finish();
    }

    public void createProject(View view) {
        Intent intent = new Intent(this, CreateProjectActivity.class);
        intent.putExtra("user", userLogin);
        startActivity(intent);
    }
}
