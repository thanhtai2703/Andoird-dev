package com.example.lab02_23521380;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Navigate to Bai1.1
        Button btnBai11 = findViewById(R.id.btnBai11);
        if (btnBai11 != null) {
            btnBai11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, Bai11Activity.class));
                }
            });
        }

        // Navigate to Bai1.2
        Button btnBai12 = findViewById(R.id.btnBai12);
        if (btnBai12 != null) {
            btnBai12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, Bai12Activity.class));
                }
            });
        }

        // Navigate to Bai2
        Button btnBai2 = findViewById(R.id.btnBai2);
        if (btnBai2 != null) {
            btnBai2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, Bai2Activity.class));
                }
            });
        }

        // Navigate to Bai3
        Button btnBai3 = findViewById(R.id.btnBai3);
        if (btnBai3 != null) {
            btnBai3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, Bai3Activity.class));
                }
            });
        }

        // Navigate to Bai4
        Button btnBai4 = findViewById(R.id.btnBai4);
        if (btnBai4 != null) {
            btnBai4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, Bai4Activity.class));
                }
            });
        }
    }
}
