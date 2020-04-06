package com.example.eva2_7_sqlite2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class consulta extends AppCompatActivity {
    private String lista;
    TextView txtList;
    public void setLista(String lista) {
        this.lista = lista;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        txtList = findViewById(R.id.textView2);
        Intent intent = getIntent();
        txtList.setText(intent.getStringExtra("lista"));
    }
}
