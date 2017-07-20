package com.example.nikolauron.workinprogress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.nikolauron.workinprogress.Classes.DBHelper;
import com.example.nikolauron.workinprogress.Classes.Project;
import com.example.nikolauron.workinprogress.Classes.Task;
import com.example.nikolauron.workinprogress.Classes.User;

import java.io.Serializable;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private DBHelper db;
    private EditText username;
    private EditText password;
    private User loginId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DBHelper(this);

        if (db.getAllUsers().size() <= 0) {
            User admin = new User(0, "Admin", "abc", 0, 0);
            User test = new User(0, "Test", "def", 0, 0);
            db.addUser(admin);
            db.addUser(test);
        }

        if (db.getAllProjects().size() <= 0) {
            Project test = new Project(1, "Test Project", "Test Project", 0);
            db.addProject(test);
        }

        if (db.getAllTasks().size() <= 0) {
            Task testOne = new Task(1, "Test One", "This is the first task", 1, 1, "Admin", 1);
            Task testTwo = new Task(2, "Test Two", "This is the second task", 1, 1, "Admin", 0);
            Task testThree = new Task(3, "Test Three", "This is the third task",  1, 1, "Admin", 0);
            db.addTask(testOne);
            db.addTask(testTwo);
            db.addTask(testThree);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        db = new DBHelper(this);
    }

    public boolean isValidCredentials(String username, String password) {
        ArrayList<User> temp = db.getAllUsers();
        boolean valid = false;
        for (User user : temp) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    valid = true;

                    break;
                }
            } else {
                valid = false;
            }
        }
        return valid;
    }

    public boolean validateLogin() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        boolean valid = true;

        String userLogin = username.getText().toString();
        String passLogin = password.getText().toString();

        if (!isValidCredentials(userLogin, passLogin)) {
            username.setError("Invalid Username or Password");
            valid = false;
        }
        return valid;
    }

    public void login(View view) {
        if (validateLogin()) {
            username = (EditText) findViewById(R.id.username);
            String userLogin = username.getText().toString();
            ArrayList<User> temp = db.getAllUsers();
            for (User user : temp) {
                if (user.getUsername().equals(userLogin)) {
                    loginId = user;
                }
            }

            Intent intent = new Intent(this, ProjectsActivity.class);
            intent.putExtra("login", loginId.getUsername());
            startActivity(intent);
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
