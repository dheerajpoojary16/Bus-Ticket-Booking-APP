package com.example.busbookingapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class ChangeThemeActivity extends AppCompatActivity {

    private RadioGroup themeRadioGroup;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_theme);

        sharedPreferences = getSharedPreferences("ThemePrefs", MODE_PRIVATE);
        themeRadioGroup = findViewById(R.id.themeRadioGroup);
        Button applyThemeButton = findViewById(R.id.applyThemeButton);

        // Set the current theme
        int currentTheme = AppCompatDelegate.getDefaultNightMode();
        setRadioButtonForTheme(currentTheme);

        applyThemeButton.setOnClickListener(v -> applySelectedTheme());
    }

    private void setRadioButtonForTheme(int theme) {
        int radioButtonId;
        switch (theme) {
            case AppCompatDelegate.MODE_NIGHT_NO:
                radioButtonId = R.id.lightTheme;
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                radioButtonId = R.id.darkTheme;
                break;
            case AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY:
                radioButtonId = R.id.batteryTheme;
                break;
            default:
                radioButtonId = R.id.systemTheme;
        }
        themeRadioGroup.check(radioButtonId);
    }

    private void applySelectedTheme() {
        int selectedTheme = getSelectedTheme();

        // Save the selected theme
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("theme", selectedTheme);
        editor.apply();

        // Apply the theme
        AppCompatDelegate.setDefaultNightMode(selectedTheme);

        // Recreate the activity to apply the theme
        recreate();

        Toast.makeText(this, "Theme applied. Changes will take effect immediately.", Toast.LENGTH_LONG).show();
    }

    private int getSelectedTheme() {
        int checkedId = themeRadioGroup.getCheckedRadioButtonId();
        if (checkedId == R.id.lightTheme) {
            return AppCompatDelegate.MODE_NIGHT_NO;
        } else if (checkedId == R.id.darkTheme) {
            return AppCompatDelegate.MODE_NIGHT_YES;
        } else if (checkedId == R.id.batteryTheme) {
            return AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY;
        } else {
            return AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
        }
    }
}
