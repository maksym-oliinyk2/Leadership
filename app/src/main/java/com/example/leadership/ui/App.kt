package com.example.leadership.ui

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.leadership.model.AppState

val HorizontalSpacing = 23.dp
val VerticalSpacing = 23.dp

@Composable
fun App(
    state: AppState,
) {
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = { TopBar() }
    ) { paddingValues ->
        Leaderboard(
            modifier = Modifier.padding(paddingValues),
            state = state
        )
    }
}

