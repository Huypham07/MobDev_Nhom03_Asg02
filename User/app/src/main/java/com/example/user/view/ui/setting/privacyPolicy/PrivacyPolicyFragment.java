package com.example.user.view.ui.setting.privacyPolicy;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.R;
import com.example.user.databinding.FragmentPrivacyPolicyBinding;

public class PrivacyPolicyFragment extends Fragment {
    private FragmentPrivacyPolicyBinding binding;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPrivacyPolicyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.loadUrl("file:///android_asset/privacy_policy.html");

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}