package com.android.calculatorapp2022.ui.calc;

import com.android.calculatorapp2022.domain.Calculator;
import com.android.calculatorapp2022.domain.Operation;

public class CalculatorPresenter {

    private CalculatorView view;
    private Calculator calculator;

    private Double argOne = 0.0;
    private Double argTwo = null;
    private Operation previousOperation = null;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void onDotPrsesed() {
    }

    public void onDigitPressed(int digit) {

        if (previousOperation != null) {
            argTwo = argTwo * 10 + digit;

            view.showResult(String.valueOf(argOne));
        } else {
            argOne = argOne * 10 + digit;

            view.showResult(String.valueOf(argOne));
        }
    }

    public void onOperandPressed(Operation operation) {

        if (argTwo != null) {
            double result = calculator.performOperation(argOne, argTwo, previousOperation);

            view.showResult(String.valueOf(result));

            argOne = result;
            argTwo = 0.0;
        } else {
            argTwo = 0.0;
            previousOperation = operation;
        }
    }

    public int sum(int a, int b) {
        return a+b;
    }
}
