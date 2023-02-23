package ru.netology.travelmark.app.app

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import com.yandex.mapkit.MapKitFactory
import ru.netology.travelmark.BuildConfig


class App: Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(BuildConfig.MAPS_API_KEY)
    }
}