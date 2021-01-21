package com.example.afifah;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataKamus
{
    int id;
    String ENG, SBW, IND;

    //konstruktor kosong
    public DataKamus(){}

    //konstruktor bermodel
    public  DataKamus( int id, String ENG, String SBW, String IND)
    {
        this.id = id;
        this.ENG = ENG;
        this.SBW = SBW;
        this.IND = IND;
    }

    //buat setter

    public void setId(int id) { this.id = id; }

    public void setENG(String ENG) { this.ENG = ENG; }

    public void setSBW(String SBW) { this.SBW = SBW; }

    public void setIND(String IND) { this.IND = IND; }


    //buat getter
    public int getId() { return id; }

    public String getENG() { return ENG; }

    public String getSBW() { return SBW; }

    public String getIND() { return IND; }
}
