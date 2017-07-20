package com.example.nikolauron.workinprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nikolauron.workinprogress.Classes.DBHelper;
import com.example.nikolauron.workinprogress.Classes.Task;
import com.example.nikolauron.workinprogress.Classes.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private DBHelper db;
    private String taskName;
    private TextView project;
    private TextView owner;
    private TextView creator;
    private EditText description;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        db = new DBHelper(this);
        taskName = getIntent().getStringExtra("task");
        project = (TextView) findViewById(R.id.projectName);
        owner  = (TextView) findViewById(R.id.ownerName);
        creator = (TextView) findViewById(R.id.creatorName);
        description = (EditText) findViewById(R.id.notes);

        ArrayList<Task> tempTaskList = db.getAllTasks();
        for (Task temp : tempTaskList) {
            if (temp.getTask().equals(taskName)) {
                task = db.getTask(Integer.toString(temp.getId()));
            }
        }

        project.setText(task.getTask());
        creator.setText(task.getCreator());
        description.setText(task.getNotes(), TextView.BufferType.EDITABLE);

        ArrayList<User> tempUserList = db.getAllUsers();
        for (User temp : tempUserList) {
            if (temp.getId() == task.getUserId()) {
                owner.setText(temp.getUsername());
            }
        }
    }

    public void backDetails(View view) {
        finish();
    }

    public void update(View view) {
        Task oldTask = task;
        String newNotes = description.getText().toString();
        task.setNotes(newNotes);
        db.updateTask(Integer.toString(oldTask.getId()), task);
        finish();
    }

    public void complete(View view) {
        Task oldTask = task;
        task.setComplete(1);
        db.updateTask(Integer.toString(oldTask.getId()), task);
        finish();
    }

    public void delete(View view) {
        db.removeTask(Integer.toString(task.getId()));
        finish();
    }
}
