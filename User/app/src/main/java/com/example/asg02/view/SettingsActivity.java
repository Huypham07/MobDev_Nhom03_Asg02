package com.example.asg02.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.asg02.R;
import com.example.asg02.databinding.SettingsActivityBinding;

public class SettingsActivity extends AppCompatActivity {
    private SettingsActivityBinding binding;
    private NavController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SettingsActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        controller = Navigation.findNavController(this, R.id.nav_host_fragment_content_settings);
        controller.addOnDestinationChangedListener((ctr, des, arg) -> {
            getSupportActionBar().setTitle(des.getLabel());
        });

        binding.toolbar.setNavigationOnClickListener(v -> {
            if (!controller.navigateUp()) {
                onBackPressed();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int fragmentId = bundle.getInt("fragmentID", -1);
            if (fragmentId != -1) {
                controller.navigate(fragmentId);
            }
        }

    }
}