package com.mai.pilot_assistent.ui.registration;

import com.mai.pilot_assistent.ui.base.MvpPresenter;

public interface RegistrationMvpPresenter<V extends RegistrationMvpView> extends MvpPresenter<V> {

    //TODO добавить viewModel
    void onRegistrationClick(String email, String password);
}
