package com.example.campusconnect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campusconnect.databinding.ItemCourseBinding;

import java.util.List;

/**
 * CoursesAdapter: RecyclerView adapter presenting enrolled courses inside MaterialCardViews.
 */
public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {

    private final List<Course> courseList;

    public CoursesAdapter(List<Course> courseList) {
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCourseBinding binding = ItemCourseBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CourseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.bind(course);
    }

    @Override
    public int getItemCount() {
        return courseList != null ? courseList.size() : 0;
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        private final ItemCourseBinding binding;

        public CourseViewHolder(ItemCourseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Course course) {
            Context context = itemView.getContext();
            binding.tvCourseName.setText(course.getName());
            binding.tvCourseCode.setText(context.getString(R.string.label_course_code, course.getCode()));
            binding.tvCourseInstructor.setText(context.getString(R.string.label_instructor, course.getInstructor()));
            binding.tvCourseCredits.setText(context.getString(R.string.label_credits, course.getCreditHours()));
        }
    }
}
