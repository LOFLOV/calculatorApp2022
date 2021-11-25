package com.android.calculatorapp2022.domain;

public class CalculatorImpl implements Calculator {
    @Override
    public double performOperation(double argOne, double argTwo, Operation operation) {
        switch (operation) {
            case SUM:
                return argOne + argTwo;
            case SUB:
                return argOne - argTwo;
            case DIV:
                return argOne / argTwo;
            case MULT:
                return argOne * argTwo;
        }
        return 0;
    }
}
