package com.heymeowcat.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

public class Add extends AppCompatActivity {

    EditText edittitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edittitle = findViewById(R.id.edittitle);
        edittitle.requestFocus();
    }

    public void onBackPressed() {
        if (!edittitle.getText().toString().isEmpty()) {

        } else {
            super.onBackPressed();
        }
    }

    public void addtask(View view) {
        String uniqueID = UUID.randomUUID().toString();
        String tasktitlestring = edittitle.getText().toString();
        SQLiteDatabase mydb = this.openOrCreateDatabase("Tasks", MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS tasktable(id VARCHAR,title VARCHAR)");
        if (!tasktitlestring.isEmpty()) {
            mydb.execSQL("INSERT INTO tasktable(id,title) VALUES('" + uniqueID + "','" + tasktitlestring + "')");
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
