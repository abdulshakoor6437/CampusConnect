package com.example.campusconnect;

/**
 * Course: Model class representing a registered academic course.
 */
public class Course {
    private final String name;
    private final String code;
    private final String instructor;
    private final int creditHours;

    public Course(String name, String code, String instructor, int creditHours) {
        this.name = name;
        this.code = code;
        this.instructor = instructor;
        this.creditHours = creditHours;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getInstructor() {
        return instructor;
    }

    public int getCreditHours() {
        return creditHours;
    }
}
