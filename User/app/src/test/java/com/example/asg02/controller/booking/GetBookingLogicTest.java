package com.example.asg02.controller.booking;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import android.annotation.SuppressLint;

import com.example.asg02.model.Booking;
import com.example.asg02.model.Payment;
import com.example.asg02.model.Show;
import com.example.asg02.utils.DateTimeUtils;

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

    @SuppressLint("CheckResult")
    @Test
    public void testGetBookings() {
        // Prepare mock data
        Show show = new Show(1, 1, 1, 1, "01/01/2022", "12:00", "14:00");
        Booking booking1 = new Booking("user1", show, new ArrayList<>(), new ArrayList<>(), new Payment());
        booking1.setStatus(Booking.STATUS_AVAILABLE);

        // Mock the bookingReader behavior
        doAnswer(invocation -> {
            Consumer<Booking> callback = invocation.getArgument(1);
            callback.accept(booking1);
            return null;
        }).when(mockBookingReader).getBookings(eq("user1"), any(Consumer.class));

        // Mock DateTimeUtils behavior to consider the booking as expired
        mockStatic(DateTimeUtils.class);
        when(DateTimeUtils.isAfterNow(anyString())).thenReturn(false);

        // Test getBookings
        Consumer<Booking> onBookingAdded = booking -> {
            assertNotNull(booking);
            assertEquals(Booking.STATUS_EXPIRED, booking.getStatus());
        };

        getBookingLogic.getBookings("user1", onBookingAdded);

        // Verify that the booking status was updated
        verify(mockUpdateBookingController, times(1)).updateBooking(any(Booking.class));
    }
}

