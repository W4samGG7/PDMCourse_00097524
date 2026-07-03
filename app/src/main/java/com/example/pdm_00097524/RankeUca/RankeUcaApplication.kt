package com.example.pdm_00097524.RankeUca

import android.app.Application
import com.example.pdm_00097524.RankeUca.data.AppProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher


class RankeUcaApplication : Application(){
    val appProvider by lazy { AppProvider(this) }

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            appProvider.loadSavedApiKey()
        }

    }
}