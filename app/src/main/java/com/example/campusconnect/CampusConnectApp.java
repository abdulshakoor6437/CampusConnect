package com.example.campusconnect;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

/**
 * CampusConnectApp: Application subclass initializing global configurations
 * such as the persistent Dark Mode preference on application startup.
 */
public class CampusConnectApp extends Application {

    public static final String PREFS_NAME = "campusconnect_prefs";
    public static final String KEY_DARK_MODE = "dark_mode_enabled";
    public static final String KEY_NOTIFICATIONS = "notifications_enabled";

    @Override
    public void onCreate() {
        super.onCreate();
        // Load and apply saved night mode theme preference on startup
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isDarkMode = prefs.getBoolean(KEY_DARK_MODE, false);
        AppCompatDelegate.setDefaultNightMode(
            isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );
    }
}
