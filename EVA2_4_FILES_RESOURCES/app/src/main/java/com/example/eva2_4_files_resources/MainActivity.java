package com.example.eva2_4_files_resources;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=findViewById(R.id.textView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        InputStream in = getResources().openRawResource(R.raw.lorem_ipsum);
        InputStreamReader reader= new InputStreamReader(in);
        BufferedReader buffer = new BufferedReader(reader);
        String scade;
            try {
                while((scade=buffer.readLine())!=null) {
                    txt.append(scade);
                    txt.append("\n");
                }
                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


    }
}
