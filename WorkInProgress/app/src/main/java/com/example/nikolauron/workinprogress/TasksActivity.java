package com.example.nikolauron.workinprogress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nikolauron.workinprogress.Classes.DBHelper;
import com.example.nikolauron.workinprogress.Classes.ListViewAdapter;
import com.example.nikolauron.workinprogress.Classes.Project;
import com.example.nikolauron.workinprogress.Classes.Task;
import com.example.nikolauron.workinprogress.Classes.User;

import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity {

    private DBHelper db;
    private int numOfTasks;
    private String username;
    private String projectName;
    private User user;
    private Project project;
    private ListView listView;
    private ListViewAdapter adapter;
    private ArrayList<Task> tasks;
    private String[] taskList;
    private String[] status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        numOfTasks = 0;
        db = new DBHelper(this);
        username = getIntent().getStringExtra("user");
        projectName = getIntent().getStringExtra("project");
        tasks = new ArrayList<>();

        ArrayList<User> users = db.getAllUsers();
        for (User temp : users) {
            if (temp.getUsername().equals(username)) {
                user = db.getUser(Integer.toString(temp.getId()));
            }
        }

        ArrayList<Project> projects = db.getAllProjects();
        for (Project temp : projects) {
            if (temp.getProject().equals(projectName)) {
                project = db.getProject(Integer.toString(temp.getId()));
            }
        }

        ArrayList<Task> tempList = db.getAllTasks();
        for (Task task : tempList) {
            if(task.getUserId() == user.getId() && task.getProjectId() == project.getId()) {
                tasks.add(task);
            }
        }

        taskList = new String[tasks.size()];
        for (int i = 0; i < taskList.length; i++) {
            taskList[i] = tasks.get(i).getTask();
        }

        status = new String[tasks.size()];
        for (int i = 0; i < taskList.length; i++) {
            if (tasks.get(i).getComplete() == 0) {
                status[i] = "Not Complete";
            } else {
                status[i] = "Completed";
            }
        }

        numOfTasks = taskList.length;
        listView = (ListView) findViewById(R.id.taskList);
        adapter = new ListViewAdapter(this, taskList, status);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TasksActivity.this, DetailsActivity.class);
                intent.putExtra("project", projectName);
                intent.putExtra("task", taskList[position]);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        numOfTasks = 0;
        db = new DBHelper(this);
        username = getIntent().getStringExtra("user");
        projectName = getIntent().getStringExtra("project");
        tasks = new ArrayList<>();

        ArrayList<User> users = db.getAllUsers();
        for (User temp : users) {
            if (temp.getUsername().equals(username)) {
                user = db.getUser(Integer.toString(temp.getId()));
            }
        }

        ArrayList<Project> projects = db.getAllProjects();
        for (Project temp : projects) {
            if (temp.getProject().equals(projectName)) {
                project = db.getProject(Integer.toString(temp.getId()));
            }
        }

        ArrayList<Task> tempList = db.getAllTasks();
        for (Task task : tempList) {
            if(task.getUserId() == user.getId() && task.getProjectId() == project.getId()) {
                tasks.add(task);
            }
        }

        taskList = new String[tasks.size()];
        for (int i = 0; i < taskList.length; i++) {
            taskList[i] = tasks.get(i).getTask();
        }

        status = new String[tasks.size()];
        for (int i = 0; i < taskList.length; i++) {
            if (tasks.get(i).getComplete() == 0) {
                status[i] = "Not Complete";
            } else {
                status[i] = "Completed";
            }
        }

        numOfTasks = taskList.length;
        listView = (ListView) findViewById(R.id.taskList);
        adapter = new ListViewAdapter(this, taskList, status);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TasksActivity.this, DetailsActivity.class);
                intent.putExtra("project", projectName);
                intent.putExtra("task", taskList[position]);
                startActivity(intent);
            }
        });
    }

    public void backTask(View view) {
        finish();
    }

    public void projectInfo(View view) {
        Intent intent = new Intent(this, ProjectInfoActivity.class);
        intent.putExtra("project", projectName);
        intent.putExtra("tasks", numOfTasks);
        startActivity(intent);
    }
    public void createTask(View view) {
        Intent intent = new Intent(this, CreateTaskActivity.class);
        intent.putExtra("user", username);
        intent.putExtra("project", projectName);
        startActivity(intent);
    }
}
