package com.example.lookbook_3.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lookbook_3.Models.Person;
import com.example.lookbook_3.dao.PersonDao;

@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
}