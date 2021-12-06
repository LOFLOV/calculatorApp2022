package com.android.calculatorapp2022.ui.calc;

import android.util.Log;

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

    public void onDotPressed() {
    }

    public void onDigitPressed(Double digit) {

        if (previousOperation != null) {
            if (argTwo == null) {
                argTwo = 0.0;
            }
            argTwo = argTwo * 10 + digit;
            view.showResult(String.valueOf(clearAfterDot(argTwo)));
        } else {
            argOne = argOne * 10 + digit;
            view.showResult(String.valueOf(clearAfterDot(argOne)));
        }
    }

    public void onOperandPressed(Operation operation) {

        if (argTwo != null) {
            double result = calculator.performOperation(argOne, argTwo, previousOperation);

            view.showResult(String.valueOf(clearAfterDot(result)));

            argOne = result;
            argTwo = null;
        } else {
            argTwo = 0.0;
            previousOperation = operation;
            displayInResultOperation(operation);

        }
    }

    public String clearAfterDot(double argument) {
        String res = String.valueOf(argument);
        String[] arr = res.split("\\.");
        if (arr[1].equals("0")) {
            res = arr[0];
        }
        return res;
    }

    public void displayInResultOperation(Operation operation) {
        switch (operation) {
            case SUM:
                view.displayInResultOperation(" + ");
                break;
            case SUB:
                view.displayInResultOperation(" - ");
                break;
            case MULT:
                view.displayInResultOperation(" * ");
                break;
            case DIV:
                view.displayInResultOperation(" / ");
                break;
            case PERCENT:
                view.displayInResultOperation(" % ");
                break;
        }
    }

    public void onAcPressed() {
        argOne = 0.0;
        argTwo = null;
        previousOperation = null;
        view.clearResultTextView();

    }
}
