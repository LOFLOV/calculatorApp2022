package com.android.calculatorapp2022.ui.theme;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.android.calculatorapp2022.R;

public enum Theme {

    ONE(R.style.Theme_CalculatorApp2022, R.string.theme_one, "one"),
    TWO(R.style.Theme_CalculatorApp2022_Second, R.string.theme_two, "two"),
    THREE(R.style.Theme_CalculatorApp2022_Third, R.string.theme_three, "three"),
    FOUR(R.style.Theme_CalculatorApp2022_Fourth, R.string.theme_four, "four");

    @StyleRes
    private final int theme;

    @StringRes
    private final int name;

    private String key;

    public String getKey() {
        return key;
    }

    public final int getTheme() {
        return theme;
    }

    public int getName() {
        return name;
    }



    Theme(int theme, int name, String key) {
        this.theme = theme;
        this.name = name;
        this.key = key;
    }
}
