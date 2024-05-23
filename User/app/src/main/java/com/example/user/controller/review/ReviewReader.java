package com.example.user.controller.review;

import com.example.user.model.Review;

import java.util.function.Consumer;

public interface ReviewReader {
    void getReviewsOfMovie(int movieId, Consumer<Review> onReviewAdded);
}
