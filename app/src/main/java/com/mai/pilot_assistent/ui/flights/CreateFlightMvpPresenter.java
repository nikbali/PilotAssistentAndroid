package com.mai.pilot_assistent.ui.flights;

import com.mai.pilot_assistent.data.db.model.Aircraft;
import com.mai.pilot_assistent.data.db.model.Airport;
import com.mai.pilot_assistent.data.network.model.CreateFlightRequest;
import com.mai.pilot_assistent.ui.base.MvpPresenter;

public interface CreateFlightMvpPresenter<V extends CreateFlightMvpView> extends MvpPresenter<V> {

    /**
     * Загружает список самолетов в выпадающий список
     */
    void loadAircrafts();

    /**
     * Загружает список аэропотов в выпадающий список
     */
    void loadAirports();

    /**
     * ПОсылает запрос на создание полета на сервер
     */
    void createFlight(CreateFlightRequest request);

    /**
     * Загружает аэродром по названию
     */
    Airport loadAirportByName(String name);

    /**
     * Загружает самолет по регистрационному номеру
     */
    Aircraft loadAircraftByRegNumber(String regNum);
}
