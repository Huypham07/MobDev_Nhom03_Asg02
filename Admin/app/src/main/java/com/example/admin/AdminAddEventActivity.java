package com.example.admin;

import static com.example.admin.model.Event.encodeImage;

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

import androidx.appcompat.app.AlertDialog;

import com.example.admin.controller.CreateEventController;
import com.example.admin.model.Event;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class AdminAddEventActivity extends BaseActivity {

    private ImageButton backBtn;
    private EditText enterEventName;
    private ImageView selectPoster;
    private EditText enterSDate;
    private EditText enterEDate;
    private EditText enterEventInfo;
    private Button addEventBtn;
    private Event event;
    private String eventName = "";
    private String posterUri;
    private String startDate = "";
    private String endDate = "";
    private String eventInfo = "";
    private CreateEventController createEventController;

    private static final int PICK_IMAGE = 1;

    boolean fromShowEvent = false;

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

        createEventController = new CreateEventController(this);

        event = (Event) getIntent().getSerializableExtra("event");
        if (event != null) {
            fromShowEvent = true;
            eventName = event.getEventName();
            enterEventName.setText(eventName);
            posterUri = event.getPoster();
            Bitmap posterBitmap = Event.decodeBitmap(posterUri);
            selectPoster.setImageBitmap(posterBitmap);
            startDate = event.getStartDate();
            enterSDate.setText(startDate);
            endDate = event.getEndDate();
            enterEDate.setText(endDate);
            eventInfo = event.getEventInfo();
            enterEventInfo.setText(eventInfo);
            addEventBtn.setEnabled(!eventName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()
                    && !eventInfo.isEmpty() && posterUri != null);
        }

        enterEventName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                eventName = enterEventName.getText().toString();
                addEventBtn.setEnabled(!eventName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()
                        && !eventInfo.isEmpty() && posterUri != null);
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
                        && !eventInfo.isEmpty() && posterUri != null);
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
                        && !eventInfo.isEmpty() && posterUri != null);
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
                        && !eventInfo.isEmpty() && posterUri != null);
            }
        });

        selectPoster.setOnClickListener(v -> selectImage());

        enterSDate.setOnClickListener(v -> {
            Calendar myCalendar = Calendar.getInstance();
            new DatePickerDialog(AdminAddEventActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            enterSDate.setText(String.format("%02d/%02d/%04d", i2, i1 + 1, i));
                        }
                    },
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        enterEDate.setOnClickListener(v -> {
            Calendar myCalendar = Calendar.getInstance();
            new DatePickerDialog(AdminAddEventActivity.this,
                    (datePicker, i, i1, i2) -> enterEDate.setText(String.format("%02d/%02d/%04d", i2, i1 + 1, i)),
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        backBtn.setOnClickListener(v -> showConfirmBackDialog());

        addEventBtn.setOnClickListener(v -> showConfirmAddDialog());
    }

    private void showConfirmBackDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có chắc chắn muốn thoát không?");

        builder.setPositiveButton("Có", (dialog, which) -> {
            finish();
        });

        builder.setNegativeButton("Quay lại", (dialog, which) -> {
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showConfirmAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có chắc chắn muốn thêm tin mới & ưu đãi này không?");

        builder.setPositiveButton("Có", (dialog, which) -> {
            if (event == null) {
                event = new Event(eventName, posterUri, startDate, endDate, eventInfo);
            } else {
                event.setValue(eventName, posterUri, startDate, endDate, eventInfo);
            }
            createEventController.createEvent(event);

            Intent intent;
            if (fromShowEvent) {
                intent = new Intent(this, AdminShowEventActivity.class);
                intent.putExtra("event", event);
            } else {
                intent = new Intent(this, AdminManageEventActivity.class);
            }
            startActivity(intent);
        });

        builder.setNegativeButton("Quay lại", (dialog, which) -> {
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void selectImage() {
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
                Bitmap posterBitmap = BitmapFactory.decodeStream(inputStream);
                selectPoster.setImageBitmap(posterBitmap);
                selectPoster.setBackground(null);
                posterUri = encodeImage(posterBitmap);
                addEventBtn.setEnabled(!eventName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()
                        && !eventInfo.isEmpty() && posterUri != null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}