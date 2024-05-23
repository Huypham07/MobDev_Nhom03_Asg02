package com.example.admin;

import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AdminAddCinemaActivity extends BaseActivity {

    ImageButton backBtn;
    Button continueBtn;
    EditText enterCinemaName;
    String cinemaName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_cinema);

        backBtn = findViewById(R.id.backBtn2);
        continueBtn = findViewById(R.id.continueBtn);
        enterCinemaName = findViewById(R.id.enterCinemaName);

//        cinemaName = getIntent().getExtras().getString("CinemaName", "");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Kiểm tra xem Bundle có chứa key "CinemaName" không
            if (extras.containsKey("CinemaName")) {
                // Lấy giá trị của key "CinemaName" từ Bundle
                cinemaName = extras.getString("CinemaName", "");
                // Sử dụng giá trị cinemaName ở đây
            }
        }

        if (!cinemaName.isEmpty()) {
            enterCinemaName.setText(cinemaName);
            continueBtn.setEnabled(true);
        }

        enterCinemaName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                cinemaName = enterCinemaName.getText().toString();
                continueBtn.setEnabled(!cinemaName.isEmpty());
            }
        });

        continueBtn.setOnClickListener(v -> showConfirmationDialog());

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminMainActivity.class);
            startActivity(intent);
        });
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có chắc chắn muốn thêm cụm rạp này không?");

        builder.setPositiveButton("Tiếp tục", (dialog, which) -> {
            Intent intent = new Intent(this, AdminAddCinemaActivity2.class);
            intent.putExtra("CinemaName", cinemaName);
            startActivity(intent);
        });

        builder.setNegativeButton("Quay lại", (dialog, which) -> {
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}