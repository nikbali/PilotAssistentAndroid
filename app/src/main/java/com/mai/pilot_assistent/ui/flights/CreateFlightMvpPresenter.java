package com.mai.pilot_assistent.ui.flights;

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
}
