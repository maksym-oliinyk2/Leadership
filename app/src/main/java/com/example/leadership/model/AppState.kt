package com.example.leadership.model

import java.net.URI
import java.util.*

@JvmInline
value class Name(
    val value: String,
) {
    init {
        require(value.isNotBlank())
    }
}

@JvmInline
value class Username(
    val value: String,
) {
    init {
        require(value.isNotBlank())
    }
}

inline val Username.formatted: String
    get() = "@${value.replaceFirstChar { it.lowercase(Locale.getDefault()) }}"

data class Participant(
    val name: Name,
    val avatar: URI,
    val score: UInt,
    val previousScore: UInt,
) {
    val username: Username = Username(name.value)
}

val Participant.isScoreIncreased: Boolean
    get() = previousScore <= score

data class AppState(
    val regionLeaderboard: Leaderboard? = null,
    val nationalLeaderboard: Leaderboard? = null,
    val globalLeaderboard: Leaderboard? = null,
)

data class Leaderboard(
    val leaders: Leaders,
    val other: List<Participant>,
) {
    companion object {
        private const val FirstLeaderIndex = 0
        private const val SecondLeaderIndex = FirstLeaderIndex + 1
        private const val ThirdLeaderIndex = SecondLeaderIndex + 1
        private const val MaxLeadersCount = 3

        fun fromParticipants(
            participants: List<Participant>,
        ): Leaderboard {
            require(participants.isNotEmpty())
            val sorted = participants.sortedByDescending { it.score }

            val leaders = Leaders(
                first = sorted[FirstLeaderIndex],
                second = sorted.getOrNull(SecondLeaderIndex),
                third = sorted.getOrNull(ThirdLeaderIndex)
            )

            return Leaderboard(leaders, sorted.takeIf { it.size >= MaxLeadersCount }?.subList(MaxLeadersCount, sorted.size) ?: listOf())
        }
    }
}

data class Leaders(
    val first: Participant,
    val second: Participant?,
    val third: Participant?,
) {
    init {
        require(first.score >= (second?.score ?: 0U)) { toString() }
        require((second?.score ?: 0U) >= (second?.score ?: 0U)) { toString() }
    }
}