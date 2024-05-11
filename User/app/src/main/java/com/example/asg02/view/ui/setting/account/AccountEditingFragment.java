package com.example.asg02.view.ui.setting.account;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.asg02.R;
import com.example.asg02.controller.DeleteAccountController;
import com.example.asg02.controller.GetProvinceController;
import com.example.asg02.controller.UpdateAccountController;
import com.example.asg02.databinding.FragmentAccountEditingBinding;
import com.example.asg02.model.User;
import com.example.asg02.view.BaseActivity;
import com.example.asg02.view.LoginActivity;
import com.example.asg02.view.RegisterActivity;
import com.example.asg02.view.SettingsActivity;
import com.example.asg02.view.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountEditingFragment extends Fragment {
    private FragmentAccountEditingBinding binding;
    private boolean change = false;
    private User user;
    private UpdateAccountController updateAccountController;
    private DeleteAccountController deleteAccountController;
    private EditText editName;
    private EditText editPhone;
    private EditText editEmail;
    private EditText editPassword;
    private AutoCompleteTextView editBirthDate;
    private AutoCompleteTextView editSex;
    private AutoCompleteTextView editRegion;
    private AutoCompleteTextView editFavorite;
    private boolean isHidePassword = true;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAccountEditingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // assign
        editName = binding.editName;
        editPhone = binding.editPhone;
        editEmail = binding.editEmail;
        editPassword = binding.editPassword;
        ImageView passwordIcon = binding.hidePasswordButton;
        editBirthDate = binding.editBirthDate;
        editSex = binding.editSex;
        editRegion = binding.editRegion;
        editFavorite = binding.editFavorite;
        updateAccountController = new UpdateAccountController();
        deleteAccountController = new DeleteAccountController();

        user = (User) getActivity().getIntent().getSerializableExtra("user");

        if (user != null) {
            editName.setText(user.getName());
            editEmail.setText(user.getEmail());
            editPhone.setText(user.getPhone());
            editPassword.setText(user.getPassword());
            editBirthDate.setText(user.getBirthDate());
            editSex.setText(user.getSex());
            editRegion.setText(user.getRegion());


            //-----------add eventListener--------------
            editName.addTextChangedListener(Utils.afterEditTextChanged(editName, v -> {
                if (!user.getName().equals(editName.getText().toString())) {
                    change = true;
                }
            }));

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

                if (!user.getPassword().equals(editPassword.getText().toString())) {
                    change = true;
                }
            }));

            editBirthDate.setOnClickListener(v -> {
                String date = user.getBirthDate();
                String[] dateArr = date.split("/");
                int[] iDate = new int[3];
                for (int i = 0; i < 3; i++) {
                    iDate[i] = Integer.parseInt(dateArr[i]);
                }
                new DatePickerDialog(getContext(),
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

                if (!user.getBirthDate().equals(editBirthDate.getText().toString())) {
                    change = true;
                }
            }));

            editSex.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, new String[]{"Nam", "Nữ", "Khác"}));
            editSex.addTextChangedListener(Utils.afterEditTextChanged(editSex, v -> {
                if (!editSex.getText().toString().isEmpty()) {
                    editSex.setError(null);
                }
                if (!user.getSex().equals(editSex.getText().toString())) {
                    change = true;
                }
            }));

            editRegion.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item
                    , new GetProvinceController().getAllProvinces()));
            editRegion.addTextChangedListener(Utils.afterEditTextChanged(editRegion, v -> {
                if (!user.getRegion().equals(editRegion.getText().toString())) {
                    change = true;
                }
            }));

            editFavorite.setOnClickListener(v -> {
                // do nothing

            });
            editFavorite.addTextChangedListener(Utils.afterEditTextChanged(editFavorite, v -> {
                if (!user.getFavouriteCinema().equals(editFavorite.getText().toString())) {
                    change = true;
                }
            }));

            changePasswordVisibility(passwordIcon, editPassword, isHidePassword);
            passwordIcon.setOnClickListener(v -> {
                isHidePassword = !isHidePassword;
                changePasswordVisibility(passwordIcon, editPassword, isHidePassword);
            });

            binding.save.setOnClickListener(v -> {
                if (change) {
                    if (isFilledEditTexts()) {
                        String message = "Bạn có muốn lưu thông tin đã thay đổi không?";
                        Utils.createAskingDialog(message, getContext(), v1 -> {
                            // save change
                            user.setName(binding.editName.getText().toString());
                            user.setPassword(binding.editPassword.getText().toString());
                            user.setBirthDate(binding.editBirthDate.getText().toString());
                            user.setSex(binding.editSex.getText().toString());
                            user.setRegion(binding.editRegion.getText().toString());
                            change = false;
                            updateAccountController.updateCurrentAccount(user);
                        }).show();
                    }
                }
            });

            binding.deleteAccount.setOnClickListener(v -> {
                String message = "Bạn có chắc chắn muốn xóa tài khoản không?";
                Utils.createAskingDialog(message, getContext(), v1 -> {
                    deleteAccountController.deleteAccount();
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }).show();
            });
        }


        binding.scrollView.setOnTouchListener((v, e) -> {
            ((SettingsActivity) getActivity()).hideKeyboard();
            return true;
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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