package com.example.asg02.view.ui.recovery.reset;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.EditText;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.asg02.databinding.FragmentResetPasswordBinding;
import com.example.asg02.view.LoginActivity;
import com.example.asg02.R;

public class ResetPasswordFragment extends Fragment {
    private ResetPasswordViewModel mViewModel;
    private FragmentResetPasswordBinding binding;
    private boolean isHidePassword = true;
    private boolean isHideConfirmPassword = true;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ResetPasswordViewModel.class);

        binding = FragmentResetPasswordBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        changePasswordVisibility(binding.newPassword, isHidePassword);
        changePasswordVisibility(binding.confirmNewPassword, isHideConfirmPassword);

        binding.newPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (event.getRawX() >= (binding.newPassword.getRight() - binding.newPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width() - 32)) {
                    isHidePassword = !isHidePassword;
                    changePasswordVisibility(binding.newPassword, isHidePassword);
                    return true;
                }
            }
            return false;
        });

        binding.confirmNewPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (event.getRawX() >= (binding.confirmNewPassword.getRight() - binding.confirmNewPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width() - 32)) {
                    isHideConfirmPassword = !isHideConfirmPassword;
                    changePasswordVisibility(binding.confirmNewPassword, isHideConfirmPassword);
                    return true;
                }
            }
            return false;
        });

        binding.finishResetPassword.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });

        return root;
    }

    private void changePasswordVisibility(EditText editText, boolean isHidePassword) {
        if (isHidePassword) {
            editText.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.hide_password_icon), null);
            editText.setTransformationMethod(new PasswordTransformationMethod());
        } else {
            editText.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.show_password_icon), null);
            editText.setTransformationMethod(null);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}