package com.android.calculatorapp2022.ui.calc;

public interface CalculatorView {

    void showResult(String value);

    void clearResultTextView();

    void displayInResultOperation(String operation);
}