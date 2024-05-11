package com.example.asg02.view.ui.setting;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.asg02.R;
import com.example.asg02.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;
//    private NavController controller;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.moveToAccountSettings.setOnClickListener(v -> {
            // move to account settings
            navigate(R.id.action_nav_settings_to_nav_account);
        });

        return root;
    }

    private void navigate(int id) {
        NavController controller = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_settings);
        controller.navigate(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}