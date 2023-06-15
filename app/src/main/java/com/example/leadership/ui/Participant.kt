package com.example.leadership.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import com.example.leadership.R
import com.example.leadership.model.Participant
import com.example.leadership.model.isScoreIncreased
import kotlin.math.max

private val ParticipantAvatarSize = 50.dp

@Composable
fun Participant(
    modifier: Modifier,
    participant: Participant,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(HorizontalSpacing),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Avatar(
            uri = participant.avatar,
            size = ParticipantAvatarSize,
        )

        Column(
            modifier = Modifier.weight(1f),
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Name(
                    name = participant.name,
                )
                Score(
                    score = participant.score,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                var maxBaseline by remember { mutableStateOf(0f) }
                fun updateMaxBaseline(textLayoutResult: TextLayoutResult) {
                    maxBaseline = max(maxBaseline, textLayoutResult.size.height - textLayoutResult.lastBaseline)
                }

                val topBaselinePadding = with(LocalDensity.current) { maxBaseline.toDp() }
                Username(
                    modifier = Modifier.paddingFromBaseline(bottom = topBaselinePadding),
                    username = participant.username,
                    onTextLayout = ::updateMaxBaseline
                )
                Icon(
                    modifier = Modifier
                        .padding(bottom = topBaselinePadding)
                        .rotate(if (participant.isScoreIncreased) 0f else 180f),
                    painter = painterResource(R.drawable.arrow_up),
                    tint = if (participant.isScoreIncreased) Color.Green else Color.Red,
                    contentDescription = null
                )
            }
        }
    }
}