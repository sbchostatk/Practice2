package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE mytable ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "price integer,"
                + "count integer);");

        db.execSQL("INSERT INTO mytable(name, price, count) VALUES(\'Одеяло\', 1000, 5)");
        db.execSQL("INSERT INTO mytable(name, price, count) VALUES(\'Подушка\', 350, 8)");
        db.execSQL("INSERT INTO mytable(name, price, count) VALUES(\'Плед\', 2100, 9)");
        db.execSQL("INSERT INTO mytable(name, price, count) VALUES(\'Покрывало\', 970, 15)");
        db.execSQL("INSERT INTO mytable(name, price, count) VALUES(\'Наволочка\', 230, 4)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public int getItemsCount() {
        String countQuery = "SELECT * FROM mytable";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }
}
