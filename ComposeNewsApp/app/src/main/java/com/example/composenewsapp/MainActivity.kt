package com.example.composenewsapp

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import com.example.composenewsapp.ui.mainscreen.MainScreen
import com.example.composenewsapp.ui.theme.*
import com.example.composenewsapp.ui.theme.AppTheme.AppTheme
import com.google.accompanist.insets.ProvideWindowInsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProvideWindowInsets {
                this.window.statusBarColor = ContextCompat.getColor(this, R.color.light_gray)
                val darkTheme: Boolean = isSystemInDarkTheme()
                val colors = if (darkTheme) appDarkColors() else appLightColors()
                AppTheme(colors = colors) {
                    SystemUi(windows = window)
                    Surface(color = AppTheme.colors.systemBackgroundPrimary) {
                        MainScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun SystemUi(windows: Window) =
    NewsTheme {
        windows.statusBarColor = AppTheme.colors.systemBackgroundPrimary.toArgb()
        windows.navigationBarColor = AppTheme.colors.systemBackgroundPrimary.toArgb()

        @Suppress("DEPRECATION")
        if (AppTheme.colors.systemBackgroundPrimary.luminance() > 0.5f) {
            windows.decorView.systemUiVisibility = windows.decorView.systemUiVisibility or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        @Suppress("DEPRECATION")
        if (AppTheme.colors.systemBackgroundPrimary.luminance() > 0.5f) {
            windows.decorView.systemUiVisibility = windows.decorView.systemUiVisibility or
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }
