package com.example.lab03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Bai1_Activity_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai1_1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inputs
        EditText edtName = findViewById(R.id.name_value);
        EditText edtCmnd = findViewById(R.id.cmnd_value);
        RadioGroup rgDegree = findViewById(R.id.radio_group_bangcap);
        RadioButton rbTrungCap = findViewById(R.id.radio_trungcap);
        RadioButton rbCaoDang = findViewById(R.id.radio_caodang);
        RadioButton rbDaiHoc = findViewById(R.id.radio_daihoc);
        CheckBox cbTheThao = findViewById(R.id.checkbox_thethao);
        CheckBox cbAmNhac = findViewById(R.id.checkbox_amnhac);
        CheckBox cbDuLich = findViewById(R.id.checkbox_dulich);
        EditText edtExtra = findViewById(R.id.edt_extra_info);
        Button btnSend = findViewById(R.id.btn_send);

        btnSend.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String cmnd = edtCmnd.getText().toString().trim();

            String degree;
            int checkedId = rgDegree.getCheckedRadioButtonId();
            if (checkedId == rbTrungCap.getId()) {
                degree = rbTrungCap.getText().toString();
            } else if (checkedId == rbCaoDang.getId()) {
                degree = rbCaoDang.getText().toString();
            } else if (checkedId == rbDaiHoc.getId()) {
                degree = rbDaiHoc.getText().toString();
            } else {
                degree = "Chưa chọn";
            }

            ArrayList<String> hobbies = new ArrayList<>();
            if (cbTheThao.isChecked()) hobbies.add(cbTheThao.getText().toString());
            if (cbAmNhac.isChecked()) hobbies.add(cbAmNhac.getText().toString());
            if (cbDuLich.isChecked()) hobbies.add(cbDuLich.getText().toString());

            String extraInfo = edtExtra.getText().toString();

            Intent intent = new Intent(Bai1_Activity_1.this, Bai1_Activity_2.class);
            intent.putExtra(Bai1_Activity_2.EXTRA_NAME, name);
            intent.putExtra(Bai1_Activity_2.EXTRA_CMND, cmnd);
            intent.putExtra(Bai1_Activity_2.EXTRA_DEGREE, degree);
            intent.putStringArrayListExtra(Bai1_Activity_2.EXTRA_HOBBIES, hobbies);
            intent.putExtra(Bai1_Activity_2.EXTRA_EXTRA_INFO, extraInfo);
            startActivity(intent);
        });
    }
}