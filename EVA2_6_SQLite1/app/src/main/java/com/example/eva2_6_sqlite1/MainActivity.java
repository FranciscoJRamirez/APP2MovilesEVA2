package com.example.eva2_6_sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtMsg = findViewById(R.id.txtMsg);

        database = this.openOrCreateDatabase("myDB",MODE_PRIVATE,null);
        database.beginTransaction();
        try {

            database.setTransactionSuccessful();

            database.execSQL("create table if not exists tbFriends (" +
                    "id integer PRIMARY KEY autoincrement, " +
                    "name text, " +
                    "lastname text,"+
                    "age integer);");


            database.execSQL("insert into tbFriends(name, lastname, age) values ('CESAR','SAENZ', '22');");
            database.execSQL("insert into tbFriends(name, lastname, age) values ('PONCHO','RODRIGUEZ', '22');");
            database.execSQL("insert into tbFriends(name, lastname, age) values ('ALE','RUELAS', '22');");
            database.execSQL("insert into tbFriends(name, lastname, age) values ('CARLOS','RODRIGUEZ', '22');");

            txtMsg.append("\nAll done!");
        } catch (SQLException e){
            txtMsg.append("\nError "+e.getMessage());

        } finally {
            database.endTransaction();
        }

    }


}
