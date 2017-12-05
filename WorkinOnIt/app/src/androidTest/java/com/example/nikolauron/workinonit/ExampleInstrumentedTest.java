package com.example.nikolauron.workinonit;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.nikolauron.workinonit.Classes.DBHelper;
import com.example.nikolauron.workinonit.Classes.Project;
import com.example.nikolauron.workinonit.Classes.Task;
import com.example.nikolauron.workinonit.Classes.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.nikolauron.workinonit", appContext.getPackageName());
    }

    private DBHelper db;

    @Before
    public void setUp() {
        db = DBHelper.getInstance(InstrumentationRegistry.getTargetContext());
        for (int i = 0; i < 4; i++) {
            User testUsers = new User(i, "User" + i, "abc", 0, 0);
            db.addUser(testUsers);
        }

        for (int i = 0; i < 4; i++) {
            Project testProjects = new Project(1, "Test Project " + i, "Test Project" + i, 0);
            db.addProject(testProjects);
        }

        for (int i = 0; i < 4; i++) {
            Task testTasks = new Task(1, "Test Task " + i, "This is task " + i, 1, 1, "Admin", 1);
            db.addTask(testTasks);
        }


        ArrayList<User> users = db.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            System.out.print("This is user " + i);
        }
    }

    @After
    public void finish() {
        ArrayList<User> users = db.getAllUsers();
        ArrayList<Project> projects = db.getAllProjects();
        ArrayList<Task> tasks = db.getAllTasks();

        for (int i = 0; i < users.size(); i++) {
            db.removeUser(String.valueOf(users.get(i).getId()));
        }

        for (int i = 0; i < projects.size(); i++) {
            db.removeProject(String.valueOf(projects.get(i).getId()));
        }

        for (int i = 0; i < tasks.size(); i++) {
            db.removeTask(String.valueOf(tasks.get(i).getId()));
        }
        db.close();
    }


    @Test
    public void db_is_working() {
        ArrayList<User> users = db.getAllUsers();
        ArrayList<Project> projects = db.getAllProjects();

        assertEquals(users.size(), 4);
        assertEquals(projects.size(), 4);
    }

    @Test
    public void get_is_working() {
        ArrayList<User> users = db.getAllUsers();
        String user = users.get(1).getUsername();
        assertEquals(user, "User1");
    }

    @Test
    public void create_is_working() {
        String user = "TestUser";
        String pass = "123";

        ArrayList<User> users = db.getAllUsers();
        assertEquals(users.size(), 4);

        User test = new User(5, user, pass, 0, 0);
        db.addUser(test);
        db.addUser(test);
        db.addUser(test);
        users = db.getAllUsers();
        assertEquals(users.size(), 7);
        assertEquals(users.get(6).getUsername(), user);
    }
}
