package com.example.user.controller.booking;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.example.user.model.Booking;
import com.example.user.model.Payment;
import com.example.user.model.Show;

public class GetBookingLogicTest {

    private BookingReader mockBookingReader;
    private UpdateBookingController mockUpdateBookingController;
    private GetBookingLogic getBookingLogic;

    @Before
    public void setUp() {
        mockBookingReader = mock(BookingReader.class);
        mockUpdateBookingController = mock(UpdateBookingController.class);
        getBookingLogic = new GetBookingLogic(mockBookingReader, mockUpdateBookingController);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBookingsReturnsNoBookings() throws Exception {
        CompletableFuture<List<Booking>> future = CompletableFuture.completedFuture(Collections.emptyList());
        when(mockBookingReader.getAllBookings()).thenReturn(future);

        CompletableFuture<List<Booking>> resultFuture = getBookingLogic.getAllBookings();
        List<Booking> result = resultFuture.get();

        assertTrue(result.isEmpty());
        verify(mockUpdateBookingController, never()).updateBooking(any(Booking.class));
    }

    @Test
    public void testGetAllBookings() throws Exception {
        // Prepare mock data
        Show show = new Show(1, 1, 1, 1, "01/01/2022", "12:00", "14:00");
        Booking booking1 = new Booking("user1", show, new ArrayList<>(), new ArrayList<>(), new Payment());
        booking1.setStatus(Booking.STATUS_AVAILABLE);

        List<Booking> bookings = Collections.singletonList(booking1);
        CompletableFuture<List<Booking>> futureBookings = CompletableFuture.completedFuture(bookings);

        // Mock the bookingReader behavior
        when(mockBookingReader.getAllBookings()).thenReturn(futureBookings);

        // Test getAllBookings
        CompletableFuture<List<Booking>> result = getBookingLogic.getAllBookings();
        List<Booking> bookingResult = result.get();

        // Verify
        assertEquals(1, bookingResult.size());
        verify(mockUpdateBookingController, times(1)).updateBooking(any(Booking.class));
    }
}

