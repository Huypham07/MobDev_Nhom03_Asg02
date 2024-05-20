package com.example.asg02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.SharedPreferences;

import com.example.asg02.controller.CreateCinemaController;
import com.example.asg02.model.Cinema;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class AddCinemaLogicTest {
    private CreateCinemaController mockController;
    private SharedPreferences mockPreferences;
    private AddCinemaLogic addCinemaLogic;

    @Before
    public void setUp() {
        mockController = mock(CreateCinemaController.class);
        mockPreferences = mock(SharedPreferences.class);
        addCinemaLogic = new AddCinemaLogic(mockController, mockPreferences);

        when(mockPreferences.getString("email", "error")).thenReturn("test@example.com");
    }

    @Test
    public void testHandleAddCinema() {
        addCinemaLogic.handleAddCinema("Test Cinema", 10.0, 20.0, "123 Test Street");

        ArgumentCaptor<Cinema> cinemaCaptor = ArgumentCaptor.forClass(Cinema.class);
        verify(mockController).createCinema(cinemaCaptor.capture());

        Cinema capturedCinema = cinemaCaptor.getValue();
        assertEquals("Test Cinema", capturedCinema.getName());
        assertEquals(10.0, capturedCinema.getLatitude(), 0.001);
        assertEquals(20.0, capturedCinema.getLongitude(), 0.001);
        assertEquals("123 Test Street", capturedCinema.getDetailAddress());
        assertEquals("test@example.com", capturedCinema.getManager());
    }

    @Test
    public void testHandleAddCinemaWithMissingName() {
        addCinemaLogic.handleAddCinema("", 10.0, 20.0, "123 Test Street");

        verify(mockController, never()).createCinema(null);
    }

    @Test
    public void testHandleAddCinemaWithInvalidLatitude() {
        addCinemaLogic.handleAddCinema("Test Cinema", -91.0, 20.0, "123 Test Street");

        ArgumentCaptor<Cinema> cinemaCaptor = ArgumentCaptor.forClass(Cinema.class);
        verify(mockController).createCinema(cinemaCaptor.capture());

        Cinema capturedCinema = cinemaCaptor.getValue();
        assertNotNull(capturedCinema);
        assertEquals(-91.0, capturedCinema.getLatitude(), 0.001);
    }

    @Test
    public void testHandleAddCinemaWithInvalidLongitude() {
        addCinemaLogic.handleAddCinema("Test Cinema", 10.0, 200.0, "123 Test Street");

        ArgumentCaptor<Cinema> cinemaCaptor = ArgumentCaptor.forClass(Cinema.class);
        verify(mockController).createCinema(cinemaCaptor.capture());

        Cinema capturedCinema = cinemaCaptor.getValue();
        assertNotNull(capturedCinema);
        assertEquals(200.0, capturedCinema.getLongitude(), 0.001);
    }

    @Test
    public void testHandleAddCinemaWithMissingEmail() {
        // Return null for the email preference to simulate missing email
        when(mockPreferences.getString("email", "error")).thenReturn(null);

        addCinemaLogic.handleAddCinema("Test Cinema", 10.0, 20.0, "123 Test Street");

        ArgumentCaptor<Cinema> cinemaCaptor = ArgumentCaptor.forClass(Cinema.class);
        verify(mockController).createCinema(cinemaCaptor.capture());

        Cinema capturedCinema = cinemaCaptor.getValue();
        assertEquals("Test Cinema", capturedCinema.getName());
        assertEquals(10.0, capturedCinema.getLatitude(), 0.001);
        assertEquals(20.0, capturedCinema.getLongitude(), 0.001);
        assertEquals("123 Test Street", capturedCinema.getDetailAddress());
        // Verify that the fallback value "error" is used for the manager
        assertEquals("error", capturedCinema.getManager());
    }
}

