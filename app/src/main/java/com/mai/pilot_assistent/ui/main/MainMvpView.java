package com.mai.pilot_assistent.ui.main;

import com.mai.pilot_assistent.data.db.model.User;
import com.mai.pilot_assistent.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void setCurrentUser(User currentUser);
}
