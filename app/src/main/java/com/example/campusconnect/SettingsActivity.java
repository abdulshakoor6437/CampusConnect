package com.example.campusconnect;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.campusconnect.databinding.ActivitySettingsBinding;

/**
 * SettingsActivity: Provides application configuration controls including
 * runtime Dark Mode toggling via AppCompatDelegate and push notification preferences.
 * Uses a LinearLayout hierarchy as demonstrated in activity_settings.xml.
 */
public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Enable edge-to-edge system bar rendering
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Apply system bar insets to prevent status and navigation bar clipping
        ViewCompat.setOnApplyWindowInsetsListener(binding.layoutSettingsRoot, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            binding.toolbarSettings.setPadding(
                binding.toolbarSettings.getPaddingLeft(),
                insets.top,
                binding.toolbarSettings.getPaddingRight(),
                binding.toolbarSettings.getPaddingBottom()
            );
            v.setPadding(insets.left, 0, insets.right, insets.bottom);
            return windowInsets;
        });

        prefs = getSharedPreferences(CampusConnectApp.PREFS_NAME, MODE_PRIVATE);

        setupToolbar();
        setupControls();
    }

    /**
     * Configures the toolbar with a back navigation arrow returning to DashboardActivity.
     */
    private void setupToolbar() {
        setSupportActionBar(binding.toolbarSettings);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbarSettings.setNavigationOnClickListener(v -> finish());
    }

    /**
     * Configures the Dark Mode switch, Notification switch, and About version details.
     */
    private void setupControls() {
        // 1. Dark Mode Switch: Persists state and updates night mode dynamically
        boolean isDarkMode = prefs.getBoolean(CampusConnectApp.KEY_DARK_MODE, false);
        binding.switchDarkMode.setChecked(isDarkMode);
        binding.switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean(CampusConnectApp.KEY_DARK_MODE, isChecked).apply();
            // Dynamically apply Day/Night theme
            AppCompatDelegate.setDefaultNightMode(
                isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
            );
        });

        // 2. Notifications Switch: Displays Toast feedback and stores preference
        boolean isNotificationsEnabled = prefs.getBoolean(CampusConnectApp.KEY_NOTIFICATIONS, true);
        binding.switchNotifications.setChecked(isNotificationsEnabled);
        binding.switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean(CampusConnectApp.KEY_NOTIFICATIONS, isChecked).apply();
            String message = isChecked
                    ? getString(R.string.toast_notifications_enabled)
                    : getString(R.string.toast_notifications_disabled);
            Toast.makeText(SettingsActivity.this, message, Toast.LENGTH_SHORT).show();
        });

        // 3. App Version Display
        binding.tvAppVersion.setText(getString(R.string.app_version));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
