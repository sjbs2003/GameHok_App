package com.kirab.gamehokapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kirab.gamehokapp.ui.theme.GameHokAppTheme
import com.kirab.gamehokapp.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameHokAppTheme {
                HomeScreen()
            }
        }
    }
}