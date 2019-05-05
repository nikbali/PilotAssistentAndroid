package com.mai.pilot_assistent.ui.aircrafts.create;

import com.mai.pilot_assistent.data.db.model.Airport;
import com.mai.pilot_assistent.data.network.model.CreateAircraftRequest;
import com.mai.pilot_assistent.ui.base.MvpPresenter;

import java.io.File;

public interface CreateAircraftMvpPresenter<V extends CreateAircraftMvpView> extends MvpPresenter<V> {


    void createAircraft(File file, CreateAircraftRequest request);

    /**
     * Загружает список аэродромов
     */
    void loadAirports();

    /**
     * Загружает аэродром по названию
     */
    Airport loadAirportByName(String name);
}
