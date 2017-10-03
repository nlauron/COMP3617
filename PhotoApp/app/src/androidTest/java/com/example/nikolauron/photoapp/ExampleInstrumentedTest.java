package com.example.nikolauron.photoapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.nikolauron.photoapp.Classes.DBHelper;
import com.example.nikolauron.photoapp.Classes.Photo;

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

        assertEquals("com.example.nikolauron.photoapp", appContext.getPackageName());
    }

    private DBHelper db;

    @Before
    public void setUp() {
        db = DBHelper.getInstance(InstrumentationRegistry.getTargetContext());
        for (int i = 1; i < 8; i++) {
            Photo p = new Photo(i, "A Photo", "1-" + i + "-2020");
            db.addPhoto(p);
        }
    }

    @After
    public void finish() {
        ArrayList<Photo> photos = db.getAllPhotos();
        for (int i = 0; i < photos.size(); i++) {
            db.removePhoto(photos.get(i).getId().toString());
        }
        db.close();
    }


}
