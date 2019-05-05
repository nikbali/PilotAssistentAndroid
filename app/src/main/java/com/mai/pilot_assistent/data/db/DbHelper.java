package com.mai.pilot_assistent.data.db;

import com.mai.pilot_assistent.data.db.model.Aircraft;
import com.mai.pilot_assistent.data.db.model.User;
import io.reactivex.Observable;

import java.util.List;

public interface DbHelper {

    /**
     * Пользователи
     */
    Observable<Long> insertUser(final User user);

    Observable<List<User>> getAllUsers();

    Observable<User> getUserByUsername(String username);


    /**
     * Самолеты
     */
    Observable<Long> insertAircraft(final Aircraft aircraft);

    Observable<Void> insertListAircraft(final List<Aircraft> aircraftList);

    Observable<List<Aircraft>> getAllAircrafts();

    Observable<Aircraft> getAircraftByServerId(String serverId);

}
