package com.example.asg02;

import static com.example.asg02.model.Event.encodeImage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.asg02.controller.CreateMovieController;
import com.example.asg02.model.Movie;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class AdminCreateMovieActivity extends BaseActivity {
    private static final int PICK_IMAGE =  50;
    ImageButton backBtn;
    Button addMovieBtn;
    EditText enterMovieName, enterReleasedDate, enterDurationMins, enterDescription, enterGenre, enterDirector,
            enterActors, enterLanguage, enterTrailerLink;
    AutoCompleteTextView enterRated;
    ImageView selectPoster;
    private CreateMovieController createMovieController;
    private String movieName = "";
    private String poster = "";
    private String releaseDate = "";
    private int durationMins = 0;
    private String description = "";
    private String rated = "";
    private String genre = "";
    private String director = "";
    private String actors = "";
    private String language = "";
    private String trailerLink = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_movie);
        backBtn = findViewById(R.id.backMovie);
        selectPoster = findViewById(R.id.selectPoster);
        enterMovieName = findViewById(R.id.enterMovieName);
        enterReleasedDate = findViewById(R.id.enterReleasedDate);
        enterDurationMins = findViewById(R.id.enterDurationMins);
        enterDescription = findViewById(R.id.enterDescription);
        enterRated = findViewById(R.id.enterRated);
        enterGenre = findViewById(R.id.enterGenre);
        enterDirector = findViewById(R.id.enterDirector);
        enterActors = findViewById(R.id.enterActors);
        enterLanguage = findViewById(R.id.enterLanguage);
        enterTrailerLink = findViewById(R.id.enterTrailerLink);
        addMovieBtn = findViewById(R.id.addCinemaBtn);

        createMovieController = new CreateMovieController(this);

        backBtn.setOnClickListener(v -> showConfirmBackDialog());

        selectPoster.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), PICK_IMAGE);
        });

        enterReleasedDate.setOnClickListener(v -> {
            Calendar myCalendar = Calendar.getInstance();
            new DatePickerDialog(AdminCreateMovieActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            enterReleasedDate.setText(String.format("%02d/%02d/%04d", i2, i1 + 1, i));
                        }
                    },
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        enterRated.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    enterRated.showDropDown();
                }
                return false;
            }
        });
        enterRated.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"P", "K", "T13", "T16", "T18", "C"}));
        enterRated.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                rated = enterRated.getText().toString();
                addMovieBtn.setEnabled(!movieName.isEmpty() && !poster.isEmpty() && !trailerLink.isEmpty() &&
                        !releaseDate.isEmpty() && durationMins != 0 && !description.isEmpty() &&!rated.isEmpty() &&
                        !genre.isEmpty() && !director.isEmpty() && !actors.isEmpty() && !language.isEmpty());
            }
        });

        enterMovieName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                movieName = enterMovieName.getText().toString();
                addMovieBtn.setEnabled(!movieName.isEmpty() && !poster.isEmpty() && !trailerLink.isEmpty() &&
                        !releaseDate.isEmpty() && durationMins != 0 && !description.isEmpty() &&!rated.isEmpty() &&
                        !genre.isEmpty() && !director.isEmpty() && !actors.isEmpty() && !language.isEmpty());
            }
        });

        enterTrailerLink.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                trailerLink = enterTrailerLink.getText().toString();
                addMovieBtn.setEnabled(!movieName.isEmpty() && !poster.isEmpty() && !trailerLink.isEmpty() &&
                        !releaseDate.isEmpty() && durationMins != 0 && !description.isEmpty() &&!rated.isEmpty() &&
                        !genre.isEmpty() && !director.isEmpty() && !actors.isEmpty() && !language.isEmpty());
            }
        });

        enterReleasedDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                releaseDate = enterReleasedDate.getText().toString();
                addMovieBtn.setEnabled(!movieName.isEmpty() && !poster.isEmpty() && !trailerLink.isEmpty() &&
                        !releaseDate.isEmpty() && durationMins != 0 && !description.isEmpty() &&!rated.isEmpty() &&
                        !genre.isEmpty() && !director.isEmpty() && !actors.isEmpty() && !language.isEmpty());
            }
        });

        enterDurationMins.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                durationMins = Integer.parseInt((enterDurationMins.getText().toString()));
                addMovieBtn.setEnabled(!movieName.isEmpty() && !poster.isEmpty() && !trailerLink.isEmpty() &&
                        !releaseDate.isEmpty() && durationMins != 0 && !description.isEmpty() &&!rated.isEmpty() &&
                        !genre.isEmpty() && !director.isEmpty() && !actors.isEmpty() && !language.isEmpty());
            }
        });

        enterDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                description = enterDescription.getText().toString();
                addMovieBtn.setEnabled(!movieName.isEmpty() && !poster.isEmpty() && !trailerLink.isEmpty() &&
                        !releaseDate.isEmpty() && durationMins != 0 && !description.isEmpty() &&!rated.isEmpty() &&
                        !genre.isEmpty() && !director.isEmpty() && !actors.isEmpty() && !language.isEmpty());
            }
        });

        enterGenre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                genre = enterGenre.getText().toString();
                addMovieBtn.setEnabled(!movieName.isEmpty() && !poster.isEmpty() && !trailerLink.isEmpty() &&
                        !releaseDate.isEmpty() && durationMins != 0 && !description.isEmpty() &&!rated.isEmpty() &&
                        !genre.isEmpty() && !director.isEmpty() && !actors.isEmpty() && !language.isEmpty());
            }
        });

        enterDirector.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                director = enterDirector.getText().toString();
                addMovieBtn.setEnabled(!movieName.isEmpty() && !poster.isEmpty() && !trailerLink.isEmpty() &&
                        !releaseDate.isEmpty() && durationMins != 0 && !description.isEmpty() &&!rated.isEmpty() &&
                        !genre.isEmpty() && !director.isEmpty() && !actors.isEmpty() && !language.isEmpty());
            }
        });

        enterActors.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                actors = enterActors.getText().toString();
                addMovieBtn.setEnabled(!movieName.isEmpty() && !poster.isEmpty() && !trailerLink.isEmpty() &&
                        !releaseDate.isEmpty() && durationMins != 0 && !description.isEmpty() &&!rated.isEmpty() &&
                        !genre.isEmpty() && !director.isEmpty() && !actors.isEmpty() && !language.isEmpty());
            }
        });

        enterLanguage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                language = enterLanguage.getText().toString();
                addMovieBtn.setEnabled(!movieName.isEmpty() && !poster.isEmpty() && !trailerLink.isEmpty() &&
                        !releaseDate.isEmpty() && durationMins != 0 && !description.isEmpty() &&!rated.isEmpty() &&
                        !genre.isEmpty() && !director.isEmpty() && !actors.isEmpty() && !language.isEmpty());
            }
        });

        addMovieBtn.setOnClickListener(view -> showConfirmAddDialog());
    }

    private void showConfirmBackDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có chắc chắn muốn thoát không?");

        builder.setPositiveButton("Có", (dialog, which) -> {
            Intent intent = new Intent(this, AdminMainActivity.class);
            startActivity(intent);
        });

        builder.setNegativeButton("Quay lại", (dialog, which) -> {
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showConfirmAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có chắc chắn muốn thêm phim này không?");

        builder.setPositiveButton("Có", (dialog, which) -> {
            Movie movie = new Movie(movieName, poster, trailerLink, releaseDate, durationMins, description, rated, genre, director, actors, language);
            createMovieController.createMovie(movie);
            Intent intent = new Intent(this, AdminMainActivity.class);
            startActivity(intent);
        });

        builder.setNegativeButton("Quay lại", (dialog, which) -> {
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
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
                poster = encodeImage(posterBitmap);
                addMovieBtn.setEnabled(!movieName.isEmpty() && !poster.isEmpty() && !trailerLink.isEmpty() &&
                        !releaseDate.isEmpty() && durationMins != 0 && !description.isEmpty() &&!rated.isEmpty() &&
                        !genre.isEmpty() && !director.isEmpty() && !actors.isEmpty() && !language.isEmpty());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}