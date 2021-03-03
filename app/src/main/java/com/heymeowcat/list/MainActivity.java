package com.heymeowcat.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnNotelistener {
    private final ArrayList<String> mtitles = new ArrayList<>();
    private final ArrayList<String> mids = new ArrayList<>();

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton btn = findViewById(R.id.addnewbtn);
        recyclerView = findViewById(R.id.recyclerview);
        SQLiteDatabase mydb = this.openOrCreateDatabase("Tasks", MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS tasktable(id VARCHAR,title VARCHAR)");
        Cursor result = mydb.rawQuery("SELECT * FROM tasktable", null);
        int idindex = result.getColumnIndex("id");
        int titleindex = result.getColumnIndex("title");
        if (result.moveToFirst()) {
            do {
                mids.add(result.getString(idindex));
                mtitles.add(result.getString(titleindex));
                initrecyclerview();
            } while (result.moveToNext());
        }
        result.close();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, Add.class);
                startActivity(a);
            }
        });
    }




    private void initrecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mids, mtitles,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void ontaskclick(int position) {
        Intent a = new Intent(MainActivity.this, Delete.class);
        a.putExtra("id",mids.get(position));
        a.putExtra("value",mtitles.get(position));
        startActivity(a);

    }
}
