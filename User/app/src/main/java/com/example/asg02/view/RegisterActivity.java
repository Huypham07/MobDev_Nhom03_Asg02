package com.example.asg02.view;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Handler;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.asg02.R;
import com.example.asg02.controller.GetProvinceController;
import com.example.asg02.controller.RegisterController;
import com.example.asg02.databinding.ActivityRegisterBinding;
import com.example.asg02.model.User;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class RegisterActivity extends BaseActivity {
    private boolean isHidePassword = true;
    private RegisterController registerController;
    private ActivityRegisterBinding binding;
    private EditText editName;
    private EditText editPhone;
    private EditText editEmail;
    private EditText editPassword;
    private AutoCompleteTextView editBirthDate;
    private AutoCompleteTextView editSex;
    private AutoCompleteTextView editRegion;
    private AutoCompleteTextView editFavorite;
    private ProgressBar progressBar;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // assign
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        editName = binding.editName;
        editPhone = binding.editPhone;
        editEmail = binding.editEmail;
        editPassword = binding.editPassword;
        ImageView passwordIcon = binding.hidePasswordButton;
        editBirthDate = binding.editBirthDate;
        editSex = binding.editSex;
        editRegion = binding.editRegion;
        editFavorite = binding.editFavorite;
        progressBar = binding.progressBar;
        registerController = new RegisterController();

        editBirthDate.setOnClickListener(v -> {
            String currentDate = editBirthDate.getText().toString();
            int iDate[] = new int[3];
            if (currentDate.isEmpty()) {
                iDate[2] = 2000;
                iDate[1] = 1;
                iDate[0] = 1;
            } else {
                String sDate[] = currentDate.split("/");
                for (int i = 0; i < sDate.length; i++) {
                    iDate[i] = Integer.parseInt(sDate[i]);
                }
            }
            new DatePickerDialog(RegisterActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            editBirthDate.setText(String.format("%02d/%02d/%04d", i2, i1 + 1, i));
                        }
                    }, iDate[2], iDate[1] - 1, iDate[0]).show();
        });
        editBirthDate.addTextChangedListener(Utils.afterEditTextChanged(editBirthDate, v -> {
            if (!editBirthDate.getText().toString().isEmpty()) {
                editBirthDate.setError(null);
            }
        }));

        editSex.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"Nam", "Nữ", "Khác"}));
        editSex.addTextChangedListener(Utils.afterEditTextChanged(editSex, v -> {
            if (!editSex.getText().toString().isEmpty()) {
                editSex.setError(null);
            }
        }));

        editRegion.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item
                , new GetProvinceController().getAllProvinces()));
        editRegion.addTextChangedListener(Utils.afterEditTextChanged(editRegion, v -> {
            if (!editRegion.getText().toString().isEmpty()) {
                editRegion.setError(null);
            }
        }));

        editFavorite.setOnTouchListener((v, e) -> {
            return true;
        });


        changePasswordVisibility(passwordIcon, editPassword, isHidePassword);
        passwordIcon.setOnClickListener(v -> {
            isHidePassword = !isHidePassword;
            changePasswordVisibility(passwordIcon, editPassword, isHidePassword);
        });

        editPassword.addTextChangedListener(Utils.afterEditTextChanged(editPassword, v -> {
            if (editPassword.getText().toString().length() < 8) {
                editPassword.setError("Mật khẩu cần dai ít nhất 8 ký tự");
                return;
            }

            if (!editPassword.getText().toString().matches(".*\\d.*")) {
                editPassword.setError("Mật khẩu cần chứa ít nhất 1 số");
                return;
            }

            if (!editPassword.getText().toString().matches(".*[a-zA-Z].*")) {
                editPassword.setError("Mật khẩu cần chứa ít nhất 1 chữ cái");
                return;
            }

            editPassword.setError(null);
        }));

        binding.register.setOnClickListener(v -> {
            User user = getUserFromInput();
            CheckBox checkBox = binding.checkBox;
            if (checkBox.isChecked()) {
                progressBar.setVisibility(View.VISIBLE);
                registerController.createAccount(user).thenAccept(i -> {
                    switch (i) {
                        case RegisterController.FAIL:
                            Snackbar.make(v, "Đăng ký thất bại.\nVui lòng điền đầy đủ thông tin hợp lệ", Snackbar.LENGTH_LONG).show();
                            break;
                        case RegisterController.EMAIL_EXISTS:
                            Snackbar.make(v, "Email đã tồn tại", Snackbar.LENGTH_LONG).show();
                            break;
                        case RegisterController.PHONE_EXISTS:
                            Snackbar.make(v, "Số điện thoại đã tồn tại", Snackbar.LENGTH_LONG).show();
                            break;
                        default:
                            Snackbar.make(v, "Đăng ký thành công. Đang chuyển hướng", Snackbar.LENGTH_LONG).show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                }
                            }, 2000);

                    }
                    progressBar.setVisibility(View.GONE);
                });

            } else {
                Snackbar.make(v, "Vui lòng đồng ý với điều khoản và điều kiện", Snackbar.LENGTH_LONG).show();
            }
        });

        binding.scrollView.setOnTouchListener((v, event) -> {
            hideKeyboard();
            return true;
        });
    }

    private User getUserFromInput() {
        if (!isFilledEditTexts()) {
            return null;
        }

        String name = editName.getText().toString();
        String phone = editPhone.getText().toString();
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        String birthDate = editBirthDate.getText().toString();
        String sex = editSex.getText().toString();
        String region = editRegion.getText().toString();
        String favorite = editFavorite.getText().toString();

        return new User(email, password, name, birthDate, sex, phone, region, favorite, 0);
    }

    private static final String ERROR_MESSAGE = "Mục này không được để trống";

    private boolean isFilledEditTexts() {
        List<EditText> editTexts = Arrays.asList(editName, editPhone, editEmail, editPassword);
        List<EditText> notFilledEditTexts = new ArrayList<>();

        for (EditText editText : editTexts) {
            if (editText == null) {
                return false;
            }
            if (editText.getText().toString().isEmpty()) {
                notFilledEditTexts.add(editText);
                editText.setError(ERROR_MESSAGE);
            }
        }

        List<AutoCompleteTextView> autoCompleteTextViews = Arrays.asList(editBirthDate, editSex, editRegion);
        List<AutoCompleteTextView> notFilledAutoCompleteTextViews = new ArrayList<>();
        for (AutoCompleteTextView a : autoCompleteTextViews) {
            if (a == null) {
                return false;
            }
            if (a.getText().toString().isEmpty()) {
                notFilledAutoCompleteTextViews.add(a);
                a.setError(ERROR_MESSAGE);
            }
        }

        return notFilledEditTexts.isEmpty() && notFilledAutoCompleteTextViews.isEmpty();
    }

    @Override
    public void onBackPressed() {
        // custom
        super.onBackPressed();
    }

    private void changePasswordVisibility(ImageView imageView, EditText editText, boolean isHidePassword) {
        if (isHidePassword) {
            imageView.setImageResource(R.drawable.hide_password_icon);
            editText.setTransformationMethod(new PasswordTransformationMethod());
        } else {
            imageView.setImageResource(R.drawable.show_password_icon);
            editText.setTransformationMethod(null);
        }
    }
}