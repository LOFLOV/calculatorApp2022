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
import android.widget.Toast;

import com.android.calculatorapp2022.R;
import com.android.calculatorapp2022.domain.CalculatorImpl;
import com.android.calculatorapp2022.domain.Operation;
import com.android.calculatorapp2022.storage.ThemeStorage;
import com.android.calculatorapp2022.ui.theme.SelectThemeActivity;
import com.android.calculatorapp2022.ui.theme.Theme;

import java.util.HashMap;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private TextView txtResult;

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
}