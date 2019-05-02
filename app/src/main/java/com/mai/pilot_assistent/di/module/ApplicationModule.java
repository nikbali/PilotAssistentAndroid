package com.mai.pilot_assistent.di.module;

import android.app.Application;
import android.content.Context;
import com.mai.pilot_assistent.BuildConfig;
import com.mai.pilot_assistent.R;
import com.mai.pilot_assistent.data.AppDataManager;
import com.mai.pilot_assistent.data.DataManager;
import com.mai.pilot_assistent.data.db.AppDbHelper;
import com.mai.pilot_assistent.data.db.DbHelper;
import com.mai.pilot_assistent.data.network.ApiHelper;
import com.mai.pilot_assistent.data.network.AppApiHelper;
import com.mai.pilot_assistent.data.prefs.AppPreferencesHelper;
import com.mai.pilot_assistent.data.prefs.PreferencesHelper;
import com.mai.pilot_assistent.di.ApiInfo;
import com.mai.pilot_assistent.di.ApplicationContext;
import com.mai.pilot_assistent.di.DatabaseInfo;
import com.mai.pilot_assistent.di.PreferenceInfo;
import com.mai.pilot_assistent.utils.AppConstants;
import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import javax.inject.Singleton;


@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(PreferencesHelper preferencesHelper) {
        return new AppApiHelper(preferencesHelper.getAccessToken());
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
