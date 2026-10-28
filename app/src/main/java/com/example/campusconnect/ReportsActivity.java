package com.example.campusconnect;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.campusconnect.databinding.ActivityReportsBinding;

/**
 * ReportsActivity: Presents student academic performance metrics including
 * Attendance (92%), Cumulative GPA (3.4), and Enrolled Courses (5).
 * Built with a ConstraintLayout demonstrating progress indicators and stat cards.
 */
public class ReportsActivity extends AppCompatActivity {

    private ActivityReportsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Enable edge-to-edge system bar rendering
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        binding = ActivityReportsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Apply system bar insets to prevent status/navigation bar overlap
        ViewCompat.setOnApplyWindowInsetsListener(binding.layoutReportsRoot, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            binding.toolbarReports.setPadding(
                binding.toolbarReports.getPaddingLeft(),
                insets.top,
                binding.toolbarReports.getPaddingRight(),
                binding.toolbarReports.getPaddingBottom()
            );
            v.setPadding(insets.left, 0, insets.right, insets.bottom);
            return windowInsets;
        });

        setupToolbar();
        populateReportStats();
    }

    /**
     * Configures the toolbar with a back navigation arrow returning to DashboardActivity.
     */
    private void setupToolbar() {
        setSupportActionBar(binding.toolbarReports);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbarReports.setNavigationOnClickListener(v -> finish());
    }

    /**
     * Populates hardcoded academic statistics and visual progress indicators.
     */
    private void populateReportStats() {
        // Attendance: 92%
        binding.tvAttendanceValue.setText(getString(R.string.report_attendance_value));
        binding.progressAttendance.setProgress(92);

        // Cumulative GPA: 3.4 / 4.0 (85% on 4.0 scale)
        binding.tvGpaValue.setText(getString(R.string.report_gpa_value));
        binding.progressGpa.setProgress(85);

        // Courses Enrolled: 5 Active (out of typical 6 course full semester)
        binding.tvCoursesEnrolledValue.setText(getString(R.string.report_courses_value));
        binding.progressCourses.setProgress(83);
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
