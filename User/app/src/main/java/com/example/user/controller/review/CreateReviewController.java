package com.example.user.controller.review;

import com.example.user.model.Review;
import com.example.user.utils.FirebaseUtils;

public class CreateReviewController implements ReviewCreator {
    @Override
    public void createReview(int movieId, Review review) {
        FirebaseUtils.getDatabaseReference("Reviews")
                .child(String.valueOf(movieId))
                .push()
                .setValue(review);
    }
}
