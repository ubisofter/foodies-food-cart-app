package ru.requestdesign.foodies.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import ru.requestdesign.foodies.R

@Composable
fun SplashScreen(navController: NavController) {
    var animationFinished by remember { mutableStateOf(false) }

    LaunchedEffect(animationFinished) {
        delay(2000)
        animationFinished = true
        navController.navigate("catalogue")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {it
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ComposeLottieAnimation(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )
            }
        },
        backgroundColor = Color(0xFFF15412)
    )

}

@Composable
fun ComposeLottieAnimation(modifier: Modifier) {

    val clipSpecs = LottieClipSpec.Progress(
        min = 0.0f,
        max = 0.44f
    )

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_animation))

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = 1,
        clipSpec = clipSpecs,
    )
}