package com.example.lab04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Bai4_2_Activity extends AppCompatActivity {
    EditText edta, edtb;
    CheckBox cbsave;
    Button btnLogin;
    TextView txtHistory;
    LoginDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai4_2_activity);

        edta = findViewById(R.id.edtUsername);
        edtb = findViewById(R.id.edtPass);
        cbsave = findViewById(R.id.checkboxRemember);
        btnLogin = findViewById(R.id.btnLogin);
        txtHistory = findViewById(R.id.txtHistory);

        // Khởi tạo DB
        dbHelper = new LoginDBHelper(this);

        // Load lịch sử từ DB
        txtHistory.setText(dbHelper.getAllHistory());

        btnLogin.setOnClickListener(v -> {
            String user = edta.getText().toString().trim();
            String pass = edtb.getText().toString().trim();
            String encryptedPass = LoginDBHelper.getMD5(pass);
            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (dbHelper.checkUser(user, encryptedPass)) {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Tài khoản hoặc mật khẩu không tồn tại!", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(Bai4_2_Activity.this, RegisterActivity.class));
        });

    }
}
