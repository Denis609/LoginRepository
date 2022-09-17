package com.denis.test_java.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.denis.test_java.R;
import com.denis.test_java.databinding.UserFragmentBinding;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserFragment extends Fragment {
    private UserFragmentBinding binding;
    private UserViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = UserFragmentBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        viewModel.getUser();
        initUser();
        errorEvent();

        viewModel.loading.observe(getViewLifecycleOwner(), loading -> {
            binding.progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
        });
    }

    private void initUser() {
        viewModel.user.observe(getViewLifecycleOwner(), userDto -> {
            binding.roleId.setText(
                    getString(
                            R.string.role_id,
                            userDto.getRoleId() != null ? userDto.getRoleId() : "Empty"
                    ));
            binding.username.setText(
                    getString(
                            R.string.username,
                            userDto.getUsername() != null ? userDto.getUsername() : "Empty"
                    ));
            binding.email.setText(
                    getString(
                            R.string.email,
                            userDto.getEmail() != null ? userDto.getEmail() : "Empty"
                    ));
            initAdapter(userDto.getPermissions());
        });

    }

    private void initAdapter(List<String> permissions) {
        PermissionAdapter adapter = new PermissionAdapter(permissions);
        binding.items.setAdapter(adapter);
    }

    private void errorEvent() {
        viewModel.error.observe(getViewLifecycleOwner(), error -> Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show());
    }
}
