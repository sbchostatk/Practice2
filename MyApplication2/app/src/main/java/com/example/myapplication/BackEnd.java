package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BackEnd extends AppCompatActivity {

    ViewPager2 pager;
    RecyclerView.Adapter pagerAdapter;
    DBHelper dbHelper;
    Button button_sf;
    Button add;

    SQLiteDatabase db;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_end);

        dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();
        c = db.query("mytable", null, null, null, null, null, null);

        pager = findViewById(R.id.pager);
        pagerAdapter = new BackPagerAdapter(c, dbHelper);
        pager.setAdapter(pagerAdapter);

        button_sf = (Button) findViewById(R.id.button);
        button_sf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BackEnd.this, MainActivity.class);
                startActivity(intent);
            }
        });

        add = (Button) findViewById((R.id.button4));
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SQLiteDatabase d = dbHelper.getWritableDatabase();
               d.execSQL("INSERT INTO mytable(name, price, count) VALUES(\'Название\', 0, 0)");

                c = d.query("mytable", null, null, null, null, null, null);

                pager = findViewById(R.id.pager);
                pagerAdapter = new BackPagerAdapter(c, dbHelper);
                pager.setAdapter(pagerAdapter);

               int pages_count = dbHelper.getItemsCount();
               pager.setCurrentItem(pages_count);
            }
        });
    }
}
