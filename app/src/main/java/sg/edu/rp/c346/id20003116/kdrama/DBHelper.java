package sg.edu.rp.c346.id20003116.kdrama;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "kdrama.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_KDRAMA = "kdrama";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESC = "description";
    private static final String COLUMN_CAST = "casting";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE Song
        // (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT,
        // singers TEXT, stars INTEGER, year INTEGER );
        String createKDramaTableSql = "CREATE TABLE " + TABLE_KDRAMA + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DESC + " TEXT, "
                + COLUMN_CAST + " TEXT, "
                + COLUMN_YEAR + " INTEGER, "
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createKDramaTableSql);
        Log.i("info", createKDramaTableSql + "\ncreated tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KDRAMA);
        onCreate(db);
    }

    public long insertKDrama(String name, String description, String cast, int year, int stars) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESC, description);
        values.put(COLUMN_CAST, cast);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, stars);
        // Insert the row into the TABLE_KDRAMA
        long result = db.insert(TABLE_KDRAMA, null, values);
        // Close the database connection
        db.close();
        Log.d("SQL Insert","" + result);
        return result;
    }

    public ArrayList<KDrama> getAllKDrama() {
        ArrayList<KDrama> kdramaList = new ArrayList<KDrama>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_NAME + ", " + COLUMN_DESC + ", "
                + COLUMN_CAST + ", " + COLUMN_YEAR + ", "
                + COLUMN_STARS + " FROM " + TABLE_KDRAMA;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String cast = cursor.getString(3);
                int year = cursor.getInt(4);
                int stars = cursor.getInt(5);

                KDrama kdrama = new KDrama(id, name, description, cast, year, stars);
                kdramaList.add(kdrama);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return kdramaList;
    }

    public ArrayList<KDrama> getAllKDramaByStars(int starsFilter) {
        ArrayList<KDrama> kdramaList = new ArrayList<KDrama>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_NAME, COLUMN_DESC, COLUMN_CAST, COLUMN_YEAR, COLUMN_STARS};
        String condition = COLUMN_STARS + ">= ?";
        String[] args = {String.valueOf(starsFilter)};

        Cursor cursor;
        cursor = db.query(TABLE_KDRAMA, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String desc = cursor.getString(2);
                String cast = cursor.getString(3);
                int year = cursor.getInt(4);
                int stars = cursor.getInt(5);

                KDrama newIsland = new KDrama(id, name, desc, cast, year, stars);
                kdramaList.add(newIsland);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return kdramaList;
    }

    public int updateKDrama(KDrama data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, data.getId());
        values.put(COLUMN_DESC, data.getDesc());
        values.put(COLUMN_CAST, data.getCast());
        values.put(COLUMN_YEAR, data.getYearReleased());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_KDRAMA, values, condition, args);
        db.close();
        return result;
    }


    public int deleteKDrama(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_KDRAMA, condition, args);
        db.close();
        return result;
    }

    public ArrayList<String> getYears() {
        ArrayList<String> codes = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_YEAR};

        Cursor cursor;
        cursor = db.query(true, TABLE_KDRAMA, columns, null, null, null, null, null, null);
        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                codes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return codes;
    }

    public ArrayList<KDrama> getAllKDramaByYear(int yearFilter) {
        ArrayList<KDrama> kdramaList = new ArrayList<KDrama>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_NAME, COLUMN_DESC, COLUMN_CAST, COLUMN_YEAR, COLUMN_STARS};
        String condition = COLUMN_YEAR + "= ?";
        String[] args = {String.valueOf(yearFilter)};

        Cursor cursor;
        cursor = db.query(TABLE_KDRAMA, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String desc = cursor.getString(2);
                String cast = cursor.getString(3);
                int year = cursor.getInt(4);
                int stars = cursor.getInt(5);

                KDrama newKDrama = new KDrama(id, name, desc, cast, year, stars);
                kdramaList.add(newKDrama);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return kdramaList;
    }
}
