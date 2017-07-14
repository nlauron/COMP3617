package com.example.nikolauron.workinprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProjectsActivity extends AppCompatActivity {

    String[] test = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.listview, test);

        ListView listView = (ListView) findViewById(R.id.projectList);
        listView.setAdapter(adapter);
    }
}
