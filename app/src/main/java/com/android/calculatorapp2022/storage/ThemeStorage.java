package com.android.calculatorapp2022.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.calculatorapp2022.ui.theme.Theme;

public class ThemeStorage {
    private static final String ARG_SAVED_THEME = "ARG_SAVED_THEME_2";

    private final Context context;

    public ThemeStorage(Context context) {
        this.context = context;
    }

    public void saveTheme(Theme theme) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Themes", Context.MODE_PRIVATE);

        sharedPreferences.edit()
                .putString(ARG_SAVED_THEME, theme.getKey())
                .apply();
    }

    public Theme getSavedTheme() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Themes", Context.MODE_PRIVATE);

        String key = sharedPreferences.getString(ARG_SAVED_THEME, Theme.ONE.getKey());

        for (Theme theme : Theme.values()) {
            if (key.equals(theme.getKey())) {
                return theme;
            }
        }

        return Theme.ONE;
    }
}
