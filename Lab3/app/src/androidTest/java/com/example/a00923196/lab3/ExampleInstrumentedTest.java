package com.example.a00923196.lab3;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.a00923196.lab3.Classes.mydb.Preferences;

import org.junit.Test;
import org.junit.runner.RunWith;

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

        assertEquals("com.example.a00923196.lab3", appContext.getPackageName());
    }

    @Test
    public void preferences_isWorking() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Preferences db = new Preferences();

        db.saveState(appContext, "Testing");
        assertEquals("Testing", db.getState(appContext));
    }
}
