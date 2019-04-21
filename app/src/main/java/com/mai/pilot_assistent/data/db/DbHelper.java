package com.mai.pilot_assistent.data.db;

import com.mai.pilot_assistent.data.db.model.User;
import io.reactivex.Observable;

import java.util.List;

public interface DbHelper {

    Observable<Long> insertUser(final User user);

    Observable<List<User>> getAllUsers();

}
