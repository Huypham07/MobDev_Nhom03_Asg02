package com.example.user.controller.cinema;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;

import com.example.user.model.Cinema;
import com.example.user.model.CinemaHall;

public class GetCinemaLogicTest {

    private CinemaReader mockCinemaReader;
    private GetCinemaLogic getCinemaLogic;

    @Before
    public void setUp() {
        mockCinemaReader = mock(CinemaReader.class);
        getCinemaLogic = new GetCinemaLogic(mockCinemaReader);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCinemas() throws Exception {
        // Prepare mock data
        Cinema cinema1 = new Cinema(1, "Cinema 1", "Manager 1", 10.0, 20.0, "Address 1");
        List<Cinema> cinemas = Collections.singletonList(cinema1);
        CompletableFuture<List<Cinema>> futureCinemas = CompletableFuture.completedFuture(cinemas);

        // Mock the cinemaReader behavior
        when(mockCinemaReader.getAllCinemas()).thenReturn(futureCinemas);

        // Test getAllCinemas
        CompletableFuture<List<Cinema>> result = getCinemaLogic.getAllCinemas();
        List<Cinema> cinemaResult = result.get();

        // Verify
        assertEquals(1, cinemaResult.size());
        assertEquals(cinema1, cinemaResult.get(0));
    }

    @Test
    public void testGetAllCinemasByManager() throws Exception {
        // Prepare mock data
        Cinema cinema1 = new Cinema(1, "Cinema 1", "Manager 1", 10.0, 20.0, "Address 1");
        List<Cinema> cinemas = Collections.singletonList(cinema1);
        CompletableFuture<List<Cinema>> futureCinemas = CompletableFuture.completedFuture(cinemas);

        // Mock the cinemaReader behavior
        when(mockCinemaReader.getAllCinemas("Manager 1")).thenReturn(futureCinemas);

        // Test getAllCinemas by manager
        CompletableFuture<List<Cinema>> result = getCinemaLogic.getAllCinemas("Manager 1");
        List<Cinema> cinemaResult = result.get();

        // Verify
        assertEquals(1, cinemaResult.size());
        assertEquals(cinema1, cinemaResult.get(0));
    }

    @Test
    public void testGetCinema() throws Exception {
        // Prepare mock data
        Cinema cinema1 = new Cinema(1, "Cinema 1", "Manager 1", 10.0, 20.0, "Address 1");
        CompletableFuture<Cinema> futureCinema = CompletableFuture.completedFuture(cinema1);

        // Mock the cinemaReader behavior
        when(mockCinemaReader.getCinema(1)).thenReturn(futureCinema);

        // Test getCinema
        CompletableFuture<Cinema> result = getCinemaLogic.getCinema(1);
        Cinema cinemaResult = result.get();

        // Verify
        assertEquals(cinema1, cinemaResult);
    }

    @Test
    public void testGetCinemaHall() throws Exception {
        // Prepare mock data
        CinemaHall hall1 = new CinemaHall("Hall 1", 1, 10, 20);
        CompletableFuture<CinemaHall> futureHall = CompletableFuture.completedFuture(hall1);

        // Mock the cinemaReader behavior
        when(mockCinemaReader.getCinemaHall(1)).thenReturn(futureHall);

        // Test getCinemaHall
        CompletableFuture<CinemaHall> result = getCinemaLogic.getCinemaHall(1);
        CinemaHall hallResult = result.get();

        // Verify
        assertEquals(hall1, hallResult);
    }

    @Test
    public void testGetAllCinemaHalls() throws Exception {
        // Prepare mock data
        CinemaHall hall1 = new CinemaHall("Hall 1", 1, 10, 20);
        List<CinemaHall> halls = Collections.singletonList(hall1);
        CompletableFuture<List<CinemaHall>> futureHalls = CompletableFuture.completedFuture(halls);

        // Mock the cinemaReader behavior
        when(mockCinemaReader.getAllCinemaHalls()).thenReturn(futureHalls);

        // Test getAllCinemaHalls
        CompletableFuture<List<CinemaHall>> result = getCinemaLogic.getAllCinemaHalls();
        List<CinemaHall> hallResult = result.get();

        // Verify
        assertEquals(1, hallResult.size());
        assertEquals(hall1, hallResult.get(0));
    }
}
