package com.mai.pilot_assistent.ui.aircrafts.create;

import com.mai.pilot_assistent.data.db.model.Airport;
import com.mai.pilot_assistent.ui.base.MvpView;

import java.util.List;

public interface CreateAircraftMvpView extends MvpView {

    void doCreateAircraftClick();
    void addImageClick();
    void openAircraftsActivity();
    void initSpinnerAirports(List<Airport> airports);
}
