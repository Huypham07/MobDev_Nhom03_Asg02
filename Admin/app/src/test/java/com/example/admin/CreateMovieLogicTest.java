package com.example.admin;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.example.admin.controller.CreateMovieController;
import com.example.admin.model.Movie;

public class CreateMovieLogicTest {
    private CreateMovieController mockController;

    private CreateMovieLogic createMovieLogic;

    @Before
    public void setUp() {
        mockController = mock(CreateMovieController.class);
        createMovieLogic = new CreateMovieLogic(mockController);
    }

    @Test
    public void createMovie_Successfully() {

        createMovieLogic.addMovie("Test Movie", "posterUri", "trailerLink", "releaseDate", 120,
                "description", "T13", "Action", "Director", "Actors", "English");
        // Verify
        verify(mockController).createMovie(any(Movie.class));
    }

    @Test
    public void createMovie_WithNullMovieName() {
        // Action
        createMovieLogic.addMovie(null, "posterUri", "trailerLink", "releaseDate", 120,
                "description", "T13", "Action", "Director", "Actors", "English");

        // Verify
        verify(mockController, never()).createMovie(new Movie());
    }

    @Test
    public void createMovie_WithNullPosterUri() {
        // Action
        createMovieLogic.addMovie("Test Movie", null, "trailerLink", "releaseDate", 120,
                "description", "T13", "Action", "Director", "Actors", "English");

        // Verify
        verify(mockController, never()).createMovie(new Movie());
    }

    @Test
    public void createMovie_WithNullTrailerLink() {
        // Action
        createMovieLogic.addMovie("Test Movie", "posterUri", null, "releaseDate", 120,
                "description", "T13", "Action", "Director", "Actors", "English");

        // Verify
        verify(mockController, never()).createMovie(new Movie());
    }

    @Test
    public void createMovie_WithNullReleaseDate() {
        // Action
        createMovieLogic.addMovie("Test Movie", "posterUri", "trailerLink", null, 120,
                "description", "T13", "Action", "Director", "Actors", "English");

        // Verify
        verify(mockController, never()).createMovie(new Movie());
    }

    @Test
    public void createMovie_WithNullDescription() {
        // Action
        createMovieLogic.addMovie("Test Movie", "posterUri", "trailerLink", "releaseDate", 120,
                null, "T13", "Action", "Director", "Actors", "English");

        // Verify
        verify(mockController, never()).createMovie(new Movie());
    }

    @Test
    public void createMovie_WithNullCensor() {
        // Action
        createMovieLogic.addMovie("Test Movie", "posterUri", "trailerLink", "releaseDate", 120,
                "description", null, "Action", "Director", "Actors", "English");

        // Verify
        verify(mockController, never()).createMovie(new Movie());
    }

    @Test
    public void createMovie_WithNullGenre() {
        // Action
        createMovieLogic.addMovie("Test Movie", "posterUri", "trailerLink", "releaseDate", 120,
                "description", "T13", null, "Director", "Actors", "English");

        // Verify
        verify(mockController, never()).createMovie(new Movie());
    }

    @Test
    public void createMovie_WithNullDirector() {
        // Action
        createMovieLogic.addMovie("Test Movie", "posterUri", "trailerLink", "releaseDate", 120,
                "description", "T13", "Action", null, "Actors", "English");

        // Verify
        verify(mockController, never()).createMovie(new Movie());
    }

    @Test
    public void createMovie_WithNullActors() {
        // Action
        createMovieLogic.addMovie("Test Movie", "posterUri", "trailerLink", "releaseDate", 120,
                "description", "T13", "Action", "Director", null, "English");

        // Verify
        verify(mockController, never()).createMovie(new Movie());
    }

    @Test
    public void createMovie_WithNullLanguage() {
        // Action
        createMovieLogic.addMovie("Test Movie", "posterUri", "trailerLink", "releaseDate", 120,
                "description", "T13", "Action", "Director", "Actors", null);

        // Verify
        verify(mockController, never()).createMovie(new Movie());
    }
}
