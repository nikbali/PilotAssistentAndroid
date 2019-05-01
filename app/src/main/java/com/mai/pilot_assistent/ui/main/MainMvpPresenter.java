package com.mai.pilot_assistent.ui.main;

import com.mai.pilot_assistent.ui.base.MvpPresenter;
import com.mai.pilot_assistent.ui.base.MvpView;

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V>{

    void getCurrentUser();
}
