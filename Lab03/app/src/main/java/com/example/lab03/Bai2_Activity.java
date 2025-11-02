package com.example.lab03;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

public class Bai2_Activity extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnC, btnEqual, btnPlus, btnMinus, btnMul, btnDiv;
    private Button btnDot, btnDelete;
    private EditText etInput, etResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        // Initialize all buttons
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btnC = findViewById(R.id.btnC);
        btnEqual = findViewById(R.id.btnEqual);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        btnDot = findViewById(R.id.btnDot);
        btnDelete = findViewById(R.id.delete);
        etInput = findViewById(R.id.etInput);
        etResult = findViewById(R.id.etResult);

        // Set up digit button listeners
        View.OnClickListener digitClick = v -> {
            Button b = (Button) v;
            appendToDisplay(b.getText().toString());
        };

        btn0.setOnClickListener(digitClick);
        btn1.setOnClickListener(digitClick);
        btn2.setOnClickListener(digitClick);
        btn3.setOnClickListener(digitClick);
        btn4.setOnClickListener(digitClick);
        btn5.setOnClickListener(digitClick);
        btn6.setOnClickListener(digitClick);
        btn7.setOnClickListener(digitClick);
        btn8.setOnClickListener(digitClick);
        btn9.setOnClickListener(digitClick);
        // Set up operator button listeners
        btnPlus.setOnClickListener(digitClick);
        btnMinus.setOnClickListener(digitClick);
        btnMul.setOnClickListener(digitClick);
        btnDiv.setOnClickListener(digitClick);
        btnDot.setOnClickListener(digitClick);

        // Delete button - removes last character from input
        btnDelete.setOnClickListener(v -> deleteLastCharacter());

        // Clear button - clears all content
        btnC.setOnClickListener(v -> {
            etInput.setText("");
            etResult.setText("");
        });

        // Equals button - evaluates the expression
        btnEqual.setOnClickListener(v -> evaluateExpression());
    }

    private void appendToDisplay(String value) {
        String currentText = etInput.getText().toString();
        etInput.setText(currentText + value);
    }

    private void deleteLastCharacter() {
        String currentText = etInput.getText().toString();
        if (!currentText.isEmpty()) {
            etInput.setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    private void evaluateExpression() {
        String expression = etInput.getText().toString().trim();

        if (expression.isEmpty()) {
            return;
        }

        try {
            Evaluator evaluator = new Evaluator();
            String result = evaluator.evaluate(expression);
            if (result != null && result.endsWith(".0")) {
                result = result.substring(0, result.length() - 2);
            }
            etResult.setText(result);
            etInput.setText("");
        } catch (EvaluationException e) {
            etResult.setText("Error");
        }
    }
}