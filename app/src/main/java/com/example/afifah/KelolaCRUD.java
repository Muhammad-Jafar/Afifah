package com.example.afifah;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class KelolaCRUD extends SQLiteOpenHelper
{
    // static variable
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "DAfifah";

    // table name
    private static final String TABLE_NAME = "data";

    private SQLiteDatabase Dafifah;

    // column tables
    private static final String KEY_ID = "id";
    private static final String ENG = "eng";
    private static final String SBW = "sbw";
    private static final String IND = "ind";

    //konstruktor kosong
    public KelolaCRUD(Context context){ super(context, DATABASE_NAME, null, 1); }

    //method buat tabel
    public void createTable (SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS data");
        db.execSQL("CREATE TABLE if not exists data(id INTEGER PRIMARY KEY AUTOINCREMENT, ENG TEXT, SBW TEXT, IND TEXT)");
    }

    //de tabel
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String DAfifah = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + ENG + "TEXT"
                + SBW + "TEXT" + IND + "TEXT" + ")";
        db.execSQL(DAfifah);
    }

    //validasi tabel
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(Dafifah);
    }

    public void generateData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID, "1");
        cv.put(ENG, "eat");
        cv.put(IND, "lari");
        cv.put(SBW, "mangan");
        db.insert(TABLE_NAME, ENG, cv);

        cv.put(KEY_ID, "2");
        cv.put(ENG, "who");
        cv.put(IND, "siapa");
        cv.put(SBW, "sai");
        db.insert( TABLE_NAME, ENG, cv);

        cv.put(KEY_ID, "3");
        cv.put(ENG, "tomorrow");
        cv.put(SBW, "nawar");
        cv.put(IND, "besok");
        db.insert(TABLE_NAME, ENG, cv);

//        cv.put(INGGRIS, "read");
//        cv.put(INDONESIA, "membaca");
//        cv.put(JAWA, "maca");
//        db.insert("kamus", INGGRIS, cv);
//        cv.put(INGGRIS, "buy");
//        cv.put(INDONESIA, "membeli");
//        cv.put(JAWA, "tuku");
//        db.insert("kamus", INGGRIS, cv);
//        cv.put(INGGRIS, "green");
//        cv.put(INDONESIA, "hijau");
//        cv.put(JAWA, "ijo");
//        db.insert("kamus", INGGRIS, cv);
//        cv.put(INGGRIS, "late");
//        cv.put(INDONESIA, "terlambat");
//        cv.put(JAWA, "telat");
//        db.insert("kamus", INGGRIS, cv);
    }
}
