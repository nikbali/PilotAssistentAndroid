package com.mai.pilot_assistent.ui.aircrafts.create;

import com.mai.pilot_assistent.data.network.model.CreateAircraftRequest;
import com.mai.pilot_assistent.ui.base.MvpPresenter;

import java.io.File;

public interface CreateAircraftMvpPresenter<V extends CreateAircraftMvpView> extends MvpPresenter<V> {


    void createAircraft(File file, CreateAircraftRequest request);
}
