package com.example.leadership.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.leadership.model.AppState
import com.example.leadership.model.Leaderboard
import kotlinx.coroutines.launch

private val TabCornerRadius = 12.dp
private val ParticipantsCornerRadius = 40.dp

private enum class Tab {
    Region,
    National,
    Global
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Leaderboard(
    modifier: Modifier,
    state: AppState,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(VerticalSpacing),
    ) {
        val pagerState = rememberPagerState()

        TabRow(
            modifier = Modifier
                .padding(horizontal = HorizontalSpacing)
                .fillMaxWidth()
                .clip(RoundedCornerShape(TabCornerRadius)),
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colors.surface,
            divider = {},
            indicator = { tabPositions ->
                AppTabIndicator(
                    modifier = Modifier.tabAppTabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = MaterialTheme.colors.primary,
                )
            }
        ) {
            val scope = rememberCoroutineScope()
            Tab.values().forEach { tab ->
                Tab(
                    selected = Tab.values()[pagerState.currentPage] == tab,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(tab.ordinal)
                        }
                    },
                    text = {
                        Text(
                            text = tab.toTitle()
                        )
                    }
                )
            }
        }

        HorizontalPager(
            pageCount = Tab.values().size,
            state = pagerState
        ) { page ->

            val leaderboard = state.leaderboardForTab(Tab.values()[page])

            if (leaderboard == null) {
                LargeMessage(
                    modifier = Modifier.fillMaxSize(),
                    title = "No data",
                    message = "Nothing to display, please, try again later..."
                )
            } else {
                LeaderBoard(leaderboard = leaderboard)
            }
        }
    }
}

@Composable
private fun LeaderBoard(
    leaderboard: Leaderboard,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(VerticalSpacing),
    ) {

        Leaders(
            modifier = Modifier.fillMaxWidth().padding(horizontal = HorizontalSpacing),
            leaders = leaderboard.leaders
        )

        if (leaderboard.other.isEmpty()) {
            LargeMessage(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colors.surface,
                        RoundedCornerShape(topStart = ParticipantsCornerRadius, topEnd = ParticipantsCornerRadius)
                    ),
                title = "No data",
                message = "No other participants so far..."
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        MaterialTheme.colors.surface,
                        RoundedCornerShape(topStart = ParticipantsCornerRadius, topEnd = ParticipantsCornerRadius)
                    ),
            ) {
                items(
                    items = leaderboard.other,
                    key = { participant -> participant.username.value },
                ) { participant ->

                    Participant(
                        modifier = Modifier
                            .padding(all = HorizontalSpacing),
                        participant = participant
                    )

                    Divider(
                        modifier = Modifier.padding(horizontal = HorizontalSpacing)
                    )
                }
            }
        }
    }
}

private fun Tab.toTitle(): String =
    when (this) {
        Tab.Region -> "Region"
        Tab.National -> "National"
        Tab.Global -> "Global"
    }

private fun AppState.leaderboardForTab(
    tab: Tab,
) = when (tab) {
    Tab.Region -> regionLeaderboard
    Tab.National -> nationalLeaderboard
    Tab.Global -> globalLeaderboard
}