package com.denis.test_java.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.denis.test_java.databinding.SignInFragmentBinding;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignInFragment extends Fragment {

    private SignInFragmentBinding binding;
    private SignInViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = SignInFragmentBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        signInClick();
        signInEvent(view);

        errorEvent();
    }

    private void signInClick() {
        binding.signInButton.setOnClickListener(button -> viewModel.login(
                Objects.requireNonNull(binding.login.getEditText()).getText().toString(),
                Objects.requireNonNull(binding.password.getEditText()).getText().toString()
        ));
    }

    private void signInEvent(@NonNull View view) {
        viewModel.loading.observe(getViewLifecycleOwner(), loading -> binding.progressBar.setVisibility(loading ? View.VISIBLE : View.GONE));

        viewModel.success.observe(getViewLifecycleOwner(), success -> Navigation.findNavController(view).navigate(SignInFragmentDirections.actionSignInToUser()));
    }

    private void errorEvent() {
        viewModel.error.observe(getViewLifecycleOwner(), error -> {
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
        });
    }
}
