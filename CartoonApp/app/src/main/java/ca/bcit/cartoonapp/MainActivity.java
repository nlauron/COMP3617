package ca.bcit.cartoonapp;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.bcit.cartoonapp.database.CartoonHelper;
import ca.bcit.cartoonapp.database.schema.Cartoon;

public class MainActivity
    extends ListActivity
{
    private List<String> names;
    private ArrayAdapter<String> namesAdapter;
    private Map<String, String> namesPictures;
    private CartoonHelper db;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        final LoaderManager manager;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namesPictures = new HashMap<>();
        names         = new ArrayList<String>();
        namesAdapter  = new ArrayAdapter<String>(this,
                                                 android.R.layout.simple_list_item_1,
                                                 names);
        setListAdapter(namesAdapter);

        setupDb();

        if (db.getNumberOfCartoons () == 0) {
            Ion.with(this)
                    .load("http://cartoonapi.azurewebsites.net/api/cartoon")
                    .asJsonArray()
                    .setCallback(new FutureCallback<JsonArray>() {
                        @Override
                        public void onCompleted(final Exception ex,
                                                final JsonArray result) {
                            if (ex == null) {
                                for (final JsonElement element : result) {
                                    final JsonObject object;
                                    final String name;
                                    final String pictureUrl;

                                    object = element.getAsJsonObject();
                                    name = object.get("name").getAsString();
                                    pictureUrl = object.get("pictureUrl").getAsString();
                                    System.out.println(name + " -> " + pictureUrl);
                                    db.createCartoon(name, pictureUrl);
                                    names.add(name);
                                    namesPictures.put(name, pictureUrl);
                                }

                                MainActivity.this.namesAdapter.notifyDataSetChanged();
                            } else {
                                ex.printStackTrace();
                            }
                        }
                    });
        } else {
            List<Cartoon> ct = db.getCartoons();
            for (Cartoon c : ct) {
                names.add(c.getName());
                namesPictures.put(c.getName(), c.getPictureuri());
            }
        }
    }

    private void setupDb() {
        db = CartoonHelper.getInstance(getApplicationContext());
        db.openDatabaseForWriting(getApplicationContext());
    }

    private void close () {
        if (db != null)
            db.close();
    }

    @Override
    protected void onListItemClick(final ListView list,
                                   final View view,
                                   final int position,
                                   final long id)
    {
        final String name;
        final String pictureURL;
        final Intent intent;

        name = names.get(position);
        System.out.println(name);
        pictureURL = namesPictures.get(name);
        System.out.println(pictureURL);

        intent = new Intent(this, ImageActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("url", "http://cartoonapi.azurewebsites.net/" + pictureURL);

        startActivity(intent);
    }
}
