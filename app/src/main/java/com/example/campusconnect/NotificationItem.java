package com.example.campusconnect;

/**
 * NotificationItem: Model class representing a campus alert or announcement.
 */
public class NotificationItem {
    private final int iconResId;
    private final String title;
    private final String message;
    private final String time;

    public NotificationItem(int iconResId, String title, String message, String time) {
        this.iconResId = iconResId;
        this.title = title;
        this.message = message;
        this.time = time;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
