package com.example.eva2_9_sqlite4;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtMsg = findViewById(R.id.txtMensaje);
        String SDcardPath =getExternalFilesDir(null)
                .getPath() + "/myDB";

        db = SQLiteDatabase.openDatabase(

                SDcardPath,

                null,

                SQLiteDatabase.CREATE_IF_NECESSARY
        );
        //db = this.openOrCreateDatabase("myDB",MODE_PRIVATE,null);
        db.beginTransaction();
        try {

            db.setTransactionSuccessful();

            db.execSQL("create table if not exists tbFriends (" +
                    "id integer PRIMARY KEY autoincrement, " +
                    "name text, " +
                    "lastname text,"+
                    "age integer);");


            db.execSQL("insert into tbFriends(name, lastname, age) values ('CESAR','SAENZ', '22');");
            db.execSQL("insert into tbFriends(name, lastname, age) values ('PONCHO','RODRIGUEZ', '22');");
            db.execSQL("insert into tbFriends(name, lastname, age) values ('ALE','RUELAS', '22');");
            db.execSQL("insert into tbFriends(name, lastname, age) values ('CARLOS','RODRIGUEZ', '22');");

            txtMsg.setText("se realizo la operacion con exito");
        } catch (SQLException e){
            txtMsg.setText("\nError "+e.getMessage());

        } finally {
            db.endTransaction();
        }

    }
}
