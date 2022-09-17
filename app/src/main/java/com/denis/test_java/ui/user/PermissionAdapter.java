package com.denis.test_java.ui.user;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.denis.test_java.databinding.PermissionItemBinding;

import java.util.List;

public class PermissionAdapter extends RecyclerView.Adapter<PermissionAdapter.ViewHolder> {

    private final List<String> permissions;

    PermissionAdapter(List<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public PermissionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(PermissionItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        ));
    }

    @Override
    public void onBindViewHolder(PermissionAdapter.ViewHolder holder, int position) {
        holder.bind(permissions);
    }

    @Override
    public int getItemCount() {
        return permissions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private PermissionItemBinding binding;

        public ViewHolder(PermissionItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(List<String> permissions) {
            binding.permission.setText(permissions.get(getAdapterPosition()));
        }
    }
}