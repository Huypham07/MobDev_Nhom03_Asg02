package com.example.asg02.vm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.asg02.controller.account.GetAllManagerController;
import com.example.asg02.controller.booking.GetBookingController;
import com.example.asg02.controller.cinema.GetCinemaController;
import com.example.asg02.controller.event.GetAllEventController;
import com.example.asg02.controller.movie.GetMovieController;
import com.example.asg02.model.Account;
import com.example.asg02.model.Booking;
import com.example.asg02.model.Cinema;
import com.example.asg02.model.CinemaHall;
import com.example.asg02.model.Event;
import com.example.asg02.model.Manager;
import com.example.asg02.model.Movie;
import com.example.asg02.utils.FirebaseUtils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

public class BaseViewModel extends ViewModel {
    MutableLiveData<List<Movie>> movieList = new MutableLiveData<>();
    MutableLiveData<List<Event>> eventList = new MutableLiveData<>();
    MutableLiveData<List<Booking>> bookingList = new MutableLiveData<>();
    MutableLiveData<List<Cinema>> cinemaList = new MutableLiveData<>();
    MutableLiveData<List<CinemaHall>> cinemaHallList = new MutableLiveData<>();

    MutableLiveData<Booking> selectedBooking = new MutableLiveData<>();
    MutableLiveData<Event> selectedEvent = new MutableLiveData<>();

    MediatorLiveData<Boolean> dataReady = new MediatorLiveData<>();

    public BaseViewModel() {
        reLoadMovieData();
        reLoadEventData();
        reloadBookingData();
        reLoadCinemaData();
        reLoadCinemaHallData();

        dataReady.addSource(movieList, value -> checkDataReady());
        dataReady.addSource(cinemaList, value -> checkDataReady());
        dataReady.addSource(cinemaHallList, value -> checkDataReady());

        FirebaseUtils.getDatabaseReference("Movies").addChildEventListener(
                new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        reLoadMovieData();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        reLoadMovieData();
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                        reLoadMovieData();
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );

        FirebaseUtils.getDatabaseReference("Events").addChildEventListener(
                new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        reLoadEventData();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        reLoadEventData();
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                        reLoadEventData();
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );

        FirebaseUtils.getDatabaseReference("Bookings").addChildEventListener(
                new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        reloadBookingData();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        reloadBookingData();
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                        reloadBookingData();
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );
    }

    private void checkDataReady() {
        if (movieList.getValue() != null && eventList.getValue() != null && bookingList.getValue() != null && cinemaList.getValue() != null && cinemaHallList.getValue() != null) {
            dataReady.setValue(true);
        }
    }

    public LiveData<List<Movie>> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList.setValue(movieList);
    }

    public LiveData<List<Event>> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList.setValue(eventList);
    }

    public LiveData<List<Booking>> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList.setValue(bookingList);
    }

    public LiveData<Booking> getSelectedBooking() {
        return selectedBooking;
    }

    public void setSelectedBooking(Booking booking) {
        selectedBooking.setValue(booking);
    }

    public LiveData<Event> getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Event event) {
        selectedEvent.setValue(event);
    }

    public LiveData<List<Cinema>> getCinemaList() {
        return cinemaList;
    }

    public void setCinemaList(List<Cinema> cinemaList) {
        this.cinemaList.setValue(cinemaList);
    }

    public LiveData<List<CinemaHall>> getCinemaHallList() {
        return cinemaHallList;
    }

    public void setCinemaHallList(List<CinemaHall> cinemaHallList) {
        this.cinemaHallList.setValue(cinemaHallList);
    }

    public void clearData() {
        movieList.setValue(null);
        eventList.setValue(null);
        bookingList.setValue(null);
        selectedBooking.setValue(null);
        selectedEvent.setValue(null);
    }

    public void reLoadMovieData() {
        new Thread(() -> {
            new GetMovieController().getAllMovies().thenAccept(movies -> {
                setMovieList(movies);
            });
        }).start();
    }

    public void reLoadEventData() {
        new Thread(() -> {
            new GetAllEventController().getAllEvents().thenAccept(events -> {
                setEventList(events);
            });
        }).start();
    }

    public void reloadBookingData() {
        new Thread(() -> {
            new GetBookingController().getAllBookings().thenAccept(bookings -> {
                setBookingList(bookings);
            });
        }).start();
    }

    public void reLoadCinemaData() {
        new Thread(() -> {
            new GetCinemaController().getAllCinemas().thenAccept(cinemas -> {
                setCinemaList(cinemas);
            });
        }).start();
    }

    public void reLoadCinemaHallData() {
        new Thread(() -> {
            new GetCinemaController().getAllCinemaHalls().thenAccept(cinemaHalls -> {
                setCinemaHallList(cinemaHalls);
            });
        }).start();
    }

    public LiveData<Boolean> isDataReady() {
        return dataReady;
    }
}
