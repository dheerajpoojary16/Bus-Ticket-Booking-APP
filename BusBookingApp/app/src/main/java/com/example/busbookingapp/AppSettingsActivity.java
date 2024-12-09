package com.example.busbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AppSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings); // Ensure this matches your layout file

        // Initialize buttons
        Button btnChangeTheme = findViewById(R.id.btnChangeTheme);
        Button btnReportBug = findViewById(R.id.btnReportBug);
        Button btnClearHistory = findViewById(R.id.btnClearHistory);

        // Set onClickListeners for each button
        btnChangeTheme.setOnClickListener(v -> {
            Intent intent = new Intent(AppSettingsActivity.this, ChangeThemeActivity.class);
            startActivity(intent);
        });

        btnReportBug.setOnClickListener(v -> {
            Intent intent = new Intent(AppSettingsActivity.this, ReportBugActivity.class);
            startActivity(intent);
        });

        btnClearHistory.setOnClickListener(v -> {
            clearTransactionHistory();
        });
    }

    private void clearTransactionHistory() {
        // Implement the logic to clear all transaction history
        // This might involve deleting data from a database or shared preferences

        // For example, if you're using a database:
        // DatabaseHelper dbHelper = new DatabaseHelper(this);
        // dbHelper.clearTransactionHistory();

        // After clearing the history, show a confirmation message
        Toast.makeText(this, "Transaction history cleared", Toast.LENGTH_SHORT).show();
    }
}
