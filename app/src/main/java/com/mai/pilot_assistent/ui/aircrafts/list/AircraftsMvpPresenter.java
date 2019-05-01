package com.mai.pilot_assistent.ui.aircrafts.list;

import com.mai.pilot_assistent.ui.base.MvpPresenter;

public interface AircraftsMvpPresenter<V extends AircraftsMvpView>  extends MvpPresenter<V> {
    /**
     * Метод обновляет список самолетов во View
     */
    void refreshAircrafts();
}
