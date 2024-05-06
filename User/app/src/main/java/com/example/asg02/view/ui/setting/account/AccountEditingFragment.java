package com.example.asg02.view.ui.setting.account;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.asg02.databinding.FragmentAccountEditingBinding;
import com.example.asg02.view.Utils;

public class AccountEditingFragment extends Fragment {

    private AccountEditingViewModel mViewModel;
    private FragmentAccountEditingBinding binding;
    private boolean change = false;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(AccountEditingViewModel.class);

        binding = FragmentAccountEditingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.save.setOnClickListener(v -> {
            if (change) {
                String message = "Bạn có muốn lưu thông tin đã thay đổi không?";
                Utils.createAskingDialog(message, getContext(), v1 -> {
                    // save change
                    change = false;
                }).show();
            }
        });

        binding.deleteAccount.setOnClickListener(v -> {
            String message = "Bạn có chắc chắn muốn xóa tài khoản không?";
            Utils.createAskingDialog(message, getContext(), v1 -> {
                // delete account
            }).show();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}