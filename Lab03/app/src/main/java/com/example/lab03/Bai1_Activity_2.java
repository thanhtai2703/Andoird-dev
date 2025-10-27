package com.example.lab03;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Bai1_Activity_2 extends AppCompatActivity {

    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_CMND = "extra_cmnd";
    public static final String EXTRA_DEGREE = "extra_degree";
    public static final String EXTRA_HOBBIES = "extra_hobbies";
    public static final String EXTRA_EXTRA_INFO = "extra_extra_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai1_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvName = findViewById(R.id.tv_name);
        TextView tvCmnd = findViewById(R.id.tv_cmnd);
        TextView tvDegree = findViewById(R.id.tv_degree);
        TextView tvHobbies = findViewById(R.id.tv_hobbies);
        TextView tvExtra = findViewById(R.id.tv_extra);
        Button btnClose = findViewById(R.id.btn_close);

        String name = getIntent().getStringExtra(EXTRA_NAME);
        String cmnd = getIntent().getStringExtra(EXTRA_CMND);
        String degree = getIntent().getStringExtra(EXTRA_DEGREE);
        ArrayList<String> hobbies = getIntent().getStringArrayListExtra(EXTRA_HOBBIES);
        String extraInfo = getIntent().getStringExtra(EXTRA_EXTRA_INFO);

        tvName.setText(name != null ? name : "");
        tvCmnd.setText(cmnd != null ? cmnd : "");
        tvDegree.setText(degree != null ? degree : "");

        String hobbiesText;
        if (hobbies != null && !hobbies.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hobbies.size(); i++) {
                if (i > 0) sb.append(" - ");
                sb.append(hobbies.get(i));
            }
            hobbiesText = sb.toString();
        } else {
            hobbiesText = "";
        }
        tvHobbies.setText(hobbiesText);

        if (extraInfo == null) extraInfo = "";
        tvExtra.setText(extraInfo);

        btnClose.setOnClickListener(v -> finish());
    }
}