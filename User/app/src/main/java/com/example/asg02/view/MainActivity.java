package com.example.asg02.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import com.example.asg02.R;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.appcompat.app.AppCompatActivity;
import com.example.asg02.databinding.ActivityMainBinding;
import com.example.asg02.model.Account;
import com.example.asg02.model.User;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private NavController controller;
    private User user;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.navigationToggle.setOnClickListener(v -> {
            openDrawer();
        });

        controller = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        controller.addOnDestinationChangedListener((ctr, des, arg) -> {
            getSupportActionBar().setTitle(des.getLabel());
            if (ctr.getCurrentDestination().getId() == R.id.nav_home) {
                binding.appBarMain.toolbar.setNavigationIcon(R.drawable.account_icon);
                binding.appBarMain.ticket.setVisibility(View.VISIBLE);
            } else {
                binding.appBarMain.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_new_24);
                binding.appBarMain.ticket.setVisibility(View.GONE);
            }

            if (ctr.getCurrentDestination().getId() == R.id.nav_notification) {
                binding.appBarMain.navigationToggle.setVisibility(View.GONE);
            } else {
                binding.appBarMain.navigationToggle.setVisibility(View.VISIBLE);
            }
        });

        // open setting
        binding.navViewLayout.settings.setOnClickListener(v -> {
            closeDrawer();
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });


        // logout
        binding.navViewLayout.logOut.setOnClickListener(v -> {
            String message = "Bạn chắc chắn muốn đăng xuất chứ?";
            Dialog dialog = Utils.createAskingDialog(message, MainActivity.this, v1 -> {
                closeDrawer();
                // logout
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            });
            dialog.show();
        });

        binding.appBarMain.toolbar.setNavigationOnClickListener(v -> {
            if (controller.getCurrentDestination().getId() == R.id.nav_home) {
                moveToAccountInfo();
                closeDrawer();
            } else {
                controller.navigateUp();
            }
        });

        binding.navViewLayout.accountInfo.setOnClickListener(v -> {
            moveToAccountInfo();
            closeDrawer();
        });

        binding.navViewLayout.notification.setOnClickListener(v -> {
            controller.navigate(R.id.nav_notification);
            closeDrawer();
        });
    }

    private void closeDrawer() {
        if (binding.drawerLayout != null) {
            binding.drawerLayout.closeDrawer(Gravity.RIGHT);
        }
    }

    private void openDrawer() {
        if (binding.drawerLayout != null) {
            binding.drawerLayout.openDrawer(Gravity.RIGHT);
        }
    }

    private void moveToAccountInfo() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);

        Bundle bundle = new Bundle();
        bundle.putInt("fragmentID", R.id.nav_account);
        intent.putExtras(bundle);
        intent.putExtra("user", user);


        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        user = (User) getIntent().getSerializableExtra("user");
        binding.navViewLayout.name.setText(user.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}