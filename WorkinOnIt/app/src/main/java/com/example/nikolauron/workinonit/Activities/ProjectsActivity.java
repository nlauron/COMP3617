package com.example.nikolauron.workinonit.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nikolauron.workinonit.Classes.DBHelper;
import com.example.nikolauron.workinonit.Classes.Project;
import com.example.nikolauron.workinonit.Classes.Task;
import com.example.nikolauron.workinonit.Classes.User;
import com.example.nikolauron.workinonit.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProjectsActivity extends AppCompatActivity {

    private DBHelper db;
    private User user;
    private Project project;
    private int tasksNum;
    private String userLogin;
    private ArrayList<String> projects;
    private String[] projectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        db = new DBHelper(this);
        tasksNum = 0;

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
                tasksNum++;
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
        tasksNum = 0;

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
                tasksNum++;
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

    public void userInfo(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra("user", userLogin);
        intent.putExtra("projects", projectList.length);
        intent.putExtra("tasks", tasksNum);
        startActivity(intent);
    }

    public void createProject(View view) {
        Intent intent = new Intent(this, CreateProjectActivity.class);
        intent.putExtra("user", userLogin);
        startActivity(intent);
    }
}
