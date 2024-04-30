package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class AdminAddEventActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private EditText enterEventName;
    private ImageView selectPoster;
    private EditText enterSDate;
    private EditText enterEDate;
    private EditText enterEventInfo;
    private Button addEventBtn;
    String eventName = "";
    private Bitmap posterBitmap;
    String startDate = "";
    String endDate = "";
    String eventInfo = "";

    private static final int PICK_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_event);

        backBtn = findViewById(R.id.backBtn5);
        enterEventName = findViewById(R.id.enterEventName);
        selectPoster = findViewById(R.id.selectPoster);
        enterSDate = findViewById(R.id.enterSDate);
        enterEDate = findViewById(R.id.enterEDate);
        enterEventInfo = findViewById(R.id.enterEventInfo);
        addEventBtn = findViewById(R.id.addEventBtn);

        enterEventName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                eventName = enterEventName.getText().toString();
                addEventBtn.setEnabled(!eventName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()
                        && !eventInfo.isEmpty() && posterBitmap != null);
            }
        });
        enterSDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                startDate = enterSDate.getText().toString();
                addEventBtn.setEnabled(!eventName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()
                        && !eventInfo.isEmpty() && posterBitmap != null);
            }
        });
        enterEDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                endDate = enterEDate.getText().toString();
                addEventBtn.setEnabled(!eventName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()
                        && !eventInfo.isEmpty() && posterBitmap != null);
            }
        });
        enterEventInfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                eventInfo = enterEventInfo.getText().toString();
                addEventBtn.setEnabled(!eventName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()
                        && !eventInfo.isEmpty() && posterBitmap != null);
            }
        });

        selectPoster.setOnClickListener(v -> selectImage());

        enterSDate.setOnClickListener(v -> {
            Calendar myCalendar = Calendar.getInstance();
            new DatePickerDialog(AdminAddEventActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            enterSDate.setText(String.format("%02d/%02d/%04d", i2, i1, i));
                        }
                    },
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        enterEDate.setOnClickListener(v -> {
            Calendar myCalendar = Calendar.getInstance();
            new DatePickerDialog(AdminAddEventActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            enterEDate.setText(String.format("%02d/%02d/%04d", i2, i1, i));
                        }
                    },
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminEventActivity.class);
            startActivity(intent);
        });

        addEventBtn.setOnClickListener(v -> {

//            Event event = new Event(eventName, posterBitmap, startDate, endDate, eventInfo);

            // Thêm chức năng đưa sự kiện lên firebase để chia sẻ cho các tài khoản khác ở đây

            Intent intent = new Intent(this, AdminEventActivity.class);
//            intent.putExtra("event", event);
            startActivity(intent);
        });
    }

    public void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                posterBitmap = BitmapFactory.decodeStream(inputStream);
                selectPoster.setImageBitmap(posterBitmap);
                selectPoster.setBackground(null);
                addEventBtn.setEnabled(!eventName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()
                        && !eventInfo.isEmpty() && posterBitmap != null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}