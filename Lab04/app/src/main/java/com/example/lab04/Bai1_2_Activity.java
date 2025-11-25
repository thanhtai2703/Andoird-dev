package com.example.lab04;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Bai1_2_Activity extends AppCompatActivity {
    private LinearLayout mainLayout;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai1_2_activity);
        mainLayout = findViewById(R.id.main12);
        Button btnStartSettings = findViewById(R.id.btn_start);
        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("Pref_mauNen", MODE_PRIVATE);
        // Cập nhật màu nền
        updateBackgroundColor();
        btnStartSettings.setOnClickListener(v -> {
            // Đánh dấu đã ấn Start My Setting
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("has_started_settings", true);
            editor.apply();

            Intent intent = new Intent(Bai1_2_Activity.this, Bai1_2_2_Activity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Cập nhật màu nền
        updateBackgroundColor();
    }
    private void updateBackgroundColor() {
        boolean hasStartedSettings = sharedPreferences.getBoolean("has_started_settings", false);

        if (!hasStartedSettings) {
            mainLayout.setBackgroundResource(R.color.white);
        } else {
            boolean isChecked = sharedPreferences.getBoolean("background_color", false);
            if (isChecked) {
                mainLayout.setBackgroundColor(Color.parseColor("#EF6557")); // Màu đỏ khi được chọn
            } else {
                mainLayout.setBackgroundColor(Color.parseColor("#1976D2"));
            }
        }
    }
}
