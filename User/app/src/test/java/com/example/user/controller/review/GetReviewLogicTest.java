package com.example.user.controller.review;

import static org.mockito.Mockito.*;

import com.example.user.model.Review;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import java.util.function.Consumer;

public class GetReviewLogicTest {

    private ReviewReader mockReviewReader;
    private GetReviewLogic getReviewLogic;

    @Before
    public void setUp() {
        mockReviewReader = mock(ReviewReader.class);
        getReviewLogic = new GetReviewLogic(mockReviewReader);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReviewsOfMovie() {
        // Prepare mock consumer
        Consumer<Review> mockConsumer = mock(Consumer.class);

        // Prepare mock review
        Review review1 = new Review("user1", 4.5f, "Great movie!", 1625292000L);

        // Capture the consumer passed to the reviewReader
        ArgumentCaptor<Consumer<Review>> consumerCaptor = ArgumentCaptor.forClass((Class) Consumer.class);
        doNothing().when(mockReviewReader).getReviewsOfMovie(anyInt(), consumerCaptor.capture());

        // Call getReviewsOfMovie with mock consumer
        getReviewLogic.getReviewsOfMovie(1, mockConsumer);

        // Simulate the onReviewAdded consumer being called with the review
        Consumer<Review> capturedConsumer = consumerCaptor.getValue();
        capturedConsumer.accept(review1);

        // Verify the consumer was called with the correct review
        verify(mockConsumer).accept(review1);
    }

    @Test
    public void testGetReviewsOfMovie_NoReviews() {
        // Prepare mock consumer
        Consumer<Review> mockConsumer = mock(Consumer.class);

        // Capture the consumer passed to the reviewReader
        ArgumentCaptor<Consumer<Review>> consumerCaptor = ArgumentCaptor.forClass((Class) Consumer.class);
        doNothing().when(mockReviewReader).getReviewsOfMovie(anyInt(), consumerCaptor.capture());

        // Call getReviewsOfMovie with mock consumer
        getReviewLogic.getReviewsOfMovie(1, mockConsumer);

        // Simulate no reviews being added
        Consumer<Review> capturedConsumer = consumerCaptor.getValue();

        // Verify the consumer was not called
        verify(mockConsumer, never()).accept(any(Review.class));
    }

    @Test
    public void testGetReviewsOfMovie_MultipleReviews() {
        // Prepare mock consumer
        Consumer<Review> mockConsumer = mock(Consumer.class);

        // Prepare mock reviews
        Review review1 = new Review("user1", 4.5f, "Great movie!", 1625292000L);
        Review review2 = new Review("user2", 3.5f, "It was okay.", 1625378400L);

        // Capture the consumer passed to the reviewReader
        ArgumentCaptor<Consumer<Review>> consumerCaptor = ArgumentCaptor.forClass((Class) Consumer.class);
        doNothing().when(mockReviewReader).getReviewsOfMovie(anyInt(), consumerCaptor.capture());

        // Call getReviewsOfMovie with mock consumer
        getReviewLogic.getReviewsOfMovie(1, mockConsumer);

        // Simulate the onReviewAdded consumer being called with multiple reviews
        Consumer<Review> capturedConsumer = consumerCaptor.getValue();
        capturedConsumer.accept(review1);
        capturedConsumer.accept(review2);

        // Verify the consumer was called with the correct reviews
        verify(mockConsumer).accept(review1);
        verify(mockConsumer).accept(review2);
    }
}
