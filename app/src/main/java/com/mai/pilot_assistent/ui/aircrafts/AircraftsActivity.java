package com.mai.pilot_assistent.ui.aircrafts;

import com.mai.pilot_assistent.ui.base.BaseActivity;
import javax.inject.Inject;

public class AircraftsActivity extends BaseActivity implements AircraftsMvpView{

    @Inject
    AircraftsMvpPresenter<AircraftsMvpView> mPresenter;

    @Override
    protected void setUp() {

    }
}
