package ca.bcit.cartoonapp;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import ca.bcit.cartoonapp.database.CartoonHelper;

/**
 * Created by Niko Lauron on 7/4/2017.
 */

public class CartoonProvider extends ContentProvider {

    private static final UriMatcher uriMatcher;
    private static final int NAMES_URI = 1;
    private CartoonHelper cartoonHelper;
    public static final Uri CONTENT_URI;

    static  {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("cartoonapi.azurewebsites.net", "/api/cartoon", NAMES_URI);
    }

    static {
        CONTENT_URI = Uri.parse("content://cartoonapi.azurewebsites.net/api/cartoon");
    }

    @Override
    public boolean onCreate() {
        cartoonHelper = CartoonHelper.getInstance(getContext());

        return true;
    }

    @Override
    public Cursor query(final Uri uri, final String[] projection,
                        final String selection, final String[] selectionArgs, final String sortOrder) {

        final Cursor cursor;

        switch (uriMatcher.match(uri)) {
            case NAMES_URI:
            {
                final SQLiteDatabase db;
                cartoonHelper.openDatabaseForReading(getContext());
                cursor = cartoonHelper.getCartoonMarker();
                cartoonHelper.close();
                break;
            }
            default:
            {
                throw new IllegalArgumentException("Unsupported URI: " + uri);
            }
        }

        return (cursor);
    }

    @Override
    public String getType(final Uri uri) {
        final String type;

        switch(uriMatcher.match(uri)) {
            case NAMES_URI:
                type = "vnd.android.cursor.dir/vnd.cartoonapi.azurewebsites.net/api/cartoon";
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        return (type);

    }

    @Override
    public int delete(final Uri uri,
                      final String selection,
                      final String[] selectionArgs)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(final Uri uri,
                      final ContentValues values)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(final Uri uri,
                      final ContentValues values,
                      final String selection,
                      final String[]      selectionArgs)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}