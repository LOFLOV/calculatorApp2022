package com.android.calculatorapp2022.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.calculatorapp2022.R;
import com.android.calculatorapp2022.domain.CalculatorImpl;
import com.android.calculatorapp2022.domain.Operation;

import java.util.HashMap;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private TextView txtResult;

    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new CalculatorPresenter(this, new CalculatorImpl());

        txtResult = findViewById(R.id.result);

        findViewById(R.id.btn_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDotPrsesed();
            }
        });

        HashMap<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.btn_0, 0);
        digits.put(R.id.btn_1, 1);
        digits.put(R.id.btn_2, 2);
        digits.put(R.id.btn_3, 3);
        digits.put(R.id.btn_4, 4);
        digits.put(R.id.btn_5, 5);
        digits.put(R.id.btn_6, 6);
        digits.put(R.id.btn_7, 7);
        digits.put(R.id.btn_8, 8);
        digits.put(R.id.btn_9, 9);

        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitPressed(digits.get(view.getId()));
            }
        };

        findViewById(R.id.btn_0).setOnClickListener(digitClickListener);
        findViewById(R.id.btn_1).setOnClickListener(digitClickListener);
        findViewById(R.id.btn_2).setOnClickListener(digitClickListener);
        findViewById(R.id.btn_3).setOnClickListener(digitClickListener);
        findViewById(R.id.btn_4).setOnClickListener(digitClickListener);
        findViewById(R.id.btn_5).setOnClickListener(digitClickListener);
        findViewById(R.id.btn_6).setOnClickListener(digitClickListener);
        findViewById(R.id.btn_7).setOnClickListener(digitClickListener);
        findViewById(R.id.btn_8).setOnClickListener(digitClickListener);
        findViewById(R.id.btn_9).setOnClickListener(digitClickListener);

        HashMap<Integer, Operation> operands = new HashMap<>();
        operands.put(R.id.btn_plus, Operation.SUM);
        operands.put(R.id.btn_minus, Operation.SUB);
        operands.put(R.id.btn_divide, Operation.DIV);
        operands.put(R.id.btn_multiply, Operation.MULT);
/*
        operands.put(R.id.btn_plus_minus, Operation.PLUSMINUS);
        operands.put(R.id.btn_percent, Operation.PERCENT);

 */

        View.OnClickListener operandClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperandPressed(operands.get(view.getId()));
            }
        };

        findViewById(R.id.btn_plus).setOnClickListener(operandClickListener);
        findViewById(R.id.btn_minus).setOnClickListener(operandClickListener);
        findViewById(R.id.btn_divide).setOnClickListener(operandClickListener);
        findViewById(R.id.btn_multiply).setOnClickListener(operandClickListener);


    }

    @Override
    public void showResult(String value) {
        txtResult.setText(value);
    }
}