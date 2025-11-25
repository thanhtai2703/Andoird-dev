package com.example.lab04;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Bai1_1_Activity extends AppCompatActivity {
    EditText edta,edtb;
    CheckBox cbsave;
    Button btnLogin;
    TextView txtHistory;
    String history ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai1_1_activity);
        edta = findViewById(R.id.edtUsername);
        edtb = findViewById(R.id.edtPass);
        cbsave = findViewById(R.id.checkboxRemember);
        btnLogin = findViewById(R.id.btnLogin);
        txtHistory = findViewById(R.id.txtHistory);

        //Đọc dữ liệu
        SharedPreferences myprefs = getSharedPreferences("myhistory",MODE_PRIVATE);
        history = myprefs.getString("history","");
        txtHistory.setText(history);
        //Xử lí nút đăng nhập
        btnLogin.setOnClickListener(view -> {
            String username = edta.getText().toString();
            String pass = edtb.getText().toString();
            if(cbsave.isChecked()){
                history += "Username: " + username + " - Password: " + pass + "\n";
                txtHistory.setText(history);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences myprefs = getSharedPreferences("myhistory",MODE_PRIVATE);
        SharedPreferences.Editor myedit = myprefs.edit();
        myedit.putString("history",history);
        myedit.commit();
    }
}
