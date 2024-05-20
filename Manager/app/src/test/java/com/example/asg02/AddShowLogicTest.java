package com.example.asg02;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.SharedPreferences;

import com.example.asg02.controller.CreateShowController;
import com.example.asg02.model.Show;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class AddShowLogicTest {
    private CreateShowController mockCreateShowController;
    private SharedPreferences mockSharedPreferences;
    private AddShowLogic addShowLogic;

    @Before
    public void setUp() {
        mockCreateShowController = mock(CreateShowController.class);
        mockSharedPreferences = mock(SharedPreferences.class);
        addShowLogic = new AddShowLogic(mockCreateShowController, mockSharedPreferences);
    }

    @Test
    public void testHandleAddShowWithNullDate() {
        addShowLogic.handleAddShow(1, 1, 1, "10:00", "12:00", null);

        verify(mockCreateShowController, never()).createShow(any(Show.class));
    }

    @Test
    public void testHandleAddShowWithEmptyStartTime() {
        addShowLogic.handleAddShow(1, 1, 1, "", "12:00", "2024-05-20");

        verify(mockCreateShowController, never()).createShow(any(Show.class));
    }

    @Test
    public void testHandleAddShowWithEmptyEndTime() {
        addShowLogic.handleAddShow(1, 1, 1, "10:00", "", "2024-05-20");

        verify(mockCreateShowController, never()).createShow(any(Show.class));
    }

    @Test
    public void testHandleAddShowWithInvalidCinemaId() {
        addShowLogic.handleAddShow(-1, 1, 1, "10:00", "12:00", "2024-05-20");

        verify(mockCreateShowController, never()).createShow(any(Show.class));
    }

    @Test
    public void testHandleAddShowWithValidData() {
        addShowLogic.handleAddShow(1, 1, 1, "10:00", "12:00", "2024-05-20");

        ArgumentCaptor<Show> showCaptor = ArgumentCaptor.forClass(Show.class);
        verify(mockCreateShowController).createShow(showCaptor.capture());

        Show capturedShow = showCaptor.getValue();
        assertEquals(1, capturedShow.getCinemaId());
        assertEquals(1, capturedShow.getHallId());
        assertEquals(1, capturedShow.getMovieId());
        assertEquals("10:00", capturedShow.getStartTime());
        assertEquals("12:00", capturedShow.getEndTime());
        assertEquals("2024-05-20", capturedShow.getDate());
    }
}
