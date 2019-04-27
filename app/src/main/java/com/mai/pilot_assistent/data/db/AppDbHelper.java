package com.mai.pilot_assistent.data.db;

import com.mai.pilot_assistent.data.db.model.DaoMaster;
import com.mai.pilot_assistent.data.db.model.DaoSession;
import com.mai.pilot_assistent.data.db.model.User;
import io.reactivex.Observable;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.Callable;


@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<Long> insertUser(final User user) {
        return Observable.fromCallable(() -> mDaoSession.getUserDao().insert(user));
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return Observable.fromCallable(() -> mDaoSession.getUserDao().loadAll());
    }

}
