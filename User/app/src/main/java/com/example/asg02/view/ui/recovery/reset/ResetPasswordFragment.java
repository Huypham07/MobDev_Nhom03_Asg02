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
import android.widget.LinearLayout;

import com.example.asg02.databinding.FragmentResetPasswordBinding;
import com.example.asg02.view.LoginActivity;
import com.example.asg02.R;
import com.example.asg02.view.Utils;

public class ResetPasswordFragment extends Fragment {
    private ResetPasswordViewModel mViewModel;
    private FragmentResetPasswordBinding binding;
    private boolean isHidePassword = true;
    private boolean isHideConfirmPassword = true;
    private EditText editNewPassword;
    private EditText editConfirmPassword;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ResetPasswordViewModel.class);

        binding = FragmentResetPasswordBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // assign

        editNewPassword = binding.newPassword;
        editConfirmPassword = binding.confirmNewPassword;

        changePasswordVisibility(binding.newPassword, isHidePassword);
        changePasswordVisibility(binding.confirmNewPassword, isHideConfirmPassword);

        editNewPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (event.getRawX() >= (editNewPassword.getRight() - editNewPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width() - 32)) {
                    isHidePassword = !isHidePassword;
                    changePasswordVisibility(editNewPassword, isHidePassword);
                    return true;
                }
            }
            return false;
        });
        editConfirmPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (event.getRawX() >= (editConfirmPassword.getRight() - editConfirmPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width() - 32)) {
                    isHideConfirmPassword = !isHideConfirmPassword;
                    changePasswordVisibility(editConfirmPassword, isHideConfirmPassword);
                    return true;
                }
            }
            return false;
        });

        editNewPassword.addTextChangedListener(Utils.afterEditTextChanged(editNewPassword, v -> {
            if (isEnableLoginButton()) {
                binding.finishResetPassword.setEnabled(true);
            } else {
                binding.finishResetPassword.setEnabled(false);
            }
        }));

        editConfirmPassword.addTextChangedListener(Utils.afterEditTextChanged(editConfirmPassword, v -> {
            if (isEnableLoginButton()) {
                binding.finishResetPassword.setEnabled(true);
            } else {
                binding.finishResetPassword.setEnabled(false);
            }
        }));

        binding.finishResetPassword.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });

        return root;
    }

    private boolean isEnableLoginButton() {
        return !editNewPassword.getText().toString().isEmpty() && !editConfirmPassword.getText().toString().isEmpty();
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