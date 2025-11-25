package com.example.lab03;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
        // Set Đại học as default
        rbDaiHoc.setChecked(true);
        // Name validation: not empty and at least 3 characters
        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                String name = s.toString().trim();
                if (name.isEmpty() || name.length() < 3) {
                    edtName.setError("Tên phải có ít nhất 3 ký tự");
                } else {
                    edtName.setError(null);
                }
            }
        });

        // CMND validation: exactly 9 digits
        edtCmnd.setInputType(InputType.TYPE_CLASS_NUMBER);
        edtCmnd.setFilters(new InputFilter[] { new InputFilter.LengthFilter(9) });
        edtCmnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                String cmnd = s.toString().trim();
                if (!cmnd.matches("\\d{9}")) {
                    edtCmnd.setError("CMND phải có đúng 9 chữ số");
                } else {
                    edtCmnd.setError(null);
                }
            }
        });

        // Send button click validation and handling
        btnSend.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String cmnd = edtCmnd.getText().toString().trim();
            boolean isValid = true;

            // Validate name
            if (name.isEmpty() || name.length() < 3) {
                edtName.setError("Tên phải có ít nhất 3 ký tự");
                isValid = false;
            }

            // Validate CMND
            if (!cmnd.matches("\\d{9}")) {
                edtCmnd.setError("CMND phải có đúng 9 chữ số");
                isValid = false;
            }
            if (!cbTheThao.isChecked() && !cbAmNhac.isChecked() && !cbDuLich.isChecked()) {
                Toast.makeText(this, "Vui lòng chọn ít nhất một sở thích", Toast.LENGTH_SHORT).show();
                isValid = false;
            }

            if (!isValid) {
                return;
            }

            // Get selected degree
            String degree;
            int checkedId = rgDegree.getCheckedRadioButtonId();
            if (checkedId == rbTrungCap.getId()) {
                degree = rbTrungCap.getText().toString();
            } else if (checkedId == rbCaoDang.getId()) {
                degree = rbCaoDang.getText().toString();
            } else {
                degree = rbDaiHoc.getText().toString();
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