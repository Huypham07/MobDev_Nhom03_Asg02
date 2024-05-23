package com.example.user.controller.review;

import com.example.user.model.Review;

import java.util.function.Consumer;

public class GetReviewLogic {
    private final ReviewReader reviewReader;

    public GetReviewLogic(ReviewReader reviewReader) {
        this.reviewReader = reviewReader;
    }

    public void getReviewsOfMovie(int movieId, Consumer<Review> onReviewAdded) {
        reviewReader.getReviewsOfMovie(movieId, onReviewAdded);
    }
}
