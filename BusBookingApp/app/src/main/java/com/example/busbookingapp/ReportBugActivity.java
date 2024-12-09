package com.example.busbookingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReportBugActivity extends AppCompatActivity {

    private EditText etBugDescription;
    private Button btnSubmitBug;
    private static final String DEVELOPER_PHONE = "8789806607";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_bug);

        etBugDescription = findViewById(R.id.etBugDescription);
        btnSubmitBug = findViewById(R.id.btnSubmitBug);

        btnSubmitBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bugDescription = etBugDescription.getText().toString().trim();
                if (!bugDescription.isEmpty()) {
                    sendBugReport(bugDescription);
                } else {
                    Toast.makeText(ReportBugActivity.this, "Please describe the bug", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendBugReport(String bugDescription) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + DEVELOPER_PHONE));
        intent.putExtra("sms_body", "Bug Report: " + bugDescription);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No messaging app found", Toast.LENGTH_SHORT).show();
        }
    }
}
