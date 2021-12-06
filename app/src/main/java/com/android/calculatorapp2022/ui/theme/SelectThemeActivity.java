package com.android.calculatorapp2022.ui.theme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.calculatorapp2022.R;

public class SelectThemeActivity extends AppCompatActivity {

    public static final String EXTRA_THEME = "EXTRA_THEME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_theme);

        getSupportActionBar().hide();

        Intent launchIntent = getIntent();

        Theme selectedTheme = (Theme) launchIntent.getSerializableExtra(EXTRA_THEME);

        LinearLayout themeContainer = findViewById(R.id.themes);

        for (Theme theme: Theme.values()) {

            View view = LayoutInflater.from(this).inflate(R.layout.item_theme,themeContainer, false);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent data = new Intent();
                    data.putExtra(EXTRA_THEME, theme);
                    setResult(Activity.RESULT_OK, data);            //пердаем данные (1 аргумент код числовой. 2-й данные в интенте)
                    finish();
                }
            });

            TextView themeItemTitle = view.findViewById(R.id.theme_item);
            themeItemTitle.setText(theme.getName());

            ImageView check = view.findViewById(R.id.check);

            if (theme.equals(selectedTheme)){
                check.setVisibility(View.VISIBLE);
            } else {
                check.setVisibility(View.GONE);
            }

            themeContainer.addView(view);
        }
    }
}