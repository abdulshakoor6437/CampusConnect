package com.example.campusconnect;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.campusconnect.databinding.ActivitySplashBinding;

/**
 * SplashActivity: Launcher activity for the CampusConnect application.
 * Demonstrates:
 * 1. ConstraintLayout layout hierarchy (activity_splash.xml).
 * 2. Auto-navigation via Handler postDelayed (~2000ms delay).
 * 3. Explicit Intent to LoginActivity with back-stack removal via finish().
 */
public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY_MS = 2000L;
    private ActivitySplashBinding binding;
    private final Handler splashHandler = new Handler(Looper.getMainLooper());
    private Runnable navigateRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        // Initialize ViewBinding for type-safe view access
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int basePadding = getResources().getDimensionPixelSize(R.dimen.screen_padding);
        ViewCompat.setOnApplyWindowInsetsListener(binding.layoutSplashRoot, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(
                insets.left + basePadding,
                insets.top + basePadding,
                insets.right + basePadding,
                insets.bottom + basePadding
            );
            return windowInsets;
        });

        // Prepare navigation runnable to launch LoginActivity
        navigateRunnable = () -> {
            // Explicit Intent to navigate from SplashActivity to LoginActivity
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);

            // Remove SplashActivity from the back stack so user cannot return to it via Back button
            finish();
        };

        // Post delayed task for 2 seconds (~2000ms)
        splashHandler.postDelayed(navigateRunnable, SPLASH_DELAY_MS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Prevent memory leak if activity is destroyed before timer fires
        if (navigateRunnable != null) {
            splashHandler.removeCallbacks(navigateRunnable);
        }
    }
}
