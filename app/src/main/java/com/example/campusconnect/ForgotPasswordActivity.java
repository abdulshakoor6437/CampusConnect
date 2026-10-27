package com.example.campusconnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.campusconnect.databinding.ActivityForgotPasswordBinding;
import com.google.android.material.snackbar.Snackbar;

/**
 * ForgotPasswordActivity: Initiates account recovery by email.
 * Demonstrates:
 * 1. ConstraintLayout layout demonstration (activity_forgot_password.xml).
 * 2. Email format validation via ValidationUtils.isValidEmail().
 * 3. Feedback via Toast/Snackbar ("Reset link sent").
 * 4. Explicit Intent navigating to ResetPasswordActivity.
 */
public class ForgotPasswordActivity extends AppCompatActivity {

    private ActivityForgotPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupListeners();
    }

    private void setupListeners() {
        // Send reset link button click
        binding.btnSendResetLink.setOnClickListener(v -> handleSendResetLink());

        // Back to Login link
        binding.btnForgotBackToLogin.setOnClickListener(v -> finish());
    }

    /**
     * Validates email and simulates sending password reset instructions.
     */
    private void handleSendResetLink() {
        binding.tilForgotEmail.setError(null);

        String email = "";
        if (binding.etForgotEmail.getText() != null) {
            email = binding.etForgotEmail.getText().toString().trim();
        }

        // Validate empty email
        if (!ValidationUtils.isNotEmpty(email)) {
            binding.tilForgotEmail.setError(getString(R.string.err_empty_email));
            binding.etForgotEmail.requestFocus();
            return;
        }

        // Validate email format
        if (!ValidationUtils.isValidEmail(email)) {
            binding.tilForgotEmail.setError(getString(R.string.err_invalid_email));
            binding.etForgotEmail.requestFocus();
            return;
        }

        // Feedback simulation via Toast
        Toast.makeText(this, R.string.msg_reset_link_sent, Toast.LENGTH_SHORT).show();

        // Explicit Intent navigating to ResetPasswordActivity
        Intent intent = new Intent(ForgotPasswordActivity.this, ResetPasswordActivity.class);
        startActivity(intent);
        finish();
    }
}
