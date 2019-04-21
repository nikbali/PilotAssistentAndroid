package com.mai.pilot_assistent.ui.login;


import com.mai.pilot_assistent.di.PerActivity;
import com.mai.pilot_assistent.ui.base.MvpPresenter;


@PerActivity
public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void onServerLoginClick(String email, String password);

}
