package com.example.lab03;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

public class Bai2_Activity extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnC, btnEqual, btnPlus, btnMinus, btnMul, btnDiv;
    private Button btnDot, btnDelete;
    private TextView tvInput;

    private final StringBuilder expr = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
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
        tvInput = findViewById(R.id.tvInput);

        // Digit listeners
        View.OnClickListener digitClick = v -> {
            Button b = (Button) v;
            appendDigit(b.getText().toString());
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

        // Operator listeners
        btnPlus.setOnClickListener(v -> appendOperator("+"));
        btnMinus.setOnClickListener(v -> appendOperator("-"));
        btnMul.setOnClickListener(v -> appendOperator("*"));
        btnDiv.setOnClickListener(v -> appendOperator("/"));

        // Dot
        btnDot.setOnClickListener(v -> appendDot());

        // Delete (backspace)
        btnDelete.setOnClickListener(v -> {
            backspace();
            updateDisplay();
        });

        // Clear
        btnC.setOnClickListener(v -> {
            expr.setLength(0);
            updateDisplay();
        });

        // Equals
        btnEqual.setOnClickListener(v -> evaluate());

        updateDisplay();
    }

    private void appendDigit(String d) {
        // Avoid leading zeroes like "00" unless after operator
        if (d.equals("0")) {
            if (expr.length() == 0) {
                expr.append('0');
                updateDisplay();
                return;
            }
            // If last char is operator, allow 0
        }
        expr.append(d);
        updateDisplay();
    }

    private boolean isOperatorChar(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private void appendOperator(String op) {
        if (expr.length() == 0) {
            // Only allow '-' as leading to start negative numbers
            if (op.equals("-")) {
                expr.append('-');
            }
            updateDisplay();
            return;
        }
        // Replace trailing operator with the new one
        char last = expr.charAt(expr.length() - 1);
        if (isOperatorChar(last)) {
            expr.setCharAt(expr.length() - 1, op.charAt(0));
        } else {
            expr.append(op);
        }
        updateDisplay();
    }

    private void appendDot() {
        if (expr.length() == 0) {
            expr.append("0.");
            updateDisplay();
            return;
        }
        // Prevent consecutive dots and multiple dots in the current number token
        int i = expr.length() - 1;
        while (i >= 0 && !isOperatorChar(expr.charAt(i))) {
            if (expr.charAt(i) == '.') {
                // already has a dot in this number
                return;
            }
            i--;
        }
        char last = expr.charAt(expr.length() - 1);
        if (isOperatorChar(last)) {
            expr.append('0');
        }
        expr.append('.');
        updateDisplay();
    }

    private void backspace() {
        if (expr.length() > 0) {
            expr.deleteCharAt(expr.length() - 1);
        }
    }

    private void evaluate() {
        // Trim trailing operator if present
        while (expr.length() > 0 && isOperatorChar(expr.charAt(expr.length() - 1))) {
            expr.deleteCharAt(expr.length() - 1);
        }
        if (expr.length() == 0) {
            tvInput.setText("");
            return;
        }
        String expression = expr.toString();
        try {
            Evaluator evaluator = new Evaluator();
            String result = evaluator.evaluate(expression);
            // Normalize result (strip trailing .0)
            if (result != null && result.endsWith(".0")) {
                result = result.substring(0, result.length() - 2);
            }
            tvInput.setText(result);
            expr.setLength(0);
            if (result != null) expr.append(result);
        } catch (EvaluationException e) {
            tvInput.setText("Error");
            expr.setLength(0);
        }
    }

    private void updateDisplay() {
        tvInput.setText(expr.toString());
    }
}