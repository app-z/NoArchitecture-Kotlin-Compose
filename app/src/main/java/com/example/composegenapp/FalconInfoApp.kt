package com.example.composegenapp;

import android.app.Application
import dagger.hilt.android.HiltAndroidApp;
import timber.log.Timber

@HiltAndroidApp
class FalconInfoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
