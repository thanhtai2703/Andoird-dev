package com.example.lab04;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Bai3_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai3_activity);
        EditText edtInput = findViewById(R.id.edtData);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnLoad = findViewById(R.id.btnRead);
        TextView txtResult = findViewById(R.id.txtResult);
        if(!isExternalStorageWritable()){
            Toast.makeText(this,"External Storage not available",Toast.LENGTH_SHORT).show();
            return;
        }
        //luu du lieu
        btnSave.setOnClickListener(v -> {
            String data = edtInput.getText().toString();
            if (data.isEmpty()) {
                Toast.makeText(this, "Please enter data to save", Toast.LENGTH_SHORT).show();
                return;
            }
            File file = new File(getExternalFilesDir(null), "external.txt");
            try (FileOutputStream fos = new FileOutputStream(file,true)){
                fos.write(data.getBytes());
                Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //doc du lieu
        btnLoad.setOnClickListener(v -> {
            File file = new File(getExternalFilesDir(null), "external.txt");
            if(file.exists()) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    byte[] data = new byte[(int) file.length()];
                    fis.read(data);
                    txtResult.setText(new String(data));
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            else{
                txtResult.setText("No data in external.txt");
            }
        });
    }
    //kiem tra co the ghi vao ex storage khong
    private boolean isExternalStorageWritable(){
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }
}
