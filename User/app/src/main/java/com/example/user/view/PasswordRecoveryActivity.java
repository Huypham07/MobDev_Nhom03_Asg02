package com.example.user.view;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.user.R;
import com.example.user.databinding.ActivityPasswordRecoveryBinding;

public class PasswordRecoveryActivity extends AppCompatActivity {
    private ActivityPasswordRecoveryBinding binding;
    private NavController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPasswordRecoveryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        controller = Navigation.findNavController(this, R.id.nav_host_fragment_content_recovery);
        controller.addOnDestinationChangedListener((ctr, des, arg) -> {
            getSupportActionBar().setTitle(des.getLabel());
        });

        binding.toolbar.setNavigationOnClickListener(v -> {
            if (!controller.navigateUp()) {
                startActivity(new Intent(this, LoginActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}