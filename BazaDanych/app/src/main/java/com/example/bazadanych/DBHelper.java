package com.example.bazadanych;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Cars.db", null, 1);
    }

    //podstawowe implementacje klasy SQLiteOpenHelper
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Cars(Make TEXT primary key, Model TEXT, Year TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Cars");
    }

    //obsluzenie dodania samochodu do bazy danych
    public Boolean insertCar(String make, String model, String year)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("make", make);
        contentValues.put("model", model);
        contentValues.put("year", year);
        long result=DB.insert("Cars", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    //obsluzenie zmiany detali o samochodzie, ktory istnieje w bazie danych
    public Boolean updateCar(String make, String model, String year) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("model", model);
        contentValues.put("year", year);
        Cursor cursor = DB.rawQuery("Select * from Cars where make = ?", new String[]{make});
        if (cursor.getCount() > 0) {
            long result = DB.update("Cars", contentValues, "make=?", new String[]{make});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}

    //obsluzenie usuniecia samochodu, ktory istnieje w bazie danych
    public Boolean deleteCar(String make)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Cars where make = ?", new String[]{make});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Cars", "make=?", new String[]{make});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    //obsluzenie pobrania wszystkich elementow z bazy danych
    public Cursor getData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Cars", null);
        return cursor;
    }
}