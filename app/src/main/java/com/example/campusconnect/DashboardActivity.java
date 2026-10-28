package com.example.campusconnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.campusconnect.databinding.ActivityDashboardBinding;

/**
 * DashboardActivity: Central hub displaying student services in a 2-column GridLayout.
 * Demonstrates:
 * 1. GridLayout layout demonstration (2-column module grid in activity_dashboard.xml).
 * 2. Toolbar menu integration with Profile navigation.
 * 3. Handling card clicks with feedback Toasts.
 * 4. Navigating to ProfileActivity via explicit Intent, forwarding user data extras.
 */
public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;
    private String userName;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.layoutDashboardRoot, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            binding.toolbarDashboard.setPadding(
                binding.toolbarDashboard.getPaddingLeft(),
                insets.top,
                binding.toolbarDashboard.getPaddingRight(),
                binding.toolbarDashboard.getPaddingBottom()
            );
            v.setPadding(insets.left, 0, insets.right, insets.bottom);
            return windowInsets;
        });

        extractIntentExtras();
        setupToolbar();
        setupModuleCards();
    }

    /**
     * Extracts user data passed via Intent extras from LoginActivity.
     */
    private void extractIntentExtras() {
        Intent intent = getIntent();
        if (intent != null) {
            userName = intent.getStringExtra(ValidationUtils.EXTRA_USER_NAME);
            userEmail = intent.getStringExtra(ValidationUtils.EXTRA_USER_EMAIL);
        }

        // Default fallbacks if extras are null
        if (userName == null || userName.isEmpty()) {
            userName = ValidationUtils.DEFAULT_USER_NAME;
        }
        if (userEmail == null || userEmail.isEmpty()) {
            userEmail = ValidationUtils.AUTH_EMAIL;
        }

        // Display personalized welcome text
        binding.tvDashboardWelcome.setText(getString(R.string.dashboard_welcome, userName));
    }

    /**
     * Configures the MaterialToolbar and menu item selection.
     */
    private void setupToolbar() {
        binding.toolbarDashboard.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_profile) {
                openProfile();
                return true;
            }
            return false;
        });
    }

    /**
     * Sets up click listeners for all dashboard cards.
     */
    private void setupModuleCards() {
        // 1. Profile Module: Opens ProfileActivity
        binding.cardModuleProfile.setOnClickListener(v -> {
            showModuleToast(getString(R.string.module_profile));
            openProfile();
        });

        // 2. Courses Module: Opens CoursesActivity
        binding.cardModuleCourses.setOnClickListener(v -> openCourses());

        // 3. Notifications Module: Opens NotificationsActivity
        binding.cardModuleNotifications.setOnClickListener(v -> openNotifications());

        // 4. Settings Module: Opens SettingsActivity
        binding.cardModuleSettings.setOnClickListener(v -> openSettings());

        // 5. Reports Module: Opens ReportsActivity
        binding.cardModuleReports.setOnClickListener(v -> openReports());

        // 6. Help Module: Opens HelpActivity
        binding.cardModuleHelp.setOnClickListener(v -> openHelp());
    }

    /**
     * Helper to show consistent module feedback Toast.
     */
    private void showModuleToast(String moduleName) {
        Toast.makeText(this, getString(R.string.msg_module_clicked, moduleName), Toast.LENGTH_SHORT).show();
    }

    /**
     * Navigates to ProfileActivity passing user name and email.
     */
    private void openProfile() {
        // Explicit Intent to open ProfileActivity
        Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
        intent.putExtra(ValidationUtils.EXTRA_USER_NAME, userName);
        intent.putExtra(ValidationUtils.EXTRA_USER_EMAIL, userEmail);
        startActivity(intent);
    }

    /**
     * Navigates to CoursesActivity passing user name and email.
     */
    private void openCourses() {
        // Explicit Intent to open CoursesActivity
        Intent intent = new Intent(DashboardActivity.this, CoursesActivity.class);
        intent.putExtra(ValidationUtils.EXTRA_USER_NAME, userName);
        intent.putExtra(ValidationUtils.EXTRA_USER_EMAIL, userEmail);
        startActivity(intent);
    }

    /**
     * Navigates to NotificationsActivity passing user name and email.
     */
    private void openNotifications() {
        // Explicit Intent to open NotificationsActivity
        Intent intent = new Intent(DashboardActivity.this, NotificationsActivity.class);
        intent.putExtra(ValidationUtils.EXTRA_USER_NAME, userName);
        intent.putExtra(ValidationUtils.EXTRA_USER_EMAIL, userEmail);
        startActivity(intent);
    }

    /**
     * Navigates to SettingsActivity passing user name and email.
     */
    private void openSettings() {
        // Explicit Intent to open SettingsActivity
        Intent intent = new Intent(DashboardActivity.this, SettingsActivity.class);
        intent.putExtra(ValidationUtils.EXTRA_USER_NAME, userName);
        intent.putExtra(ValidationUtils.EXTRA_USER_EMAIL, userEmail);
        startActivity(intent);
    }

    /**
     * Navigates to ReportsActivity passing user name and email.
     */
    private void openReports() {
        // Explicit Intent to open ReportsActivity
        Intent intent = new Intent(DashboardActivity.this, ReportsActivity.class);
        intent.putExtra(ValidationUtils.EXTRA_USER_NAME, userName);
        intent.putExtra(ValidationUtils.EXTRA_USER_EMAIL, userEmail);
        startActivity(intent);
    }

    /**
     * Navigates to HelpActivity passing user name and email.
     */
    private void openHelp() {
        // Explicit Intent to open HelpActivity
        Intent intent = new Intent(DashboardActivity.this, HelpActivity.class);
        intent.putExtra(ValidationUtils.EXTRA_USER_NAME, userName);
        intent.putExtra(ValidationUtils.EXTRA_USER_EMAIL, userEmail);
        startActivity(intent);
    }
}
