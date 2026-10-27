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

        // 2. Courses Module: Shows Toast
        binding.cardModuleCourses.setOnClickListener(v ->
                showModuleToast(getString(R.string.module_courses)));

        // 3. Notifications Module: Shows Toast
        binding.cardModuleNotifications.setOnClickListener(v ->
                showModuleToast(getString(R.string.module_notifications)));

        // 4. Settings Module: Shows Toast
        binding.cardModuleSettings.setOnClickListener(v ->
                showModuleToast(getString(R.string.module_settings)));

        // 5. Reports Module: Shows Toast
        binding.cardModuleReports.setOnClickListener(v ->
                showModuleToast(getString(R.string.module_reports)));

        // 6. Help Module: Shows Toast
        binding.cardModuleHelp.setOnClickListener(v ->
                showModuleToast(getString(R.string.module_help)));
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
        Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
        intent.putExtra(ValidationUtils.EXTRA_USER_NAME, userName);
        intent.putExtra(ValidationUtils.EXTRA_USER_EMAIL, userEmail);
        startActivity(intent);
    }
}
