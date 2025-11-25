package com.example.lab04;

import android.database.Cursor;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Bai4_1_Activity extends AppCompatActivity {
    EditText edtCode,edtNamme,edtCount;
    ListView listView;
    SimpleCursorAdapter adapter;
    DatabaseHelper dbHelper;
    long selectedRowId = -1;
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai4_1_activity);
        edtCode = findViewById(R.id.edtClassCode);
        edtNamme = findViewById(R.id.edtClassName);
        edtCount = findViewById(R.id.edtTotal);
        listView = findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);
        loadData();
        Button btnInsert = findViewById(R.id.btnAdd);
        btnInsert.setOnClickListener(v->{
            // basic input validation to avoid NumberFormatException
            String code = edtCode.getText().toString().trim();
            String name = edtNamme.getText().toString().trim();
            String countStr = edtCount.getText().toString().trim();
            if(code.isEmpty() || name.isEmpty() || countStr.isEmpty()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            int count;
            try{
                count = Integer.parseInt(countStr);
            }catch(NumberFormatException ex){
                Toast.makeText(this, "Student count must be a number", Toast.LENGTH_SHORT).show();
                return;
            }
            dbHelper.insertClass(code, name, count);
            clearInput();
            loadData();
        });

        Button btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(v->{
           String code = edtCode.getText().toString().trim();
           String name = edtNamme.getText().toString().trim();
           String countStr = edtCount.getText().toString().trim();
           if(code.isEmpty() || name.isEmpty() || countStr.isEmpty()){
               Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
               return;
           }
           int count;
           try{
               count = Integer.parseInt(countStr);
           }catch(NumberFormatException ex){
               Toast.makeText(this, "Student count must be a number", Toast.LENGTH_SHORT).show();
               return;
           }
           dbHelper.updateClass(code, name, count);
           clearInput();
           loadData();
        });

        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(v->{
            String code = edtCode.getText().toString().trim();
            if(code.isEmpty()){
                Toast.makeText(this, "Enter class code to delete", Toast.LENGTH_SHORT).show();
                return;
            }
            dbHelper.deleteClass(code);
            clearInput();
            loadData();
        });
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            if(cursor == null) return;
            // use DatabaseHelper column constants to avoid hard-coded mismatches
            int idxId = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
            if(idxId != -1) selectedRowId = cursor.getLong(idxId);
            int idxCode = cursor.getColumnIndex(DatabaseHelper.COLUMN_CLASS_CODE);
            if(idxCode != -1) edtCode.setText(cursor.getString(idxCode));
            int idxName = cursor.getColumnIndex(DatabaseHelper.COLUMN_CLASS_NAME);
            if(idxName != -1) edtNamme.setText(cursor.getString(idxName));
            int idxStudent = cursor.getColumnIndex(DatabaseHelper.COLUMN_STUDENT_COUNT);
            if(idxStudent != -1) edtCount.setText(String.valueOf(cursor.getInt(idxStudent)));
        });
    }
    private void loadData(){
        Cursor cursor = dbHelper.getAllClasses();
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.class_list_item,
                cursor,
                new String[]{
                        DatabaseHelper.COLUMN_CLASS_CODE,
                        DatabaseHelper.COLUMN_CLASS_NAME,
                        DatabaseHelper.COLUMN_STUDENT_COUNT
                },
                new int[]{
                        R.id.txtClassCode,
                        R.id.txtClassName,
                        R.id.txtTotal
                },
                0
        );
        listView.setAdapter(adapter);
    }
    private void clearInput() {
        edtCode.setText("");
        edtNamme.setText("");
        edtCount.setText("");
        selectedRowId = -1;
    }
}
