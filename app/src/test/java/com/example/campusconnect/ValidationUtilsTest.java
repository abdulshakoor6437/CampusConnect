package com.example.campusconnect;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for ValidationUtils logic.
 */
public class ValidationUtilsTest {

    @Test
    public void testIsValidPassword() {
        assertTrue(ValidationUtils.isValidPassword("Password@123"));
        assertTrue(ValidationUtils.isValidPassword("123456"));
        assertFalse(ValidationUtils.isValidPassword("12345"));
        assertFalse(ValidationUtils.isValidPassword(""));
        assertFalse(ValidationUtils.isValidPassword(null));
    }

    @Test
    public void testPasswordsMatch() {
        assertTrue(ValidationUtils.passwordsMatch("Password@123", "Password@123"));
        assertFalse(ValidationUtils.passwordsMatch("Password@123", "password@123"));
        assertFalse(ValidationUtils.passwordsMatch("Password@123", "DifferentPassword"));
        assertFalse(ValidationUtils.passwordsMatch(null, "Password@123"));
        assertFalse(ValidationUtils.passwordsMatch("Password@123", null));
    }

    @Test
    public void testCheckCredentials() {
        assertTrue(ValidationUtils.checkCredentials("user@example.com", "Password@123"));
        // Case-insensitive email check
        assertTrue(ValidationUtils.checkCredentials("USER@EXAMPLE.COM", "Password@123"));
        assertTrue(ValidationUtils.checkCredentials("  user@example.com  ", "Password@123"));

        // Invalid credentials
        assertFalse(ValidationUtils.checkCredentials("user@example.com", "WrongPassword"));
        assertFalse(ValidationUtils.checkCredentials("wrong@example.com", "Password@123"));
        assertFalse(ValidationUtils.checkCredentials(null, "Password@123"));
        assertFalse(ValidationUtils.checkCredentials("user@example.com", null));
    }

    @Test
    public void testIsNotEmpty() {
        assertTrue(ValidationUtils.isNotEmpty("John"));
        assertFalse(ValidationUtils.isNotEmpty(""));
        assertFalse(ValidationUtils.isNotEmpty("   "));
        assertFalse(ValidationUtils.isNotEmpty(null));
    }
}
