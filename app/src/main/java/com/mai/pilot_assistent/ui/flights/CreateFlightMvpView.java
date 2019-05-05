package com.mai.pilot_assistent.ui.flights;

import com.mai.pilot_assistent.data.db.model.Aircraft;
import com.mai.pilot_assistent.ui.base.MvpView;

import java.util.List;

public interface CreateFlightMvpView extends MvpView {

    void doCreateFlightClick();
    void initSpinnerAircrafts(List<Aircraft> aircrafts);
}
