package com.example.nikolauron.workinonit.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Niko Lauron on 11/27/2017.
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

    public static final String PROJECT_TABLE_NAME = "projects";
    public static final String PROJECT_COLUMN_ID = "id";
    public static final String PROJECT_COLUMN_PROJECT = "project";
    public static final String PROJECT_COLUMN_DESCRIPTION = "description";
    public static final String PROJECT_COLUMN_TASKS = "tasks";

    public static final String TASK_TABLE_NAME = "tasks";
    public static final String TASK_COLUMN_ID = "id";
    public static final String TASK_COLUMN_TASK = "task";
    public static final String TASK_COLUMN_NOTES = "notes";
    public static final String TASK_COLUMN_USERID = "userId";
    public static final String TASK_COLUMN_PROJECTID = "projectId";
    public static final String TASK_COLUMN_CREATOR = "creator";
    public static final String TASK_COLUMN_COMPLETE = "complete";

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
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "username TEXT, " +
                "password TEXT, " +
                "projects INTEGER, " +
                "tasks INTEGER" +
                ")";
        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE " + PROJECT_TABLE_NAME +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "project TEXT, " +
                "description TEXT, " +
                "tasks INTEGER " +
                ")";
        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE " + TASK_TABLE_NAME +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "task TEXT, " +
                "notes TEXT, " +
                "userId INTEGER, " +
                "projectId INTEGER, " +
                "creator TEXT, " +
                "complete INTEGER" +
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
        db.insert(USER_TABLE_NAME, null, values);
    }

    public void addProject(Project p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROJECT_COLUMN_PROJECT, p.getProject());
        values.put(PROJECT_COLUMN_DESCRIPTION, p.getDescription());
        values.put(PROJECT_COLUMN_TASKS, p.getTasks());
        db.insert(PROJECT_TABLE_NAME, null, values);
    }

    public void addTask(Task t) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TASK_COLUMN_TASK, t.getTask());
        values.put(TASK_COLUMN_NOTES, t.getNotes());
        values.put(TASK_COLUMN_USERID, t.getUserId());
        values.put(TASK_COLUMN_PROJECTID, t.getProjectId());
        values.put(TASK_COLUMN_CREATOR, t.getCreator());
        values.put(TASK_COLUMN_COMPLETE, t.getComplete());
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
                        cursor.getInt(4)
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
                        cursor.getString(2),
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
        String selectQuery = "SELECT * From " + TASK_TABLE_NAME + " ORDER BY ID ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Task temp = new Task (
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getInt(6)
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
                new String[] {USER_COLUMN_ID, USER_COLUMN_USERNAME, USER_COLUMN_PASSWORD, USER_COLUMN_PROJECTS, USER_COLUMN_TASKS},
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
                cursor.getInt(4)
        );
    }

    public Project getProject(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(PROJECT_TABLE_NAME,
                new String[] {PROJECT_COLUMN_ID, PROJECT_COLUMN_PROJECT, PROJECT_COLUMN_DESCRIPTION, PROJECT_COLUMN_TASKS},
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
                cursor.getString(2),
                cursor.getInt(3)
        );
    }

    public Task getTask(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TASK_TABLE_NAME,
                new String[] {TASK_COLUMN_ID, TASK_COLUMN_TASK, TASK_COLUMN_NOTES, TASK_COLUMN_USERID, TASK_COLUMN_PROJECTID, TASK_COLUMN_CREATOR, TASK_COLUMN_COMPLETE},
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
                cursor.getInt(3),
                cursor.getInt(4),
                cursor.getString(5),
                cursor.getInt(6)
        );
    }

    public void updateUser(String id, User newUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_COLUMN_USERNAME, newUser.getUsername());
        values.put(USER_COLUMN_PASSWORD, newUser.getPassword());
        values.put(USER_COLUMN_PROJECTS, newUser.getProjects());
        values.put(USER_COLUMN_TASKS, newUser.getTasks());

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
        values.put(PROJECT_COLUMN_DESCRIPTION, newProject.getDescription());
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
        values.put(TASK_COLUMN_NOTES, newTask.getNotes());
        values.put(TASK_COLUMN_USERID, newTask.getUserId());
        values.put(TASK_COLUMN_PROJECTID, newTask.getProjectId());
        values.put(TASK_COLUMN_CREATOR, newTask.getCreator());
        values.put(TASK_COLUMN_COMPLETE, newTask.getComplete());

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
