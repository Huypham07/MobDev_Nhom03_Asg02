package com.example.user.controller.review;

import com.example.user.model.Review;

public interface ReviewCreator {
    void createReview(int movieId, Review review);
}
