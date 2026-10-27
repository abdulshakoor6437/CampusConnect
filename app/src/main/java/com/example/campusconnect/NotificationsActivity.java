package com.example.campusconnect;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.campusconnect.databinding.ActivityNotificationsBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * NotificationsActivity: Displays a list of 5-6 hardcoded notifications
 * or an empty-state message if no notifications are present.
 * Opened from DashboardActivity via explicit Intent.
 */
public class NotificationsActivity extends AppCompatActivity {

    private ActivityNotificationsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Enable edge-to-edge system bar rendering
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Apply system bar window insets for status and navigation bars
        ViewCompat.setOnApplyWindowInsetsListener(binding.layoutNotificationsRoot, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            binding.toolbarNotifications.setPadding(
                binding.toolbarNotifications.getPaddingLeft(),
                insets.top,
                binding.toolbarNotifications.getPaddingRight(),
                binding.toolbarNotifications.getPaddingBottom()
            );
            v.setPadding(insets.left, 0, insets.right, insets.bottom);
            return windowInsets;
        });

        setupToolbar();
        setupRecyclerView();
    }

    /**
     * Configures the toolbar with a back navigation arrow returning to DashboardActivity.
     */
    private void setupToolbar() {
        setSupportActionBar(binding.toolbarNotifications);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbarNotifications.setNavigationOnClickListener(v -> finish());
    }

    /**
     * Loads notifications and binds them to the RecyclerView, or reveals the empty state view.
     */
    private void setupRecyclerView() {
        List<NotificationItem> items = new ArrayList<>();
        items.add(new NotificationItem(
                R.drawable.ic_assignment,
                getString(R.string.notif_1_title),
                getString(R.string.notif_1_msg),
                getString(R.string.notif_1_time)
        ));
        items.add(new NotificationItem(
                R.drawable.ic_schedule,
                getString(R.string.notif_2_title),
                getString(R.string.notif_2_msg),
                getString(R.string.notif_2_time)
        ));
        items.add(new NotificationItem(
                R.drawable.ic_reports,
                getString(R.string.notif_3_title),
                getString(R.string.notif_3_msg),
                getString(R.string.notif_3_time)
        ));
        items.add(new NotificationItem(
                R.drawable.ic_courses,
                getString(R.string.notif_4_title),
                getString(R.string.notif_4_msg),
                getString(R.string.notif_4_time)
        ));
        items.add(new NotificationItem(
                R.drawable.ic_notifications,
                getString(R.string.notif_5_title),
                getString(R.string.notif_5_msg),
                getString(R.string.notif_5_time)
        ));
        items.add(new NotificationItem(
                R.drawable.ic_profile,
                getString(R.string.notif_6_title),
                getString(R.string.notif_6_msg),
                getString(R.string.notif_6_time)
        ));

        // Handle empty-state condition
        if (items.isEmpty()) {
            binding.tvEmptyNotifications.setVisibility(View.VISIBLE);
            binding.rvNotifications.setVisibility(View.GONE);
        } else {
            binding.tvEmptyNotifications.setVisibility(View.GONE);
            binding.rvNotifications.setVisibility(View.VISIBLE);

            NotificationsAdapter adapter = new NotificationsAdapter(items);
            binding.rvNotifications.setLayoutManager(new LinearLayoutManager(this));
            binding.rvNotifications.setAdapter(adapter);
        }
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
