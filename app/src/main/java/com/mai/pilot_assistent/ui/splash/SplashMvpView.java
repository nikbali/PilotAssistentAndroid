package com.mai.pilot_assistent.ui.splash;


import com.mai.pilot_assistent.ui.base.MvpView;

public interface SplashMvpView extends MvpView {

    void openLoginActivity();

    void openMainActivity();

    void startSyncService();
}
