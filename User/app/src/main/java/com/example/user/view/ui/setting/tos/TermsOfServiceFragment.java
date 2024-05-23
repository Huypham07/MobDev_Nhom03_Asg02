package com.example.user.view.ui.setting.tos;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.R;
import com.example.user.databinding.FragmentTermsOfServiceBinding;

public class TermsOfServiceFragment extends Fragment {
    private FragmentTermsOfServiceBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTermsOfServiceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.loadUrl("file:///android_asset/terms_of_service.html");

        return root;
    }


}