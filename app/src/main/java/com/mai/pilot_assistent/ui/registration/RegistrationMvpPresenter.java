package com.mai.pilot_assistent.ui.registration;

import com.mai.pilot_assistent.data.network.model.RegistrationRequest;
import com.mai.pilot_assistent.ui.base.MvpPresenter;

public interface RegistrationMvpPresenter<V extends RegistrationMvpView> extends MvpPresenter<V> {

    void onRegistrationClick(RegistrationRequest registrationRequest);
}
