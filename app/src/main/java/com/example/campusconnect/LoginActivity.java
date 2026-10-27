package com.example.campusconnect;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.campusconnect.databinding.ActivityLoginBinding;

/**
 * LoginActivity: Handles user sign-in with simulated local authentication.
 * Demonstrates:
 * 1. LinearLayout layout demonstration (vertical form in activity_login.xml).
 * 2. Input validation for empty fields and simulated credentials (user@example.com / Password@123).
 * 3. Clear error indications via TextInputLayout setError().
 * 4. Explicit Intents to RegisterActivity, ForgotPasswordActivity, and DashboardActivity.
 * 5. Clearing back stack on successful login using Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK.
 */
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        // Initialize ViewBinding for type-safe view binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.scrollLogin, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(insets.left, insets.top, insets.right, insets.bottom);
            return windowInsets;
        });

        setupListeners();
    }

    private void setupListeners() {
        // Login button click handler
        binding.btnLogin.setOnClickListener(v -> performLogin());

        // Navigate to RegisterActivity via explicit Intent
        binding.btnRegisterLink.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Navigate to ForgotPasswordActivity via explicit Intent
        binding.btnForgotPasswordLink.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Validates input fields and authenticates credentials locally.
     */
    private void performLogin() {
        // Clear previous error states
        binding.tilLoginEmail.setError(null);
        binding.tilLoginPassword.setError(null);

        String email = "";
        if (binding.etLoginEmail.getText() != null) {
            email = binding.etLoginEmail.getText().toString().trim();
        }

        String password = "";
        if (binding.etLoginPassword.getText() != null) {
            password = binding.etLoginPassword.getText().toString();
        }

        // Validate empty email
        if (!ValidationUtils.isNotEmpty(email)) {
            binding.tilLoginEmail.setError(getString(R.string.err_empty_email));
            binding.etLoginEmail.requestFocus();
            return;
        }

        // Validate email format
        if (!ValidationUtils.isValidEmail(email)) {
            binding.tilLoginEmail.setError(getString(R.string.err_invalid_email));
            binding.etLoginEmail.requestFocus();
            return;
        }

        // Validate empty password
        if (!ValidationUtils.isNotEmpty(password)) {
            binding.tilLoginPassword.setError(getString(R.string.err_empty_password));
            binding.etLoginPassword.requestFocus();
            return;
        }

        // Validate credentials against hardcoded user@example.com / Password@123
        if (!ValidationUtils.checkCredentials(email, password)) {
            binding.tilLoginPassword.setError(getString(R.string.err_invalid_credentials));
            return;
        }

        // Successful authentication: Navigate to DashboardActivity
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        // Pass the user's name and email as Intent extras
        intent.putExtra(ValidationUtils.EXTRA_USER_NAME, ValidationUtils.DEFAULT_USER_NAME);
        intent.putExtra(ValidationUtils.EXTRA_USER_EMAIL, email);

        // Clear the back stack so user cannot go back to login screen via system back
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
