package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class BackPagerAdapter extends CursorRecyclerAdapter<PagerVH2> {
    DBHelper dbHelper;

    public BackPagerAdapter(Cursor c, DBHelper dbHelper) {
        super(c);
        this.dbHelper = dbHelper;
    }

    public PagerVH2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_back_end, parent, false);
        return new PagerVH2(itemView);
    }

    public void onBindViewHolder(final PagerVH2 holder, final Cursor c) {
        holder.name.setText(c.getString(1));
        holder.price.setText(c.getString(2));
        holder.count.setText(c.getString(3));

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE mytable SET name = ?, price = ?, count = ? WHERE id = ?", new String[]{holder.name.getText().toString(), holder.price.getText().toString(), holder.count.getText().toString(), Long.toString(c.getLong(0))});
                Toast.makeText(v.getContext(), "Данные обновлены", Toast.LENGTH_LONG).show();

                Cursor c = db.query("mytable", null, null, null, null, null, null);
                swapCursor(c);
            }
        });
    }
}

class PagerVH2 extends RecyclerView.ViewHolder {
    EditText name;
    EditText price;
    EditText count;
    Button button;

    public PagerVH2(View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.editText);
        price = itemView.findViewById(R.id.editText2);
        count = itemView.findViewById(R.id.editText3);
        button = itemView.findViewById(R.id.button3);
    }
}
