package com.example.asg02.controller.show;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.example.asg02.model.Show;

public class GetShowLogicTest {

    private ShowReader mockShowReader;
    private GetShowLogic getShowLogic;

    @Before
    public void setUp() {
        mockShowReader = mock(ShowReader.class);
        getShowLogic = new GetShowLogic(mockShowReader);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllShowsByCinema() {
        // Prepare mock future and shows
        CompletableFuture<List<Show>> mockFuture = new CompletableFuture<>();
        List<Show> expectedShows = new ArrayList<>();
        expectedShows.add(new Show(1, 1, 1, 1, "2024-05-23", "18:00", "20:00"));
        mockFuture.complete(expectedShows);

        // Mock showReader behavior
        when(mockShowReader.getAllShowsByCinema(anyInt())).thenReturn(mockFuture);

        // Call getAllShowsByCinema
        CompletableFuture<List<Show>> resultFuture = getShowLogic.getAllShowsByCinema(1);

        // Verify that showReader was called with correct cinemaId
        verify(mockShowReader).getAllShowsByCinema(1);

        // Assert the result
        assertEquals(expectedShows, resultFuture.join());
    }

    @Test
    public void testGetAllShowsByCinemaAndMovie() {
        // Prepare mock future and shows
        CompletableFuture<List<Show>> mockFuture = new CompletableFuture<>();
        List<Show> expectedShows = new ArrayList<>();
        expectedShows.add(new Show(1, 1, 1, 1, "2024-05-23", "18:00", "20:00"));
        mockFuture.complete(expectedShows);

        // Mock showReader behavior
        when(mockShowReader.getAllShowsByCinemaAndMovie(anyInt(), anyInt())).thenReturn(mockFuture);

        // Call getAllShowsByCinemaAndMovie
        CompletableFuture<List<Show>> resultFuture = getShowLogic.getAllShowsByCinemaAndMovie(1, 1);

        // Verify that showReader was called with correct cinemaId and movieId
        verify(mockShowReader).getAllShowsByCinemaAndMovie(1, 1);

        // Assert the result
        assertEquals(expectedShows, resultFuture.join());
    }

    @Test
    public void testGetAllShowsByCinemaAndMovieWithDate() {
        // Prepare mock future and shows
        CompletableFuture<List<Show>> mockFuture = new CompletableFuture<>();
        List<Show> expectedShows = new ArrayList<>();
        expectedShows.add(new Show(1, 1, 1, 1, "2024-05-23", "18:00", "20:00"));
        mockFuture.complete(expectedShows);

        // Mock showReader behavior
        when(mockShowReader.getAllShowsByCinemaAndMovie(anyInt(), anyInt(), anyString())).thenReturn(mockFuture);

        // Call getAllShowsByCinemaAndMovieWithDate
        CompletableFuture<List<Show>> resultFuture = getShowLogic.getAllShowsByCinemaAndMovie(1, 1, "2024-05-23");

        // Verify that showReader was called with correct cinemaId, movieId, and date
        verify(mockShowReader).getAllShowsByCinemaAndMovie(1, 1, "2024-05-23");

        // Assert the result
        assertEquals(expectedShows, resultFuture.join());
    }
}

