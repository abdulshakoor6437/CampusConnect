package com.example.campusconnect;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.campusconnect.databinding.ActivityCoursesBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * CoursesActivity: Displays a scrollable list of 5 hardcoded courses
 * using a RecyclerView, custom Adapter, and MaterialCardViews.
 * Opened from DashboardActivity via explicit Intent.
 */
public class CoursesActivity extends AppCompatActivity {

    private ActivityCoursesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Enable edge-to-edge system bar rendering
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        binding = ActivityCoursesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Apply system bar insets to prevent status/navigation bar overlap
        ViewCompat.setOnApplyWindowInsetsListener(binding.layoutCoursesRoot, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            binding.toolbarCourses.setPadding(
                binding.toolbarCourses.getPaddingLeft(),
                insets.top,
                binding.toolbarCourses.getPaddingRight(),
                binding.toolbarCourses.getPaddingBottom()
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
        setSupportActionBar(binding.toolbarCourses);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbarCourses.setNavigationOnClickListener(v -> finish());
    }

    /**
     * Initializes the RecyclerView with a list of 5 hardcoded courses.
     */
    private void setupRecyclerView() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course(
                getString(R.string.course_1_name),
                getString(R.string.course_1_code),
                getString(R.string.course_1_instructor),
                3
        ));
        courses.add(new Course(
                getString(R.string.course_2_name),
                getString(R.string.course_2_code),
                getString(R.string.course_2_instructor),
                4
        ));
        courses.add(new Course(
                getString(R.string.course_3_name),
                getString(R.string.course_3_code),
                getString(R.string.course_3_instructor),
                3
        ));
        courses.add(new Course(
                getString(R.string.course_4_name),
                getString(R.string.course_4_code),
                getString(R.string.course_4_instructor),
                3
        ));
        courses.add(new Course(
                getString(R.string.course_5_name),
                getString(R.string.course_5_code),
                getString(R.string.course_5_instructor),
                4
        ));

        CoursesAdapter adapter = new CoursesAdapter(courses);
        binding.rvCourses.setLayoutManager(new LinearLayoutManager(this));
        binding.rvCourses.setAdapter(adapter);
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
