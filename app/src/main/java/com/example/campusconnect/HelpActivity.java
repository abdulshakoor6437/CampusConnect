package com.example.campusconnect;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.campusconnect.databinding.ActivityHelpBinding;

/**
 * HelpActivity: Displays an interactive FAQ list with expandable answers
 * and provides a Contact Support action launching an email Intent.
 * Opened from DashboardActivity via explicit Intent.
 */
public class HelpActivity extends AppCompatActivity {

    private ActivityHelpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Enable edge-to-edge system bar rendering
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        binding = ActivityHelpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Apply system bar insets to prevent status and navigation bar overlap
        ViewCompat.setOnApplyWindowInsetsListener(binding.layoutHelpRoot, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            binding.toolbarHelp.setPadding(
                binding.toolbarHelp.getPaddingLeft(),
                insets.top,
                binding.toolbarHelp.getPaddingRight(),
                binding.toolbarHelp.getPaddingBottom()
            );
            v.setPadding(insets.left, 0, insets.right, insets.bottom);
            return windowInsets;
        });

        setupToolbar();
        setupFaqItems();
        setupContactSupport();
    }

    /**
     * Configures the toolbar with a back navigation arrow returning to DashboardActivity.
     */
    private void setupToolbar() {
        setSupportActionBar(binding.toolbarHelp);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbarHelp.setNavigationOnClickListener(v -> finish());
    }

    /**
     * Configures click listeners for each FAQ question header to expand/collapse its answer.
     */
    private void setupFaqItems() {
        binding.headerFaq1.setOnClickListener(v -> toggleFaqItem(binding.tvFaqAnswer1, binding.imgFaqArrow1));
        binding.headerFaq2.setOnClickListener(v -> toggleFaqItem(binding.tvFaqAnswer2, binding.imgFaqArrow2));
        binding.headerFaq3.setOnClickListener(v -> toggleFaqItem(binding.tvFaqAnswer3, binding.imgFaqArrow3));
        binding.headerFaq4.setOnClickListener(v -> toggleFaqItem(binding.tvFaqAnswer4, binding.imgFaqArrow4));
        binding.headerFaq5.setOnClickListener(v -> toggleFaqItem(binding.tvFaqAnswer5, binding.imgFaqArrow5));
    }

    /**
     * Toggles the visibility of a given FAQ answer TextView and rotates its arrow indicator.
     */
    private void toggleFaqItem(TextView answerView, ImageView arrowView) {
        if (answerView.getVisibility() == View.VISIBLE) {
            answerView.setVisibility(View.GONE);
            arrowView.animate().rotation(0f).setDuration(200).start();
        } else {
            answerView.setVisibility(View.VISIBLE);
            arrowView.animate().rotation(180f).setDuration(200).start();
        }
    }

    /**
     * Sets up the Contact Support button to dispatch an email Intent to support@campusconnect.example.
     */
    private void setupContactSupport() {
        binding.btnContactSupport.setOnClickListener(v -> {
            // Explicit ACTION_SENDTO mailto: Intent to contact campus support
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:support@campusconnect.example"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_support_subject));

            try {
                startActivity(emailIntent);
            } catch (ActivityNotFoundException e) {
                // If no email application is installed, provide feedback via Toast
                Toast.makeText(this, R.string.toast_support_contact, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
