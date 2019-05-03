package com.mai.pilot_assistent.ui.flights;

import com.mai.pilot_assistent.data.network.model.CreateAircraftRequest;
import com.mai.pilot_assistent.ui.base.MvpPresenter;

import java.io.File;

public interface CreateFlightMvpPresenter<V extends CreateFlightMvpView> extends MvpPresenter<V> {


    void createAircraft(File file, CreateAircraftRequest request);
}
