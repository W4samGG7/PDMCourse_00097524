package com.example.pdm_00097524.RankeUca

import android.app.Application
import com.example.pdm_00097524.RankeUca.data.AppProvider


class RankeUcaApplication : Application(){
    val appProvider by lazy { AppProvider(this) }
}