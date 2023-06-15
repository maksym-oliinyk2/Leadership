package com.example.leadership.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.leadership.R
import com.example.leadership.model.Leaders
import com.example.leadership.model.Participant
import com.example.leadership.ui.theme.darkSurface
import kotlin.math.roundToInt

private val CrownSize = 34.dp
private val CrownTint = Color(0xFFFFAA00)
private val NonLeaderAvatarSize = 68.dp
private val LeaderAvatarSize = 82.dp
private val AvatarBorderWidth = 3.dp
private val NonWinnerBackgroundCorner = 12.dp
private val WinnerBackgroundCorner = 30.dp
private val NonLeaderCardPadding = PaddingValues(start = 28.dp, end = 28.dp, top = 29.dp, bottom = 23.dp)
private val LeaderCardPadding = PaddingValues(start = 28.dp, end = 28.dp, top = 29.dp + LeaderAvatarSize / 2, bottom = 23.dp)
private val NonLeaderCardOverlapping = NonLeaderAvatarSize / 3
private val LeaderCardOverlapping = LeaderAvatarSize / 2
private val FirstLeaderColor = Color(0xFFFB9639)
private val SecondLeaderColor = Color(0xFF009BD6)
private val ThirdLeaderColor = Color(0xFF00D95F)

@Composable
fun Leaders(
    modifier: Modifier,
    leaders: Leaders,
) {
    val (first, second, third) = leaders

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        OverlappingParticipantCard(
            modifier = Modifier.weight(1f),
            top = {
                Avatar(
                    modifier = Modifier
                        .aspectRatio(1f, matchHeightConstraintsFirst = true)
                        .border(AvatarBorderWidth, SecondLeaderColor, CircleShape),
                    uri = second?.avatar,
                    size = NonLeaderAvatarSize,
                )
            },
            bottom = {
                OptionalParticipantInfo(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.surface,
                            RoundedCornerShape(topStart = NonWinnerBackgroundCorner, bottomStart = NonWinnerBackgroundCorner)
                        )
                        .padding(NonLeaderCardPadding),
                    participant = second,
                    highlight = SecondLeaderColor,
                )
            },
            contentOverlap = NonLeaderCardOverlapping
        )

        OverlappingParticipantCard(
            modifier = Modifier.weight(1f),
            top = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        modifier = Modifier.padding(bottom = 2.dp).size(CrownSize),
                        tint = CrownTint,
                        painter = painterResource(R.drawable.crown),
                        contentDescription = null,
                    )
                    Avatar(
                        modifier = Modifier
                            .border(AvatarBorderWidth, FirstLeaderColor, CircleShape),
                        uri = first.avatar,
                        size = LeaderAvatarSize,
                    )
                }
            },
            bottom = {
                ParticipantInfo(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.darkSurface,
                            RoundedCornerShape(topStart = WinnerBackgroundCorner, topEnd = WinnerBackgroundCorner)
                        )
                        .padding(LeaderCardPadding),
                    participant = first,
                    highlight = FirstLeaderColor,
                )
            },
            contentOverlap = LeaderCardOverlapping
        )

        OverlappingParticipantCard(
            modifier = Modifier.weight(1f),
            top = {
                Avatar(
                    modifier = Modifier
                        .aspectRatio(1f, matchHeightConstraintsFirst = true)
                        .border(AvatarBorderWidth, SecondLeaderColor, CircleShape),
                    uri = third?.avatar,
                    size = NonLeaderAvatarSize,
                )
            },
            bottom = {
                OptionalParticipantInfo(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.surface,
                            RoundedCornerShape(bottomEnd = NonWinnerBackgroundCorner, topEnd = NonWinnerBackgroundCorner)
                        )
                        .padding(NonLeaderCardPadding),
                    participant = third,
                    highlight = ThirdLeaderColor,
                )
            },
            contentOverlap = NonLeaderCardOverlapping
        )
    }
}

@Composable
private fun OverlappingParticipantCard(
    top: @Composable () -> Unit,
    bottom: @Composable () -> Unit,
    contentOverlap: Dp,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val overlapPx = with(density) { contentOverlap.toPx() }.roundToInt()
    Layout(content = {
        bottom()
        top()
    }, modifier = modifier) { measurables, constraints ->
        val bottomMeasurable = measurables[0].measure(constraints)
        val topMeasurable = measurables[1].measure(constraints)

        check(bottomMeasurable.width >= topMeasurable.width)

        val layoutWidth = bottomMeasurable.width
        val layoutHeight = bottomMeasurable.height + topMeasurable.height - overlapPx

        layout(layoutWidth, layoutHeight) {
            bottomMeasurable.placeRelative(
                x = 0,
                y = topMeasurable.height - overlapPx,
            )

            topMeasurable.placeRelative(
                x = (layoutWidth - topMeasurable.width) / 2,
                y = 0,
            )
        }
    }
}

@Composable
private fun OptionalParticipantInfo(
    modifier: Modifier,
    participant: Participant?,
    highlight: Color,
) {
    if (participant == null) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "???",
                style = MaterialTheme.typography.subtitle1
            )
        }
    } else {
        ParticipantInfo(
            modifier = modifier,
            participant = participant,
            highlight = highlight,
        )
    }
}

@Composable
private fun ParticipantInfo(
    modifier: Modifier,
    participant: Participant,
    highlight: Color,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Name(
            name = participant.name,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Score(
            score = participant.score,
            highlight = highlight,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Username(username = participant.username)
    }
}