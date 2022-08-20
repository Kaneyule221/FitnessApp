package com.example.repfluentv2.util

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.preferencesDataStore
import java.util.prefs.Preferences


//val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "DeviceDataStore" )

class DeviceDataStore:Application() {
}