package com.fiek.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Databaza extends SQLiteOpenHelper {

    public static final String Perdoruesit = "Perdoruesit";
    public Databaza(@Nullable Context context)
    {
        super(context, "FIEKDB", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String strQuery = "CREATE TABLE "+Perdoruesit+" ("+
                Perdoruesi.ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Perdoruesi.Emri+" TEXT NOT NULL)";
                       
        db.execSQL(strQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
