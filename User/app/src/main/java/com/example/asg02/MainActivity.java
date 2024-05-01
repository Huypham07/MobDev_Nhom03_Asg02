package com.example.asg02;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.widget.*;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.NavDestination;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.asg02.databinding.ActivityMainBinding;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private NavController controller;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.navigationToggle.setOnClickListener(v -> {
            binding.drawerLayout.openDrawer(Gravity.RIGHT);
        });

        controller = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        controller.addOnDestinationChangedListener((ctr, des, arg) -> {
            getSupportActionBar().setTitle(des.getLabel());
            if (des.getLabel() == null) {
                binding.appBarMain.toolbar.setNavigationIcon(R.drawable.account_icon);
            }
        });

        binding.navViewLayout.logOut.setOnClickListener(v_ -> {
            Dialog dialog = new Dialog(this);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_asking_for_user_opinions);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            TextView content = dialog.findViewById(R.id.content);
            content.setText("Bạn chắc chắn muốn đăng xuất chứ?");

            Button cancel = dialog.findViewById(R.id.cancel_action);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            Button continue_ = dialog.findViewById(R.id.continue_action);
            continue_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });

            dialog.show();
        });
    }

    @Override
    public void onBackPressed() {
        // custom
    }

    private void clearNavigationUpButton() {
        binding.appBarMain.toolbar.setNavigationIcon(null);
    }

    private void setNavigationUpButton() {
        binding.appBarMain.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_new_24);
    }
}