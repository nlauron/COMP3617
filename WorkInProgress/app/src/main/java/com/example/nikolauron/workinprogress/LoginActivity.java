package com.example.nikolauron.workinprogress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.nikolauron.workinprogress.Classes.DBHelper;
import com.example.nikolauron.workinprogress.Classes.Project;
import com.example.nikolauron.workinprogress.Classes.User;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private DBHelper db;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DBHelper(this);

        if (db.getAllUsers().size() <= 0) {
            User admin = new User(0, "admin", "abc", 0, 0, "Admin");
            User test = new User(0, "test", "def", 0, 0, "Test");
            db.addUser(admin);
            db.addUser(test);
        }

        if (db.getAllProjects().size() <= 0) {
            Project origin = new Project(0, "Origin", 0, 0);
        }
    }
//
//    public boolean isValidUsername(String username) {
//        ArrayList<User> temp = db.getAllUsers();
//        boolean valid = false;
//        for (User user : temp) {
//            if (user.getUsername().equals(username)) {
//                valid = true;
//            } else {
//                valid = false;
//            }
//        }
//        return valid;
//    }

    public boolean isValidPassword(String username, String password) {
        ArrayList<User> temp = db.getAllUsers();
        boolean valid = false;
        for (User user : temp) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    valid = true;
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

//        if (!isValidUsername(userLogin)){
//            username.setError("Invalid Username");
//            valid = false;
//        } else {
//            username.setError(null);
//        }

        if (!isValidPassword(userLogin, passLogin)) {
            username.setError("Invalid Username or Password");
            valid = false;
        }
        return valid;
    }

    public void login(View view) {
        if (validateLogin()) {
            Intent intent = new Intent(this, ProjectsActivity.class);
            startActivity(intent);
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
