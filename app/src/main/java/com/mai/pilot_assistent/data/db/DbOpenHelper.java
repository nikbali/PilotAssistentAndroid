package com.mai.pilot_assistent.data.db;

import android.content.Context;
import com.mai.pilot_assistent.data.db.model.DaoMaster;
import com.mai.pilot_assistent.data.db.model.UserDao;
import com.mai.pilot_assistent.di.ApplicationContext;
import com.mai.pilot_assistent.di.DatabaseInfo;
import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class DbOpenHelper extends DaoMaster.OpenHelper {

    @Inject
    public DbOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        switch (oldVersion) {
            case 1:
            case 2:
            case 3:
            case 4:
                UserDao.dropTable(db, true);
                UserDao.createTable(db, false);
        }
    }
}
