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
    private String username;
    private String projectName;
    private User user;
    private Project project;
    private ListView listView;
    private ListViewAdapter adapter;
    private ArrayList<String> tasks;
    private String[] taskList;
    private String[] status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        db = new DBHelper(this);
        username = getIntent().getStringExtra("user");
        projectName = getIntent().getStringExtra("project");

        ArrayList<User> users = db.getAllUsers();
        for (User temp : users) {
            if (temp.getUsername().equals(username)) {
                user = db.getUser(Integer.toString(temp.getId()));
            }
        }

        ArrayList<Project> projects = db.getAllProjects();
        for (Project temp : projects) {
            if (project.getProject().equals(projectName)) {
                project = db.getProject(Integer.toString(temp.getId()));
            }
        }

        ArrayList<Task> tempList = db.getAllTasks();
        for (Task task : tempList) {
            if(task.getUserId() == user.getId() && task.getProjectId() == project.getId()) {
                tasks.add(task.getTask());
            }
        }

        taskList = tasks.toArray(new String[0]);
        status = new String[tasks.size()];

        for (Task tempTask : tempList) {
            if (tempTask.getComplete() == 0) {
                status[tempTask.getId()] = "Not Complete";
            } else {
                status[tempTask.getId()] = "Completed";
            }
        }

        listView = (ListView) findViewById(R.id.taskList);
        adapter = new ListViewAdapter(this, taskList, status);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TasksActivity.this, DetailsActivity.class);
                intent.putExtra("task", taskList[position]);
                startActivity(intent);
            }
        });
    }

    public void createTask(View view) {
        Intent intent = new Intent(this, CreateTaskActivity.class);
        intent.putExtra("user", username);
        intent.putExtra("project", projectName);
        startActivity(intent);
        finish();
    }
}
