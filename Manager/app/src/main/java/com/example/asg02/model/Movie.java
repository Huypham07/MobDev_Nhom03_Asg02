package com.example.asg02.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class Movie implements Serializable {
    private int id;
    private String name;
    private String poster;
    private String genre;
    private String durationMins;
    private String releaseDate;
    private String description;
    private String director;
    private String actors;
    private String language;
    private String trailerLink;
    private float rating;
    public Movie() {};

    public Movie(String name, String poster, String genre, String durationMins, String releaseDate, String description, String director, String actors, String language, String trailerLink, float rating) {
        this.name = name;
        this.poster = poster;
        this.genre = genre;
        this.durationMins = durationMins;
        this.releaseDate = releaseDate;
        this.description = description;
        this.director = director;
        this.actors = actors;
        this.language = language;
        this.trailerLink = trailerLink;
        this.rating = rating;
        id = hashCode();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDurationMins() {
        return durationMins;
    }

    public void setDurationMins(String durationMins) {
        this.durationMins = durationMins;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    public static String encodeImage(Bitmap bitmap) {
        int previewWidth = 400;
        int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static Bitmap decodeBitmap(String uri) {
        byte[] bytes = Base64.getDecoder().decode(uri);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
