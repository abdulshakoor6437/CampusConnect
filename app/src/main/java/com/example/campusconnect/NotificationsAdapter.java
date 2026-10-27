package com.example.campusconnect;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campusconnect.databinding.ItemNotificationBinding;

import java.util.List;

/**
 * NotificationsAdapter: RecyclerView adapter presenting alerts and notices.
 */
public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationViewHolder> {

    private final List<NotificationItem> notificationList;

    public NotificationsAdapter(List<NotificationItem> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNotificationBinding binding = ItemNotificationBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        NotificationItem item = notificationList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return notificationList != null ? notificationList.size() : 0;
    }

    static class NotificationViewHolder extends RecyclerView.ViewHolder {
        private final ItemNotificationBinding binding;

        public NotificationViewHolder(ItemNotificationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(NotificationItem item) {
            binding.imgNotifIcon.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), item.getIconResId()));
            binding.tvNotifTitle.setText(item.getTitle());
            binding.tvNotifMessage.setText(item.getMessage());
            binding.tvNotifTime.setText(item.getTime());
        }
    }
}
