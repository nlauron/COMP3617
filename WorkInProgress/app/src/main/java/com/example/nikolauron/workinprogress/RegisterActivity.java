package com.example.nikolauron.workinprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.nikolauron.workinprogress.Classes.DBHelper;
import com.example.nikolauron.workinprogress.Classes.User;

public class RegisterActivity extends AppCompatActivity {

    DBHelper db;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public boolean validate(String username, String password) {
        boolean valid;
        if (username.equals("") || password.equals("")){
            valid = false;
        } else {
            valid = true;
        }

        return valid;
    }

    public void register(View view) {
        db = new DBHelper(this);
        username = (EditText) findViewById(R.id.usernameET);
        password = (EditText) findViewById(R.id.passwordET);
        String user = username.getText().toString();
        String pass = password.getText().toString();

        if (!validate(user, pass)) {
            username.setError("Please Enter a Username and Password");
        } else {
            User newUser = new User(0, user, pass, 0, 0);
            db.addUser(newUser);
            finish();
        }
    }
}
