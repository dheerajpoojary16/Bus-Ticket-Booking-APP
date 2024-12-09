package com.example.busbookingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.preferences, rootKey);

            setupPreference("my_account", MyAccountActivity.class);
            setupPreference("previous_transactions", PreviousTransactionsActivity.class);
            setupPreference("app_settings", AppSettingsActivity.class);
            
            // Add logout preference
            Preference logoutPreference = findPreference("logout");
            if (logoutPreference != null) {
                logoutPreference.setOnPreferenceClickListener(preference -> {
                    logout();
                    return true;
                });
            }
        }

        private void setupPreference(String key, Class<?> activityClass) {
            Preference preference = findPreference(key);
            if (preference != null) {
                preference.setOnPreferenceClickListener(pref -> {
                    Intent intent = new Intent(getActivity(), activityClass);
                    startActivity(intent);
                    return true;
                });
            }
        }

        private void logout() {
            // Clear user session data (if any)
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();

            // Redirect to LoginActivity
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            requireActivity().finish();
        }
    }
}
