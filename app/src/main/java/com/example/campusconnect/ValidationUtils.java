package com.example.campusconnect;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Utility helper functions for input validation, credential verification,
 * and common Intent extra constants.
 */
public final class ValidationUtils {

    // Hardcoded demo credentials as required
    public static final String AUTH_EMAIL = "user@example.com";
    public static final String AUTH_PASSWORD = "Password@123";
    public static final String DEFAULT_USER_NAME = "Alex Johnson";

    // Intent Extra Keys
    public static final String EXTRA_USER_NAME = "com.example.campusconnect.EXTRA_USER_NAME";
    public static final String EXTRA_USER_EMAIL = "com.example.campusconnect.EXTRA_USER_EMAIL";

    private ValidationUtils() {
        // Prevent instantiation
    }

    /**
     * Validates whether an email string is non-empty and follows RFC email pattern.
     *
     * @param email The email sequence to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidEmail(CharSequence email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Validates whether a password satisfies the minimum length requirement (at least 6 characters).
     *
     * @param password The password string
     * @return true if non-null and at least 6 characters
     */
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }

    /**
     * Validates whether two passwords match exactly and are non-empty.
     *
     * @param pass1 Primary password
     * @param pass2 Repeated confirmation password
     * @return true if both are non-empty and equal
     */
    public static boolean passwordsMatch(String pass1, String pass2) {
        return pass1 != null && pass1.equals(pass2);
    }

    /**
     * Checks if a string has non-whitespace characters.
     *
     * @param text The character sequence to inspect
     * @return true if not empty after trim
     */
    public static boolean isNotEmpty(CharSequence text) {
        return text != null && !text.toString().trim().isEmpty();
    }

    /**
     * Checks if given credentials match the hardcoded authentication values.
     *
     * @param email User entered email
     * @param password User entered password
     * @return true if matching demo credentials
     */
    public static boolean checkCredentials(String email, String password) {
        if (email == null || password == null) return false;
        return AUTH_EMAIL.equalsIgnoreCase(email.trim()) && AUTH_PASSWORD.equals(password);
    }
}
