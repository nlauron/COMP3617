package ca.bcit.cartoonapp;

import android.app.ListActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity
    extends ListActivity
{

    private static final String TAG = MainActivity.class.getName();
    private CartoonHelper cartoonHelper;
    private ListView lv;
    private ArrayAdapter adapter;
    private ArrayList names;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = cartoonHelper.getNames(db);
        cartoonHelper = CartoonHelper.getInstance(getApplicationContext());
        lv = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<>(this, R.layout.activity_main, names);

    }

    @Override
    protected void onListItemClick(ListView list, View view, int position, long id)
    {
    }
}
