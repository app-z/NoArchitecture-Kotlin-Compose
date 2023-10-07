package com.example.composegenapp.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.DecayAnimation
import androidx.compose.animation.core.FloatExponentialDecaySpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


object Animations {

    @Composable
    fun AnimBox(resId: Int) {

        val state by remember { mutableStateOf(false) }
        val anim = remember {
            DecayAnimation(
                animationSpec = FloatExponentialDecaySpec(frictionMultiplier = 0.25f),
                initialValue = 0f,
                initialVelocity = 500f
            )
        }
        var playTime by remember { mutableStateOf(0L) }
        var animationValue by remember { mutableStateOf(0) }

        LaunchedEffect(state) {
            val startTime = withFrameNanos { it }
            do {
                playTime = withFrameNanos { it } - startTime
                animationValue = anim.getValueFromNanos(playTime).toInt()
            } while (!anim.isFinishedFromNanos(playTime))
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "About")
            Image(
                painter = painterResource(resId),
                contentDescription = null,
                modifier = Modifier.size(animationValue.dp)
            )
        }
    }

    @Composable
    fun AnimButton() {
        var visible by remember { mutableStateOf(true) }
        Column() {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(
                    // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                    initialAlpha = 0.4f
                ),
                exit = fadeOut(
                    // Overwrites the default animation with tween
                    animationSpec = tween(durationMillis = 250)
                )
            ) {
                // Content that needs to appear/disappear goes here:
                Text("....")
            }
            Button(onClick = { visible = !visible }) { Text("Toggle") }
        }
    }
}
