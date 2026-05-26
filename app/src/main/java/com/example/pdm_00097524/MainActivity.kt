package com.example.pdm_00097524

import com.example.pdm_00097524.LemonadeApp.LemonadeApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.pdm_00097524.DiceRoller.DiceRollerApp
import com.example.pdm_00097524.ui.theme.PDM_00097524Theme
import com.pdm0126.practica_preparcial1.TipCalculator.TipCalculator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PDM_00097524Theme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        TipCalculator(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


