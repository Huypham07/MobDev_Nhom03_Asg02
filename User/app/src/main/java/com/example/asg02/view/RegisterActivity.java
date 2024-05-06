package com.example.asg02.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.example.asg02.R;
import com.example.asg02.controller.RegisterController;
import com.example.asg02.model.User;

public class RegisterActivity extends AppCompatActivity {
    private boolean isHidePassword = true;
    private RegisterController registerController;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerController = new RegisterController();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v_back -> {
            onBackPressed();
        });

        EditText editPassword = findViewById(R.id.editPassword);
        changePasswordVisibility(editPassword, isHidePassword);
        editPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (event.getRawX() >= (editPassword.getRight() - editPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    isHidePassword = !isHidePassword;
                    changePasswordVisibility(editPassword, isHidePassword);
                    return true;
                }
            }
            return false;
        });

        Button register = findViewById(R.id.register);
        register.setOnClickListener(v -> {
            EditText editName = findViewById(R.id.editName);
            String name = editName.getText().toString();

            EditText editPhone = findViewById(R.id.editPhone);
            String phone = editPhone.getText().toString();

            EditText editEmail = findViewById(R.id.editEmail);
            String email = editEmail.getText().toString();

            String password = editPassword.getText().toString();

            EditText editBirthDate = findViewById(R.id.editBirthdate);
            String birthDate = editBirthDate.getText().toString();

            EditText editSex = findViewById(R.id.editSex);
            String sex = editSex.getText().toString();

            EditText editRegion = findViewById(R.id.editRegion);
            String region = editRegion.getText().toString();

            EditText editFavorite = findViewById(R.id.editFavorite);
            String favorite = editFavorite.getText().toString();

            User user = new User(email, password, name, birthDate, sex, phone, region, favorite, 0);

            if (findViewById(R.id.checkBox).isActivated()) {
                if (registerController.register(user)) {
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        // custom
        super.onBackPressed();
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
}