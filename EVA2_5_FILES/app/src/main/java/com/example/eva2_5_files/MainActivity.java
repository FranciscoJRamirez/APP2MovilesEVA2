package com.example.eva2_5_files;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText edit;
    final String archivo = "mi_archivo.txt";
    final int permission = 1000;
    String srutasd;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //AQUI HABRIA QUE TOMAR DECISIONES SOBRE LOS PERMISOS
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = findViewById(R.id.editText);
        srutasd = getExternalFilesDir(null).getPath();
        Toast.makeText(this, srutasd, Toast.LENGTH_SHORT).show();
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, permission);
        }
    }

    public void guardar(View view) {
        String cade = edit.getText().toString();
        try {
            File file = new File(getExternalFilesDir(null), archivo);
            FileOutputStream on = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(on);
            BufferedWriter buffer = new BufferedWriter(osw);

            buffer.write(cade);
            buffer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leer(View view) {
        try {
            //InputStream in = openFileInput(archivo); //para poner un archivo en el almacenamiento interno
            //FileInputStream in = new FileInputStream(srutasd+ "/"+ archivo); //para poner un archivo en la sdcard abajo android 10
            File file = new File(getExternalFilesDir(null), archivo);//para poner un archivo en la sdcard despues de android 10
            FileInputStream in = new FileInputStream(file);
            InputStreamReader ist = new InputStreamReader(in);
            BufferedReader buffer = new BufferedReader(ist);
            String sCade;
            while((sCade=buffer.readLine())!=null) {
                edit.append(sCade);
                edit.append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
