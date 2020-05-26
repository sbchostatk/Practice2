package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyPagerAdapter extends CursorRecyclerAdapter<PagerVH> {
    DBHelper dbHelper;

    public MyPagerAdapter(Cursor c, DBHelper dbHelper) {
        super(c);
        this.dbHelper = dbHelper;
    }

    public PagerVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page, parent, false);
        return new PagerVH(itemView);
    }

    public void onBindViewHolder(final PagerVH holder, final Cursor c) {
        holder.textView.setText(c.getString(1) + "\nСтоимость: " + c.getInt(2) + "\nКоличество: " + c.getInt(3));

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (c.getInt(3) > 1) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.execSQL("UPDATE mytable SET count = ? WHERE id = ?", new String[]{Integer.toString(c.getInt(2) - 1), Long.toString(c.getLong(0))});
                        holder.textView.setText(c.getString(1) + "\nСтоимость: " + c.getInt(2) + "\nКоличество: " + c.getInt(3));

                        Cursor c = db.query("mytable", null, null, null, null, null, null);
                        swapCursor(c);
                    }
                }
            });
    }
}

class PagerVH extends RecyclerView.ViewHolder {
    TextView textView;
    Button button;

    public PagerVH(View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.textView);
        button = itemView.findViewById(R.id.button);
    }
}
