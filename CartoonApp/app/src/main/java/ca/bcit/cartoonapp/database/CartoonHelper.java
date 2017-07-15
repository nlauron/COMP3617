package ca.bcit.cartoonapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

import java.util.List;

import ca.bcit.cartoonapp.database.schema.Cartoon;
import ca.bcit.cartoonapp.database.schema.DaoMaster;
import ca.bcit.cartoonapp.database.schema.DaoSession;
import ca.bcit.cartoonapp.database.schema.CartoonDao;

/**
 * Created by Niko Lauron on 7/4/2017.
 */

public final class CartoonHelper {
        private static CartoonHelper           instance;
        private SQLiteDatabase                 db;
        private DaoMaster daoMaster;
        private DaoSession daoSession;
        private CartoonDao cartoonDao;
        private DaoMaster.DevOpenHelper        helper;

        private CartoonHelper(final Context context)
        {
            openDatabaseForWriting(context);
        }

        public synchronized static CartoonHelper getInstance(final Context context)
        {
            if(instance == null)
            {
                instance = new CartoonHelper(context);
            }

            return (instance);
        }

        public static CartoonHelper getInstance()
        {
            if(instance == null)
            {
                throw new Error();
            }

            return (instance);
        }

        private void openDatabase()
        {
            daoMaster  = new DaoMaster (db);
            daoSession = daoMaster.newSession ();
            cartoonDao = daoSession.getCartoonDao ();
        }

        public void openDatabaseForWriting(final Context context) {
            helper = new DaoMaster.DevOpenHelper(context, "Defiblocator.db", null);
            db     = helper.getWritableDatabase();
            openDatabase();
        }

        public void openDatabaseForReading(final Context context) {
            final DaoMaster.DevOpenHelper helper;

            helper = new DaoMaster.DevOpenHelper(context,
                    "Defiblocator.db",
                    null);
            db = helper.getReadableDatabase();
            openDatabase();
        }

        public void close()
        {
            helper.close();
        }

        public Cartoon createCartoon (final String name, final String cartoonuri) {
            final Cartoon data;

            data = new Cartoon (null, name, cartoonuri);

            cartoonDao.insertOrReplace (data);

            return data;
        }

        public void createCartoon (final Cartoon m) {
            cartoonDao.insertOrReplace (m);
        }

        public Cartoon getCartoonFromCursor (final Cursor cursor) {
            return cartoonDao.readEntity(cursor, 0);
        }

        public List<Cartoon> getCartoons ()
        {
            return (cartoonDao.loadAll());
        }

        public Cursor getCartoonMarker () {
            final Cursor cursor;

            cursor = db.query(cartoonDao.getTablename(),
                    cartoonDao.getAllColumns(),
                    null,
                    null,
                    null,
                    null,
                    null);

            return (cursor);
        }
        public static void upgrade(final Database db,
                                   final int      oldVersion,
                                   final int      newVersion)
        {
        }

        public long getNumberOfCartoons () {
            return (cartoonDao.count ());
        }
}
