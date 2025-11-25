package com.example.lab04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout; // Layout menu dùng LinearLayout làm nút bấm
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Bài 1.1: SharedPreferences - Login
        LinearLayout btnBai1_1 = findViewById(R.id.btnBai1_1);
        btnBai1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Bai1_1_Activity.class);
                startActivity(intent);
            }
        });

        // Bài 1.2: SharedPreferences - Settings
        findViewById(R.id.btnBai1_2).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Bai1_2_Activity.class);
            startActivity(intent);
        });

        // Bài 2: Internal Storage - Note App
        findViewById(R.id.btnBai2).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Bai2_Activity.class);
            startActivity(intent);
        });

        // Bài 3: External Storage - File Manager
        findViewById(R.id.btnBai3).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Bai3_Activity.class);
            startActivity(intent);
        });

        // Bài 4.1: SQLite Management
        findViewById(R.id.btnBai4_1).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Bai4_1_Activity.class);
            startActivity(intent);
        });

        // Bài 4.2: SQLite User Login
        findViewById(R.id.btnBai4_2).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Bai4_2_Activity.class);
            startActivity(intent);
        });
    }
}