package com.example.eva2_8_sqlite3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, last, age;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.editName);
        last = findViewById(R.id.editLastname);
        age = findViewById(R.id.editAge);
        db = this.openOrCreateDatabase("myDB",MODE_PRIVATE,null);

    }

    public void addRow(View view) {
        if(name.getText().toString().equals("") && last.getText().toString().equals("") && age.getText().toString().equals("")){
            Toast.makeText(this, "Todos los campos tienen que tener valores", Toast.LENGTH_SHORT).show();
        }else {
            if (isNumeric(age.getText().toString())){
                String nombre = name.getText().toString();
                String apellido = last.getText().toString();
                int edad = Integer.parseInt(age.getText().toString());
                ContentValues row=new ContentValues();
                row.put("name", nombre);
                row.put("lastname", apellido);
                row.put("age", edad);
                db.beginTransaction();
                try {

                    db.setTransactionSuccessful();

                    db.execSQL("create table if not exists tbFriends (" +
                            "id integer PRIMARY KEY autoincrement, " +
                            "name text, " +
                            "lastname text,"+
                            "age integer);");


                    db.insert("tbFriends", null, row);
                    name.setText("");
                    last.setText("");
                    age.setText("");
                    Toast.makeText(this, "Operación exitosa", Toast.LENGTH_SHORT).show();

                }catch (SQLException e){
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }finally {
                    db.endTransaction();
                }
            }else {
                Toast.makeText(this, "La edad tiene que tener un valor numerico", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void selectRows(View view) {
        try {
            db.beginTransaction();
            db.execSQL("create table if not exists tbFriends (" +
                    "id integer PRIMARY KEY autoincrement, " +
                    "name text, " +
                    "lastname text,"+
                    "age integer);");
            String SQL = "select * from tbFriends";
            Cursor cursor = db.rawQuery(SQL, null);
            String list="id    Name    Lastname    Age\n";
            while (cursor.moveToNext()){
                list+=cursor.getInt(0)+"    "+cursor.getString(1)+"    "+cursor.getString(2)+"    "+cursor.getInt(3)+"\n";
            }

            Intent intent = new Intent(this, consulta.class);
            intent.putExtra("lista", list);
            startActivity(intent);
        }catch (SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            db.endTransaction();
        }


    }

    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    public void Delete(View view) {
        if(name.getText().toString().equals("") && last.getText().toString().equals("") && age.getText().toString().equals("")){
            Toast.makeText(this, "Todos los campos tienen que tener valores", Toast.LENGTH_SHORT).show();
        }else {
            if (isNumeric(age.getText().toString())){
                String nombre = name.getText().toString();
                String apellido = last.getText().toString();
                int edad = Integer.parseInt(age.getText().toString());
                db.beginTransaction();
                try {
                    db.setTransactionSuccessful();
                    db.delete("tbFriends", "name='"+nombre+"' and lastname='"+apellido+"'and age= "+edad, null);
                    name.setText("");
                    last.setText("");
                    age.setText("");
                    Toast.makeText(this, "Operación exitosa", Toast.LENGTH_SHORT).show();

                }catch (SQLException e){
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }finally {
                    db.endTransaction();
                }
            }else {
                Toast.makeText(this, "La edad tiene que tener un valor numerico", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Update(View view) {
        if(name.getText().toString().equals("") && last.getText().toString().equals("") && age.getText().toString().equals("")){
            Toast.makeText(this, "Todos los campos tienen que tener valores", Toast.LENGTH_SHORT).show();
        }else {
            if (isNumeric(age.getText().toString())){
                String nombre = name.getText().toString();
                String apellido = last.getText().toString();
                int edad = Integer.parseInt(age.getText().toString());
                db.beginTransaction();
                try {
                    db.setTransactionSuccessful();
                    ContentValues contentValues= new ContentValues();
                    contentValues.put("age", edad);
                    String [] args = {nombre,apellido};
                    db.update("tbFriends", contentValues , "name=? and lastname=?",args);
                    name.setText("");
                    last.setText("");
                    age.setText("");
                    Toast.makeText(this, "Operación exitosa", Toast.LENGTH_SHORT).show();

                }catch (SQLException e){
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }finally {
                    db.endTransaction();
                }
            }else {
                Toast.makeText(this, "La edad tiene que tener un valor numerico", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
