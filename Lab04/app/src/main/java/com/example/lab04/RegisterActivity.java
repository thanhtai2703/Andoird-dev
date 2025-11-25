package com.example.lab04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edtNewUser, edtNewPass;
    Button btnSubmitRegister;

    LoginDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        edtNewUser = findViewById(R.id.edtNewUser);
        edtNewPass = findViewById(R.id.edtNewPass);
        btnSubmitRegister = findViewById(R.id.btnSubmitRegister);

        dbHelper = new LoginDBHelper(this);

        btnSubmitRegister.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String user = edtNewUser.getText().toString().trim();
        String pass = edtNewPass.getText().toString().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Không được để trống!", Toast.LENGTH_SHORT).show();
            return;
        }
        String encryptedPass = LoginDBHelper.getMD5(pass);
        // Lưu vào SQLite
        dbHelper.insertHistory(user, encryptedPass);

        Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

        // Quay lại màn hình Login
        Intent intent = new Intent(RegisterActivity.this, Bai4_2_Activity.class);
        startActivity(intent);
        finish();
    }
}
