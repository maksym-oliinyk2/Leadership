package com.example.leadership.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.statusBarsPadding().fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Outlined.ArrowBackIos,
                contentDescription = null
            )
        }
        Text(text = "Leaderboard", style = MaterialTheme.typography.h6)
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Outlined.Menu,
                contentDescription = null
            )
        }
    }
}