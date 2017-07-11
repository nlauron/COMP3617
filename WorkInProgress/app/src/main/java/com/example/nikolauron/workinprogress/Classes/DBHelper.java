package com.example.nikolauron.workinprogress.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

import com.example.nikolauron.workinprogress.Classes.Task;
import com.example.nikolauron.workinprogress.Classes.User;

import java.util.ArrayList;

/**
 * Created by Niko Lauron on 7/7/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WIPDB.db";

    public static final String USER_TABLE_NAME = "users";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_USERNAME = "username";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_PROJECTS = "projects";
    public static final String USER_COLUMN_TASKS = "tasks";
    public static final String USER_COLUMN_ROLE =  "role";

    public static final String PROJECT_TABLE_NAME = "projects";
    public static final String PROJECT_COLUMN_ID = "id";
    public static final String PROJECT_COLUMN_PROJECT = "project";
    public static final String PROJECT_COLUMN_USERS = "users";
    public static final String PROJECT_COLUMN_TASKS = "tasks";

    public static final String TASK_TABLE_NAME = "tasks";
    public static final String TASK_COLUMN_ID = "id";
    public static final String TASK_COLUMN_TASK = "task";
    public static final String TASK_COLUMN_USER = "user";
    public static final String TASK_COLUMN_CREATOR = "creator";

    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //this.onUpgrade(this.getWritableDatabase(), 1, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Creating table");
        String CREATE_TABLE;

        CREATE_TABLE = "CREATE TABLE " + USER_TABLE_NAME +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, " +
                "password TEXT, " +
                "projects INTEGER, " +
                "tasks INTEGER, " +
                "role TEXT" +
                ")";
        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE " + PROJECT_TABLE_NAME +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "project TEXT, " +
                "users INTEGER, " +
                "tasks INTEGER, " +
                ")";
        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE " + TASK_TABLE_NAME +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "task TEXT, " +
                "user TEXT, " +
                "creator TEXT" +
                ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public void addUser(User u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_USERNAME, u.getUsername());
        values.put(USER_COLUMN_PASSWORD, u.getPassword());
        values.put(USER_COLUMN_PROJECTS, u.getProjects());
        values.put(USER_COLUMN_TASKS, u.getTasks());
        values.put(USER_COLUMN_ROLE, u.getRole());
        db.insert(USER_TABLE_NAME, null, values);
    }

    public void addProject(Project p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROJECT_COLUMN_PROJECT, p.getProject());
        values.put(PROJECT_COLUMN_USERS, p.getUsers());
        values.put(PROJECT_COLUMN_TASKS, p.getTasks());
        db.insert(PROJECT_TABLE_NAME, null, values);
    }

    public void addTask(Task t) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TASK_COLUMN_TASK, t.getTask());
        values.put(TASK_COLUMN_USER, t.getUser());
        values.put(TASK_COLUMN_CREATOR, t.getCreator());
        db.insert(TASK_TABLE_NAME, null, values);
    }

    public void removeUser(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USER_TABLE_NAME, USER_COLUMN_ID + " = ?", new String[] {id});
    }

    public void removeProject(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PROJECT_TABLE_NAME, PROJECT_COLUMN_ID + " = ?", new String[] {id});
    }

    public void removeTask(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TASK_TABLE_NAME, TASK_COLUMN_ID + " = ?", new String[] {id});
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String selectQuery = "SELECT * From " + USER_TABLE_NAME + " ORDER BY ID ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User temp = new User (
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getString(5)
                );
                users.add(temp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }

    public ArrayList<Project> getAllProjects() {
        ArrayList<Project> projects = new ArrayList<>();
        String selectQuery = "SELECT * From " + PROJECT_TABLE_NAME + " ORDER BY ID ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Project temp = new Project(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3)
                );
                projects.add(temp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return projects;
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        String selectQuery = "SELECT * From " + USER_TABLE_NAME + " ORDER BY ID ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Task temp = new Task (
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                tasks.add(temp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tasks;
    }

    public User getUser(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(USER_TABLE_NAME,
                new String[] {USER_COLUMN_ID, USER_COLUMN_USERNAME, USER_COLUMN_PASSWORD, USER_COLUMN_PROJECTS, USER_COLUMN_TASKS, USER_COLUMN_ROLE},
                USER_COLUMN_ID + " = ?",
                new String[] {id},
                null,
                null,
                null,
                null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return new User (
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getInt(4),
                cursor.getString(5)
        );
    }

    public Project getProject(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(PROJECT_TABLE_NAME,
                new String[] {PROJECT_COLUMN_ID, PROJECT_COLUMN_PROJECT, PROJECT_COLUMN_USERS, PROJECT_COLUMN_TASKS},
                USER_COLUMN_ID + " = ?",
                new String[] {id},
                null,
                null,
                null,
                null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return new Project(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getInt(3)
        );
    }

    public Task getTask(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TASK_TABLE_NAME,
                new String[] {TASK_COLUMN_ID, TASK_COLUMN_TASK, TASK_COLUMN_USER, TASK_COLUMN_CREATOR},
                TASK_COLUMN_ID + " = ?",
                new String[] {id},
                null,
                null,
                null,
                null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return new Task (
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );
    }

    public void updateUser(String id, User newUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_COLUMN_USERNAME, newUser.getUsername());
        values.put(USER_COLUMN_PASSWORD, newUser.getPassword());
        values.put(USER_COLUMN_PROJECTS, newUser.getProjects());
        values.put(USER_COLUMN_TASKS, newUser.getTasks());
        values.put(USER_COLUMN_ROLE, newUser.getRole());

        this.getWritableDatabase().update(
                USER_TABLE_NAME,
                values,
                USER_COLUMN_ID + " = ?",
                new String[] {id}
        );
    }

    public void updateProject(String id, Project newProject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PROJECT_COLUMN_PROJECT, newProject.getProject());
        values.put(PROJECT_COLUMN_USERS, newProject.getUsers());
        values.put(PROJECT_COLUMN_TASKS, newProject.getTasks());

        this.getWritableDatabase().update(
                PROJECT_TABLE_NAME,
                values,
                PROJECT_COLUMN_ID + " = ?",
                new String[] {id}
        );
    }

    public void updateTask(String id, Task newTask) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TASK_COLUMN_TASK, newTask.getTask());
        values.put(TASK_COLUMN_USER, newTask.getUser());
        values.put(TASK_COLUMN_CREATOR, newTask.getCreator());

        this.getWritableDatabase().update(
                TASK_TABLE_NAME,
                values,
                TASK_COLUMN_ID + " = ?",
                new String[] {id}
        );
    }

    public void dropAllTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS user");
        db.close();
    }
}
