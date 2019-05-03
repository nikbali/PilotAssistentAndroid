package com.mai.pilot_assistent.ui.main;

import com.mai.pilot_assistent.ui.base.MvpPresenter;

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V>{

    /**
     * Bind текущего пользователя
     */
    void getCurrentUser();

    /**
     * Выход из аккаунта
     */
    void onLogoutClick();

    /**
     * Переход на экран Самолеты
     */
    void onAircraftOpenClick();
}
