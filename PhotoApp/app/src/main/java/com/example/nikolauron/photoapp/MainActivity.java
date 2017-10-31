package com.example.nikolauron.photoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.nikolauron.photoapp.Classes.Cell;
import com.example.nikolauron.photoapp.Classes.DBHelper;
import com.example.nikolauron.photoapp.Classes.MyAdapter;
import com.example.nikolauron.photoapp.Classes.Photo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DBHelper db;
    private final String image_titles[] = {
            "Img1",
            "Img2",
            "Img3",
            "Img4",
            "Img5",
            "Img6",
            "Img7",
            "Img8"
    };

    private final Integer image_ids[] = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8
    };

    private final Photo images[] = {

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);

        for (int i = 1; i < 8; i++) {
            Photo p = new Photo(image_ids[i], image_titles[i], "1-" + i + "-2020");
            db.addPhoto(p);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.gallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Cell> cells = prepareData();
        MyAdapter adapter = new MyAdapter(getApplicationContext(), cells);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Cell> prepareData() {
        ArrayList<Photo> photos = db.getAllPhotos();
        ArrayList<Cell> theimage = new ArrayList<>();
        for (int i = 0; i < photos.size(); i++) {
            Cell cell = new Cell();
            cell.setTitle(image_titles[i]);
            cell.setImg(image_ids[i]);
            theimage.add(cell);
        }
        return theimage;
    }

    public void filter(View view) {
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
    }

    public void camera(View view) {
        Intent i = new Intent(this, CameraActivity.class);
        startActivity(i);
    }
}
