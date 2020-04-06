package com.example.eva2_3_preference_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mi_menu, menu);


        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.misettings){
            //invocar preferencesreen
            //Toast.makeText(this,"Hola como estas?", Toast.LENGTH_SHORT).show();
            Intent inSettings = new Intent(this, Main2Activity.class);
            startActivity(inSettings);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }
}
