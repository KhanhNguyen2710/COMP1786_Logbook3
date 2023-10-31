package com.example.lookbook_3;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sqlite_example";
    // A set of constants to store the column and table names
    private static final String TABLE_NAME = "persons";
    public static final String ID_COLUMN = "person_id";
    public static final String NAME_COLUMN = "name";
    public static final String DOB_COLUMN = "dob";
    public static final String EMAIL_COLUMN = "email";
    private static final String IMAGE_COLUMN = "image";



    private SQLiteDatabase database;

    private static final String TABLE_CREATE = String.format(

            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT)",
            TABLE_NAME, ID_COLUMN, NAME_COLUMN, DOB_COLUMN, EMAIL_COLUMN, IMAGE_COLUMN
    );

    // The constructor makes a call to the method in the super class, passing the database name
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = getWritableDatabase();
    }

    // Overriding the onCreate() method which generates the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }
    // This method upgrades the database if the version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        Log.v(this.getClass().getName(), TABLE_NAME +
                "database upgrade to version" + newVersion + " - old data lost"
        );
        onCreate(db);
    }

    // Returns the automatically generated primary key
    public long insertDetails(String name, String dob, String email, String imagePath) {
        ContentValues rowValues = new ContentValues();
        rowValues.put(NAME_COLUMN, name);
        rowValues.put(DOB_COLUMN, dob);
        rowValues.put(EMAIL_COLUMN, email);
        rowValues.put(IMAGE_COLUMN, imagePath);  // Lưu đường dẫn hoặc tên tệp ảnh

        return database.insertOrThrow(
                TABLE_NAME,
                // nullColumnHack specifies a column that will be set to null if the ContentValues argument contains no data
                null,
                rowValues
        );
    }


    public String getDetails() {
        Cursor results = database.query(TABLE_NAME,
                // Defines the query to execute
                new String[]{ID_COLUMN, NAME_COLUMN, DOB_COLUMN, EMAIL_COLUMN,IMAGE_COLUMN},
                null, null, null, null, NAME_COLUMN
        );
        String resultText = "";
        results.moveToFirst();
        while (!results.isAfterLast()) {
            int id = results.getInt(0);
            String name = results.getString(1);
            String dob = results.getString(2);
            String email = results.getString(3);
            String imagePath = results.getString(4);  // Lấy đường dẫn hoặc tên tệp ảnh

            resultText += id + " " + name + " " + dob + " " + email + "" + imagePath + "\n";
            results.moveToNext();
        }
        return resultText;
    }
}