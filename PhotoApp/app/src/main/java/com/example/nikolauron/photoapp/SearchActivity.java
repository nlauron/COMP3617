package com.example.nikolauron.photoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    private EditText startDate;
    private EditText endDate;
    private EditText toplocation;
    private EditText btmlocation;
    private EditText keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        startDate = (EditText)findViewById(R.id.startDate);
        endDate = (EditText)findViewById(R.id.endDate);
        toplocation = (EditText)findViewById(R.id.latlongTL);
        btmlocation = (EditText)findViewById(R.id.latlongBR);
        keyword = (EditText)findViewById(R.id.keyword);


    }
}
