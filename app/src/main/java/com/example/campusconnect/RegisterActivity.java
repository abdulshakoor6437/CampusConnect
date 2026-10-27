package com.example.campusconnect;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.campusconnect.databinding.ActivityRegisterBinding;

/**
 * RegisterActivity: Allows new campus users to create an account.
 * Demonstrates:
 * 1. LinearLayout layout demonstration (vertical form in activity_register.xml).
 * 2. Input validation through helper methods:
 *    - All fields required
 *    - Valid email pattern via Patterns.EMAIL_ADDRESS
 *    - Password at least 6 characters
 *    - Passwords match
 * 3. Shows Toast on success and returns to Login.
 */
public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupListeners();
    }

    private void setupListeners() {
        // Register button click
        binding.btnRegister.setOnClickListener(v -> performRegistration());

        // Back to Login link
        binding.btnLoginLink.setOnClickListener(v -> finish());
    }

    /**
     * Executes form validation and simulates account creation.
     */
    private void performRegistration() {
        // Clear previous error messages
        binding.tilRegisterName.setError(null);
        binding.tilRegisterEmail.setError(null);
        binding.tilRegisterPassword.setError(null);
        binding.tilRegisterConfirmPassword.setError(null);

        String name = "";
        if (binding.etRegisterName.getText() != null) {
            name = binding.etRegisterName.getText().toString().trim();
        }

        String email = "";
        if (binding.etRegisterEmail.getText() != null) {
            email = binding.etRegisterEmail.getText().toString().trim();
        }

        String password = "";
        if (binding.etRegisterPassword.getText() != null) {
            password = binding.etRegisterPassword.getText().toString();
        }

        String confirmPassword = "";
        if (binding.etRegisterConfirmPassword.getText() != null) {
            confirmPassword = binding.etRegisterConfirmPassword.getText().toString();
        }

        // Validate name (required)
        if (!ValidationUtils.isNotEmpty(name)) {
            binding.tilRegisterName.setError(getString(R.string.err_empty_name));
            binding.etRegisterName.requestFocus();
            return;
        }

        // Validate email (required & valid format)
        if (!ValidationUtils.isNotEmpty(email)) {
            binding.tilRegisterEmail.setError(getString(R.string.err_empty_email));
            binding.etRegisterEmail.requestFocus();
            return;
        }

        if (!isValidEmail(email)) {
            binding.tilRegisterEmail.setError(getString(R.string.err_invalid_email));
            binding.etRegisterEmail.requestFocus();
            return;
        }

        // Validate password (required & minimum length)
        if (!ValidationUtils.isNotEmpty(password)) {
            binding.tilRegisterPassword.setError(getString(R.string.err_empty_password));
            binding.etRegisterPassword.requestFocus();
            return;
        }

        if (!isValidPassword(password)) {
            binding.tilRegisterPassword.setError(getString(R.string.err_password_too_short));
            binding.etRegisterPassword.requestFocus();
            return;
        }

        // Validate confirm password (required & must match)
        if (!ValidationUtils.isNotEmpty(confirmPassword)) {
            binding.tilRegisterConfirmPassword.setError(getString(R.string.err_empty_confirm_password));
            binding.etRegisterConfirmPassword.requestFocus();
            return;
        }

        if (!passwordsMatch(password, confirmPassword)) {
            binding.tilRegisterConfirmPassword.setError(getString(R.string.err_passwords_mismatch));
            binding.etRegisterConfirmPassword.requestFocus();
            return;
        }

        // Registration success: Notify user and navigate back to LoginActivity
        Toast.makeText(this, R.string.toast_registration_success, Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * Helper validation function: Checks email format.
     */
    private boolean isValidEmail(String email) {
        return ValidationUtils.isValidEmail(email);
    }

    /**
     * Helper validation function: Checks minimum password length.
     */
    private boolean isValidPassword(String password) {
        return ValidationUtils.isValidPassword(password);
    }

    /**
     * Helper validation function: Checks if both passwords match.
     */
    private boolean passwordsMatch(String pass1, String pass2) {
        return ValidationUtils.passwordsMatch(pass1, pass2);
    }
}
