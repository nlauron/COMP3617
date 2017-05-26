package com.example.a00923196.assignment1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        final ImageView image = (ImageView) findViewById(R.id.picture);
        Intent i = getIntent();
        int colorID = i.getIntExtra("color", 0);

        if (colorID == 0) {
            image.setColorFilter(Color.RED, PorterDuff.Mode.LIGHTEN);
        } else if (colorID == 1) {
            image.setColorFilter(Color.BLUE, PorterDuff.Mode.LIGHTEN);
        } else if (colorID == 2) {
            image.setColorFilter(Color.GREEN, PorterDuff.Mode.LIGHTEN);
        } else if (colorID == 3) {
            image.setColorFilter(Color.YELLOW, PorterDuff.Mode.LIGHTEN);
        } else {
            image.setColorFilter(Color.MAGENTA, PorterDuff.Mode.LIGHTEN);
        }
    }
}
