package com.heymeowcat.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Delete extends AppCompatActivity {

    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        TextView textView = findViewById(R.id.textfromdel);
        String value = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            value = Objects.requireNonNull(getIntent().getExtras()).getString("value");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            id = Objects.requireNonNull(getIntent().getExtras()).getString("id");
        }
        textView.setText(value);
    }

    public void deletethisid(View view) {
        SQLiteDatabase mydb = this.openOrCreateDatabase("Tasks", MODE_PRIVATE, null);
        mydb.execSQL("DELETE FROM tasktable WHERE id ='" + id + "'");
        Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show();
        finish();
    }
}
