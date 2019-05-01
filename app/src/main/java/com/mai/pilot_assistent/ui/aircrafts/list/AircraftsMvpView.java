package com.mai.pilot_assistent.ui.aircrafts.list;

import com.mai.pilot_assistent.data.db.model.Aircraft;
import com.mai.pilot_assistent.ui.base.MvpView;

import java.util.List;

public interface AircraftsMvpView  extends MvpView {

    void refreshAircraftList(List<Aircraft> aircrafts);
    void backToMainActivity();
}
