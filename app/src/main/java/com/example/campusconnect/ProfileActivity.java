package com.example.campusconnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.campusconnect.databinding.ActivityProfileBinding;

/**
 * ProfileActivity: Displays user profile details received from Intent extras
 * and provides a Logout function that clears the back stack.
 * Demonstrates:
 * 1. ConstraintLayout layout demonstration (activity_profile.xml).
 * 2. Intent extras handling for user name and email.
 * 3. Clearing the activity task/back stack on logout via
 *    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK.
 */
public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        displayUserData();
        setupListeners();
    }

    /**
     * Reads and displays user information from the intent extras.
     */
    private void displayUserData() {
        Intent intent = getIntent();
        String name = ValidationUtils.DEFAULT_USER_NAME;
        String email = ValidationUtils.AUTH_EMAIL;

        if (intent != null) {
            String extraName = intent.getStringExtra(ValidationUtils.EXTRA_USER_NAME);
            if (extraName != null && !extraName.isEmpty()) {
                name = extraName;
            }

            String extraEmail = intent.getStringExtra(ValidationUtils.EXTRA_USER_EMAIL);
            if (extraEmail != null && !extraEmail.isEmpty()) {
                email = extraEmail;
            }
        }

        binding.tvProfileNameHeader.setText(name);
        binding.tvProfileDetailName.setText(name);
        binding.tvProfileDetailEmail.setText(email);
    }

    private void setupListeners() {
        // Back navigation on toolbar icon click
        binding.toolbarProfile.setNavigationOnClickListener(v -> finish());

        // Logout action: Return to LoginActivity and clear entire back stack
        binding.btnLogout.setOnClickListener(v -> performLogout());
    }

    /**
     * Logs the user out, cleans up the navigation back stack, and redirects to LoginActivity.
     */
    private void performLogout() {
        Toast.makeText(this, R.string.toast_logout_success, Toast.LENGTH_SHORT).show();

        // Explicit Intent to LoginActivity with cleared back stack flags
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
