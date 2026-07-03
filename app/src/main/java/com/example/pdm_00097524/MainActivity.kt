package com.example.pdm_00097524

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pdm_00097524.RankeUca.AppRoot
import com.example.pdm_00097524.ui.theme.PDM_00097524Theme
import com.example.pdm_00097524.RankeUca.navigation.RankeUCA_App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PDM_00097524Theme {
                AppRoot()
            }
        }
    }
}


