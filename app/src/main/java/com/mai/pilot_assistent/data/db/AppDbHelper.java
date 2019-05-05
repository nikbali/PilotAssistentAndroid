package com.mai.pilot_assistent.data.db;

import com.mai.pilot_assistent.data.db.model.*;
import io.reactivex.Observable;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;


@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<Long> insertUser(final User user) {
        return Observable.fromCallable(() -> {
            long id = mDaoSession.getUserDao().insertOrReplace(user);
            return id;
        });
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return Observable.fromCallable(() -> mDaoSession.getUserDao().loadAll());
    }

    @Override
    public Observable<User> getUserByUsername(String username) {
        return Observable.fromCallable(() -> mDaoSession.getUserDao()
                .queryBuilder()
                .where(UserDao.Properties.Username.eq(username))
                .unique());
    }

    @Override
    public Observable<Long> insertAircraft(Aircraft aircraft) {
        return Observable.fromCallable(() -> mDaoSession.getAircraftDao().insertOrReplace(aircraft));
    }

    @Override
    public Observable<Void> insertListAircraft(List<Aircraft> aircraftList) {
        return Observable.fromCallable(() -> {
             mDaoSession.getAircraftDao().insertOrReplaceInTx(aircraftList);
             return null;
        });
    }

    @Override
    public Observable<Void> deleteAll() {
        return Observable.fromCallable(() -> {
            mDaoSession.getAircraftDao().deleteAll();
            return null;
        });
    }

    @Override
    public Observable<List<Aircraft>> getAllAircrafts() {
        return Observable.fromCallable(() ->
                mDaoSession.getAircraftDao().loadAll()
        );
    }

    @Override
    public Observable<Aircraft> getAircraftByServerId(String serverId) {
        return Observable.fromCallable(() -> mDaoSession.getAircraftDao()
                .queryBuilder()
                .where(AircraftDao.Properties.IdServer.eq(serverId))
                .unique());
    }

    @Override
    public Observable<Long> insertAirport(Airport airport) {
        return Observable.fromCallable(() -> mDaoSession.getAirportDao().insertOrReplace(airport));
    }

    @Override
    public Observable<Void> insertListAirport(List<Airport> airportList) {
        return Observable.fromCallable(() -> {
            mDaoSession.getAirportDao().insertOrReplaceInTx(airportList);
            return null;
        });
    }

    @Override
    public Observable<Void> deleteAllAirports() {
        return null;
    }

    @Override
    public Observable<List<Airport>> getAllAirports() {
        return Observable.fromCallable(() -> mDaoSession.getAirportDao().loadAll());
    }

    @Override
    public Airport getAirportById(long id) {
        Airport airport = mDaoSession.getAirportDao().queryBuilder()
                .where(AirportDao.Properties.Id.eq(id))
                .unique();
        return airport;
    }

    @Override
    public Airport getAirportByName(String name) {
        Airport airport = mDaoSession.getAirportDao().queryBuilder()
                .where(AirportDao.Properties.NameAirport.eq(name))
                .unique();
        return airport;
    }

}
