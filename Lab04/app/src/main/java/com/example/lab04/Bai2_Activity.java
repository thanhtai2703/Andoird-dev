package com.example.lab04;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Bai2_Activity extends AppCompatActivity {
    private static String FILE_NAME = "myfile.txt";
    private EditText edtInput;
    private ListView listView;
    private ArrayList<String> noteList;
    private ArrayAdapter<String> adapter;
    private int selectedIndex = -1;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai2_activity);
        edtInput = findViewById(R.id.edtNote);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        listView = findViewById(R.id.listNotes);
        noteList = readNotesFromFile();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteList);
        listView.setAdapter(adapter);
        //sua ghi chu
        listView.setOnItemClickListener((parent, view, position, id) -> {
            edtInput.setText(noteList.get(position));
            selectedIndex = position;
        });
        //them ghi chu
        btnAdd.setOnClickListener(v -> {
            String note = edtInput.getText().toString();
            if (!note.isEmpty()) {
                noteList.add(note);
                writeNotesToFile(noteList);
                adapter.notifyDataSetChanged();
                edtInput.setText("");
                selectedIndex = -1;
            }
        });
        //cap nhat ghi chu da duoc chon
        btnUpdate.setOnClickListener(v -> {
            String note = edtInput.getText().toString();
            if (selectedIndex != -1 && !note.isEmpty()) {
                noteList.set(selectedIndex, note);
                writeNotesToFile(noteList);
                adapter.notifyDataSetChanged();
                edtInput.setText("");
                selectedIndex = -1;
            }
        });
    }

    private ArrayList<String> readNotesFromFile() {
        ArrayList<String> notes = new ArrayList<>();
        try {
            java.io.FileInputStream fis = openFileInput(FILE_NAME);
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                notes.add(line);
            }
            reader.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notes;
    }

    private void writeNotesToFile(ArrayList<String> notes) {
        try {
            java.io.FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.OutputStreamWriter(fos));
            for (String note : notes) {
                writer.write(note);
                writer.newLine();
            }
            writer.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
