package com.mai.pilot_assistent.data.db;

import com.mai.pilot_assistent.data.db.model.DaoMaster;
import com.mai.pilot_assistent.data.db.model.DaoSession;
import com.mai.pilot_assistent.data.db.model.User;
import com.mai.pilot_assistent.data.db.model.UserDao;
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

}
