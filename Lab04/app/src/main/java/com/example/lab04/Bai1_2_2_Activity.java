package com.example.lab04;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Bai1_2_2_Activity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai1_2_2_activity);
        CheckBox cbMauNen = findViewById(R.id.cb_mauNen);
        CheckBox cbPreferences = findViewById(R.id.cb_preferences);
        TextView txtCheckboxStatus = findViewById(R.id.tv_checkbox_status);
        Button btnBack = findViewById(R.id.btn_back);

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("Pref_mauNen", MODE_PRIVATE);
        // Tải cài đặt đã lưu cho checkbox màu nền
        boolean isRedBackground = sharedPreferences.getBoolean("background_color", false);
        cbMauNen.setChecked(isRedBackground);

        // Tải cài đặt đã lưu cho checkbox preferences
        boolean isCheckboxPreferences = sharedPreferences.getBoolean("checkbox_preferences", false);
        cbPreferences.setChecked(isCheckboxPreferences);

        // Cập nhật TextView hiển thị trạng thái
        updateCheckboxStatus(txtCheckboxStatus, isCheckboxPreferences);

        // Thiết lập listener cho checkbox màu nền
        cbMauNen.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("background_color", isChecked);
            editor.apply();
        });

        // Thiết lập listener cho checkbox preferences
        cbPreferences.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Lưu cài đặt checkbox preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("checkbox_preferences", isChecked);
            editor.apply();

            // Cập nhật TextView hiển thị trạng thái
            updateCheckboxStatus(txtCheckboxStatus, isChecked);
        });

        btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    // Phương thức cập nhật trạng thái hiển thị
    private void updateCheckboxStatus(TextView textView, boolean isChecked) {
        if (isChecked) {
            textView.setText(R.string.checked_true);
        } else {
            textView.setText(R.string.checked_false);
        }
    }
}
