package com.example.leadership.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.leadership.model.Name
import com.example.leadership.model.Username
import com.example.leadership.model.formatted
import java.net.URI

@Composable
fun AppTabIndicator(
    modifier: Modifier = Modifier,
    height: Dp = TabRowDefaults.IndicatorHeight,
    color: Color = LocalContentColor.current,
) {
    Box(
        modifier
            .height(height)
            .background(color = color)
    )
}

@Composable
fun Username(
    username: Username,
    modifier: Modifier = Modifier,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        modifier = modifier,
        text = username.formatted,
        style = MaterialTheme.typography.caption,
        fontWeight = FontWeight.Light,
        onTextLayout = onTextLayout,
    )
}

@Composable
fun Score(
    score: UInt,
    modifier: Modifier = Modifier,
    highlight: Color = Color.Unspecified,
) {
    Text(
        modifier = modifier,
        text = score.toString(),
        color = highlight,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun Name(
    name: Name,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = name.value,
        style = MaterialTheme.typography.subtitle1
    )
}

@Composable
fun Avatar(
    uri: URI?,
    size: Dp,
    modifier: Modifier = Modifier,
) {
    val surface = MaterialTheme.colors.surface
    val placeholder = remember(surface) { ColorPainter(surface) }

    AsyncImage(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .then(modifier),
        model = uri?.toImageRequest(),
        placeholder = placeholder,
        error = placeholder,
        fallback = placeholder,
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun LargeMessage(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = title, style = MaterialTheme.typography.h5)
        Text(text = message)
    }
}

@Composable
private fun URI.toImageRequest(): ImageRequest = ImageRequest.Builder(LocalContext.current)
    .data(toString())
    .crossfade(true)
    .build()

fun Modifier.tabAppTabIndicatorOffset(
    currentTabPosition: TabPosition,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = currentTabPosition.width,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val indicatorWidth = currentTabWidth - 50.dp
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset + currentTabWidth / 2 - indicatorWidth / 2)
        .width(indicatorWidth)
}
