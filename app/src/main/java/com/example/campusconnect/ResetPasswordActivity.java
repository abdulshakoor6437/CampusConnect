package com.example.campusconnect;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.campusconnect.databinding.ActivityResetPasswordBinding;

/**
 * ResetPasswordActivity: Allows setting a new password following account recovery.
 * Demonstrates:
 * 1. Password validation: minimum 6 characters and password matching.
 * 2. Toast feedback on success.
 * 3. Navigates back to LoginActivity.
 */
public class ResetPasswordActivity extends AppCompatActivity {

    private ActivityResetPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupListeners();
    }

    private void setupListeners() {
        // Reset button click
        binding.btnResetPassword.setOnClickListener(v -> performPasswordReset());

        // Back to Login link
        binding.btnResetBackToLogin.setOnClickListener(v -> finish());
    }

    /**
     * Validates new passwords and simulates password update.
     */
    private void performPasswordReset() {
        binding.tilResetNewPassword.setError(null);
        binding.tilResetConfirmPassword.setError(null);

        String newPassword = "";
        if (binding.etResetNewPassword.getText() != null) {
            newPassword = binding.etResetNewPassword.getText().toString();
        }

        String confirmPassword = "";
        if (binding.etResetConfirmPassword.getText() != null) {
            confirmPassword = binding.etResetConfirmPassword.getText().toString();
        }

        // Validate empty new password
        if (!ValidationUtils.isNotEmpty(newPassword)) {
            binding.tilResetNewPassword.setError(getString(R.string.err_empty_password));
            binding.etResetNewPassword.requestFocus();
            return;
        }

        // Validate minimum length
        if (!ValidationUtils.isValidPassword(newPassword)) {
            binding.tilResetNewPassword.setError(getString(R.string.err_password_too_short));
            binding.etResetNewPassword.requestFocus();
            return;
        }

        // Validate confirm password
        if (!ValidationUtils.isNotEmpty(confirmPassword)) {
            binding.tilResetConfirmPassword.setError(getString(R.string.err_empty_confirm_password));
            binding.etResetConfirmPassword.requestFocus();
            return;
        }

        // Validate passwords match
        if (!ValidationUtils.passwordsMatch(newPassword, confirmPassword)) {
            binding.tilResetConfirmPassword.setError(getString(R.string.err_passwords_mismatch));
            binding.etResetConfirmPassword.requestFocus();
            return;
        }

        // Password reset success
        Toast.makeText(this, R.string.toast_password_reset_success, Toast.LENGTH_SHORT).show();
        finish();
    }
}
