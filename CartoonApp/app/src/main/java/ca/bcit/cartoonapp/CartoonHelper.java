package ca.bcit.cartoonapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niko Lauron on 7/4/2017.
 */

public final class CartoonHelper extends SQLiteOpenHelper {

    private static final String TAG = CartoonHelper.class.getName();
    private static final int SCHEMA_VERSION = 1;
    private static final String DB_Name = "cartoon.db";
    private static final String NAME_TABLE_NAME = "cartoon";
    private static final String ID_COLUMN_NAME = "_id";
    public static final String NAME_COLUMN_NAME = "name";
    public static final String PICTURE_COLUMN_PICTURE = "pictureUrl";
    private static CartoonHelper instance;

    private CartoonHelper(final Context ctx) {
        super(ctx, DB_Name, null, SCHEMA_VERSION);
    }

    public synchronized  static CartoonHelper getInstance(final Context ctx) {
        if(instance == null) {
            instance = new CartoonHelper(ctx.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onConfigure(final SQLiteDatabase db) {
        super.onConfigure(db);

        setWriteAheadLoggingEnabled(true);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        final String CREATE_NAME_TABLE;

        CREATE_NAME_TABLE = "CREATE TABLE IF NOT EXISTS " + NAME_TABLE_NAME + " ( " +
                ID_COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME_COLUMN_NAME + " TEXT NOT NULL, " +
                PICTURE_COLUMN_PICTURE + "TEXT NOT NULL)";
        db.execSQL(CREATE_NAME_TABLE);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {

    }

    public long getNumberofCharacters(final SQLiteDatabase db) {
        final long numEntries;

        numEntries = DatabaseUtils.queryNumEntries(db, NAME_TABLE_NAME);

        return (numEntries);
    }

    public void insertCharacter(final SQLiteDatabase db, final String name, final String pictureUrl) {
        final ContentValues contentValues;

        contentValues = new ContentValues();
        contentValues.put(NAME_COLUMN_NAME, name);
        contentValues.put(PICTURE_COLUMN_PICTURE, pictureUrl);
        db.insert(NAME_TABLE_NAME, null, contentValues);
    }

    public int deleteCharacter(final SQLiteDatabase db, final String name, final String pictureUrl) {
        final int rows;

        rows = db.delete(NAME_TABLE_NAME, NAME_COLUMN_NAME + " = ? AND " + PICTURE_COLUMN_PICTURE + " = ?", new String[] {
            name,
            pictureUrl,
        });

        return (rows);
    }

    public ArrayList getNames(SQLiteDatabase db) {
        ArrayList names = new ArrayList();
        String query = "SELECT * FROM " + NAME_TABLE_NAME;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndex(NAME_COLUMN_NAME));
                names.add(name);
                cursor.moveToNext();
            }
        }
        return names;
    }

    public Cursor getAllCharacters(final Context ctx, final SQLiteDatabase db) {
        final Cursor cursor;

        cursor = db.query(NAME_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        cursor.setNotificationUri(ctx.getContentResolver(), CartoonProvider.CONTENT_URI);

        return (cursor);
    }
}
