package com.example.leadership

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.leadership.model.GlobalAppState
import com.example.leadership.ui.App
import com.example.leadership.ui.theme.LeadershipAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            LeadershipAppTheme {
                CompositionLocalProvider(
                    LocalOverscrollConfiguration provides null
                ) {
                    App(
                        state = GlobalAppState
                    )

                    val systemUiController = rememberWindowInsetsController()
                    val isDarkModeEnabled = isSystemInDarkTheme()

                    LaunchedEffect(isDarkModeEnabled) {
                        systemUiController.isAppearanceLightStatusBars = !isDarkModeEnabled
                        systemUiController.isAppearanceLightNavigationBars = !isDarkModeEnabled
                    }
                }
            }
        }
    }
}

@Composable
private fun rememberWindowInsetsController(): WindowInsetsControllerCompat {
    val view = LocalView.current

    return remember(view) {
        val window = view.findWindow()
        WindowCompat.getInsetsController(window, window.decorView)
    }
}

private fun View.findWindow(): Window =
    (parent as? DialogWindowProvider)?.window ?: context.findWindow()

private tailrec fun Context.findWindow(): Window =
    when (this) {
        is Activity -> window
        is ContextWrapper -> baseContext.findWindow()
        else -> error("No window found")
    }

