package com.library.sample

import com.google.android.play.core.missingsplits.MissingSplitsManagerFactory
import com.library.base.log.AppLogTree
import com.library.base.log.CrashlyticsTree
import com.library.sample.di.AppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class SampleApplication : DaggerApplication() {
    private val appComponent by lazy { AppComponent.getComponent(this) }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }

    override fun onCreate() {
        if (MissingSplitsManagerFactory.create(this).disableAppIfMissingRequiredSplits()) {
            // Skip app initialization when user install app not from Google Play.
            return
        }
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(AppLogTree())
        } else {
            Timber.plant(CrashlyticsTree())
        }
    }
}