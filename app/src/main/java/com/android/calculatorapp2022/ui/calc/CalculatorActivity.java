package com.android.calculatorapp2022.ui.calc;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.calculatorapp2022.R;
import com.android.calculatorapp2022.domain.CalculatorImpl;
import com.android.calculatorapp2022.domain.Operation;
import com.android.calculatorapp2022.storage.ThemeStorage;
import com.android.calculatorapp2022.ui.theme.SelectThemeActivity;
import com.android.calculatorapp2022.ui.theme.Theme;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private TextView txtResult;
    private MaterialButton btnPlus;
    private MaterialButton btnMinus;
    private MaterialButton btnMult;
    private MaterialButton btnDiv;
    private MaterialButton btnEqual;

    private CalculatorPresenter presenter;

    private ThemeStorage storage;

    // ActivityResultLauncher позволит запустить активити и вернуть результат
    // вызвать launch и метод intent

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Theme theme = (Theme) result.getData().getSerializableExtra(SelectThemeActivity.EXTRA_THEME);

                storage.saveTheme(theme);

                recreate();
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();

        storage = new ThemeStorage(this);
        setTheme(storage.getSavedTheme().getTheme());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new CalculatorPresenter(this, new CalculatorImpl());

        txtResult = findViewById(R.id.result);
        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnMult = findViewById(R.id.btn_multiply);
        btnDiv = findViewById(R.id.btn_divide);
        btnEqual = findViewById(R.id.btn_equals);

        findViewById(R.id.btn_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDotPressed();
            }
        });

        findViewById(R.id.btn_ac).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onAcPressed();
            }
        });

        HashMap<Integer, Double> digits = new HashMap<>();
        digits.put(R.id.btn_0, 0.0);
        digits.put(R.id.btn_1, 1.0);
        digits.put(R.id.btn_2, 2.0);
        digits.put(R.id.btn_3, 3.0);
        digits.put(R.id.btn_4, 4.0);
        digits.put(R.id.btn_5, 5.0);
        digits.put(R.id.btn_6, 6.0);
        digits.put(R.id.btn_7, 7.0);
        digits.put(R.id.btn_8, 8.0);
        digits.put(R.id.btn_9, 9.0);

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
        operands.put(R.id.btn_equals, Operation.EQUAL);
        operands.put(R.id.btn_percent, Operation.PERCENT);

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
        findViewById(R.id.btn_equals).setOnClickListener(operandClickListener);
        findViewById(R.id.btn_percent).setOnClickListener(operandClickListener);

        findViewById(R.id.btn_choose_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculatorActivity.this, SelectThemeActivity.class);
                intent.putExtra(SelectThemeActivity.EXTRA_THEME, storage.getSavedTheme());
//                startActivity(intent);

                launcher.launch(intent);
            }
        });
    }

    @Override
    public void showResult(String value) {
        txtResult.setText(value);
    }

    @Override
    public void clearResultTextView() {
        txtResult.setText("");
    }

    @Override
    public void displayInResultOperation(String operation) {
        String existingText = txtResult.getText().toString();
        txtResult.setText(existingText + operation);
    }

}