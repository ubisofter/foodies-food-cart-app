package ru.requestdesign.foodies

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

@Composable
fun CelebrateScreen(navController: NavController, context: Context) {
    var animationState by remember { mutableStateOf(AnimationState.Packing) }

    val animationFinished = remember { mutableStateOf(false) }

    LaunchedEffect(animationState) {
        when (animationState) {
            AnimationState.Packing -> {
                delay(4000)
                animationState = AnimationState.Drive
            }
            AnimationState.Drive -> {
                delay(5000)
                animationState = AnimationState.Done
            }
            AnimationState.Complete -> {
                delay(5000)
                animationState = AnimationState.Done
            }
            AnimationState.Done -> {
                animationFinished.value = true
            }
        }
    }

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        content = { it
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Crossfade(targetState = animationState, label = "") { target ->
                    when (target) {
                        AnimationState.Packing -> {
                            ComposeLottieAnimation(
                                animationResId = R.raw.anim_packing,
                                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                                onFinish = { animationState = AnimationState.Drive }
                            )
                        }

                        AnimationState.Drive -> {
                            ComposeLottieAnimation(
                                animationResId = R.raw.anim_drive,
                                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                                onFinish = { animationState = AnimationState.Done }
                            )
                        }

                        AnimationState.Complete -> {
                            ComposeLottieAnimation(
                                animationResId = R.raw.anim_done,
                                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                                onFinish = { animationState = AnimationState.Done }
                            )
                        }

                        AnimationState.Done -> {
                            if (animationFinished.value) {
                                CelebrateCard(
                                    onGitHubClick = {
                                        val githubLink = "https://github.com/ubisofter/foodies-food-cart-app/"
                                        openLinkInBrowser(githubLink, context)
                                    },
                                    onInviteClick = {
                                        val githubLink = "https://t.me/brolligator"
                                        openLinkInBrowser(githubLink, context)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ComposeLottieAnimation(
    animationResId: Int,
    modifier: Modifier = Modifier,
    onFinish: () -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationResId))

    val isPlaying by remember { mutableStateOf(true) }

    LaunchedEffect(isPlaying) {
        if (!isPlaying) {
            onFinish()
        }
    }

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        isPlaying = isPlaying
    )
}

@Composable
fun CelebrateCard(
    onGitHubClick: () -> Unit,
    onInviteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .height(360.dp)
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Оцените код на GitHub!",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp),
            )

            Button(
                onClick = { onGitHubClick() },
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
                    .padding(16.dp)
                , colors = ButtonDefaults.buttonColors(Color(0xFFF15412))
            ) {
                Text(text = "На GitHub", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { onInviteClick() },
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                , colors = ButtonDefaults.buttonColors(Color(0xFFF15412))
            ) {
                Text(text = "Пригласить на работу", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

enum class AnimationState {
    Packing,
    Drive,
    Complete,
    Done
}

fun openLinkInBrowser(link: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
    context.startActivity(intent)
}