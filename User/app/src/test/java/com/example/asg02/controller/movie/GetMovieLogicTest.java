package com.example.asg02.controller.movie;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static org.junit.Assert.*;

import com.example.asg02.model.Movie;

public class GetMovieLogicTest {

    private MovieReader mockMovieReader;
    private GetMovieLogic getMovieLogic;

    @Before
    public void setUp() {
        mockMovieReader = mock(MovieReader.class);
        getMovieLogic = new GetMovieLogic(mockMovieReader);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMovies() {
        // Prepare mock consumer
        Consumer<Movie> mockConsumer = mock(Consumer.class);

        // Prepare mock movie
        Movie movie1 = new Movie("Movie 1", "poster.jpg", "Action", 120, "2024-05-01", "An action movie", "Director", "Actors", "English", 8.5f);

        // Capture the consumer passed to the movieReader
        ArgumentCaptor<Consumer<Movie>> consumerCaptor = ArgumentCaptor.forClass((Class) Consumer.class);
        doNothing().when(mockMovieReader).getAllMovies(consumerCaptor.capture());

        // Call getAllMovies with mock consumer
        getMovieLogic.getAllMovies(mockConsumer);

        // Simulate the onMovieAdded consumer being called with the movie
        Consumer<Movie> capturedConsumer = consumerCaptor.getValue();
        capturedConsumer.accept(movie1);

        // Verify the consumer was called with the correct movie
        verify(mockConsumer).accept(movie1);
    }

    @Test
    public void testGetMovie() throws Exception {
        // Prepare mock movie
        Movie movie1 = new Movie("Movie 1", "poster.jpg", "Action", 120, "2024-05-01", "An action movie", "Director", "Actors", "English", 8.5f);

        // Prepare future and set expectation
        CompletableFuture<Movie> future = CompletableFuture.completedFuture(movie1);
        when(mockMovieReader.getMovie(anyInt())).thenReturn(future);

        // Call getMovie
        CompletableFuture<Movie> result = getMovieLogic.getMovie(1);

        // Verify the future completed with the correct movie
        assertEquals(movie1, result.get());
        verify(mockMovieReader).getMovie(1);
    }

    @Test
    public void testGetAllMovies_NoMovies() {
        // Prepare mock consumer
        Consumer<Movie> mockConsumer = mock(Consumer.class);

        // Capture the consumer passed to the movieReader
        ArgumentCaptor<Consumer<Movie>> consumerCaptor = ArgumentCaptor.forClass((Class) Consumer.class);
        doNothing().when(mockMovieReader).getAllMovies(consumerCaptor.capture());

        // Call getAllMovies with mock consumer
        getMovieLogic.getAllMovies(mockConsumer);

        // Simulate no movies being added
        Consumer<Movie> capturedConsumer = consumerCaptor.getValue();

        // Verify the consumer was not called
        verify(mockConsumer, never()).accept(any(Movie.class));
    }

    @Test
    public void testGetAllMovies_MultipleMovies() {
        // Prepare mock consumer
        Consumer<Movie> mockConsumer = mock(Consumer.class);

        // Prepare mock movies
        Movie movie1 = new Movie("Movie 1", "poster.jpg", "Action", 120, "2024-05-01", "An action movie", "Director", "Actors", "English", 8.5f);
        Movie movie2 = new Movie("Movie 2", "poster2.jpg", "Comedy", 90, "2024-06-01", "A comedy movie", "Director 2", "Actors 2", "English", 7.5f);

        // Capture the consumer passed to the movieReader
        ArgumentCaptor<Consumer<Movie>> consumerCaptor = ArgumentCaptor.forClass((Class) Consumer.class);
        doNothing().when(mockMovieReader).getAllMovies(consumerCaptor.capture());

        // Call getAllMovies with mock consumer
        getMovieLogic.getAllMovies(mockConsumer);

        // Simulate the onMovieAdded consumer being called with multiple movies
        Consumer<Movie> capturedConsumer = consumerCaptor.getValue();
        capturedConsumer.accept(movie1);
        capturedConsumer.accept(movie2);

        // Verify the consumer was called with the correct movies
        verify(mockConsumer).accept(movie1);
        verify(mockConsumer).accept(movie2);
    }

    @Test
    public void testGetMovie_ErrorHandling() {
        // Simulate an error during movie retrieval
        CompletableFuture<Movie> future = new CompletableFuture<>();
        future.completeExceptionally(new RuntimeException("Database error"));
        when(mockMovieReader.getMovie(anyInt())).thenReturn(future);

        try {
            // Call getMovie
            getMovieLogic.getMovie(1).get();
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            // Verify that the exception message is as expected
            assertTrue(e.getCause() instanceof RuntimeException);
            assertEquals("Database error", e.getCause().getMessage());
        }
    }
}
