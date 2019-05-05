package com.mai.pilot_assistent.data.db;

import com.mai.pilot_assistent.data.db.model.Aircraft;
import com.mai.pilot_assistent.data.db.model.Airport;
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

    Observable<Void> deleteAll();

    Observable<List<Aircraft>> getAllAircrafts();

    Observable<Aircraft> getAircraftByServerId(String serverId);

    Aircraft getAircraftByRegistrationName(String registrationName);

    /**
     * Аэродромы
     */
    Observable<Long> insertAirport(final Airport airport);

    Observable<Void> insertListAirport(final List<Airport> airportList);

    Observable<Void> deleteAllAirports();

    Observable<List<Airport>> getAllAirports();

    Airport getAirportById(long id);
    Airport getAirportByName(String name);

}
