package ca.bcit.cartoonapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;

public class ImageActivity
    extends AppCompatActivity
{
    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        final Intent    intent;
        final String    name;
        final String    imageURL;
        final ImageView imageView;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        intent    = getIntent();
        name      = intent.getStringExtra("name");
        imageURL  = intent.getStringExtra("url");
        System.out.println(name + " -> " + imageURL);

        imageView = (ImageView)findViewById(R.id.imageView);
        Ion.with(imageView)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
//                .animateLoad(spinAnimation)
//                .animateIn(fadeInAnimation)
                .load(imageURL);
    }
}
