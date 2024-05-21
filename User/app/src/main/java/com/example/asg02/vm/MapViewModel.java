package com.example.asg02.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.asg02.model.Cinema;
import com.example.asg02.model.Manager;
import com.google.android.gms.maps.model.LatLng;

public class MapViewModel extends ViewModel {
    private MutableLiveData<LatLng> currentLatLng = new MutableLiveData<>();
    private MutableLiveData<Manager> manager = new MutableLiveData<>();
    private MutableLiveData<Cinema> cinema = new MutableLiveData<>();

    // Mediator
    private MediatorLiveData<Boolean> mapDataReady = new MediatorLiveData<>();

    public MapViewModel() {
        mapDataReady.addSource(manager, value -> checkMapDataReady());
        mapDataReady.addSource(cinema, value -> checkMapDataReady());
    }

    private void checkMapDataReady() {
        if (manager.getValue() != null && cinema.getValue() != null) {
            mapDataReady.setValue(true);
            return;
        }
        mapDataReady.setValue(false);
    }

    public LiveData<Manager> getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager.setValue(manager);
    }

    public LiveData<Cinema> getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema.setValue(cinema);
    }

    public LiveData<LatLng> getCurrentLatLng() {
        return currentLatLng;
    }

    public void setCurrentLatLng(LatLng currentLatLng) {
        this.currentLatLng.setValue(currentLatLng);
    }

    public void clearData() {
        manager.setValue(null);
        cinema.setValue(null);
        currentLatLng.setValue(null);

        mapDataReady.setValue(false);
    }

    public LiveData<Boolean> isMapDataReady() {
        return mapDataReady;
    }
}
