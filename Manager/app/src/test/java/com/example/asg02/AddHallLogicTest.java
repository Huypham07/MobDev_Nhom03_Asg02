package com.example.asg02;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.SharedPreferences;

import com.example.asg02.controller.CreateHallController;
import com.example.asg02.model.Hall;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class AddHallLogicTest {
    private CreateHallController mockCreateHallController;
    private SharedPreferences mockSharedPreferences;
    private AddHallLogic addHallLogic;

    @Before
    public void setUp() {
        mockCreateHallController = mock(CreateHallController.class);
        mockSharedPreferences = mock(SharedPreferences.class);
        addHallLogic = new AddHallLogic(mockCreateHallController, mockSharedPreferences);
    }

    @Test
    public void testHandleAddHall() {
        addHallLogic.handleAddHall("Test Hall", 10, 20, 1);

        ArgumentCaptor<Hall> hallCaptor = ArgumentCaptor.forClass(Hall.class);
        verify(mockCreateHallController).createHall(hallCaptor.capture());

        Hall capturedHall = hallCaptor.getValue();
        assertEquals("Test Hall", capturedHall.getName());
        assertEquals(10, capturedHall.getSeatsPerRow());
        assertEquals(20, capturedHall.getSeatsPerColumn());
        assertEquals(1, capturedHall.getCinemaId());
    }

    @Test
    public void testHandleAddHallWithMissingName() {
        addHallLogic.handleAddHall("", 10, 20, 1);

        verify(mockCreateHallController, never()).createHall(any(Hall.class));
    }

    @Test
    public void testHandleAddHallWithInvalidSeatsPerRow() {
        addHallLogic.handleAddHall("Test Hall", -1, 20, 1);

        verify(mockCreateHallController, never()).createHall(any(Hall.class));
    }

    @Test
    public void testHandleAddHallWithInvalidSeatsPerColumn() {
        addHallLogic.handleAddHall("Test Hall", 10, -1, 1);

        verify(mockCreateHallController, never()).createHall(any(Hall.class));
    }
}
