package com.example.shalawat.presentation.splash

import android.media.MediaPlayer
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shalawat.R
import com.example.shalawat.presentation.theme.DarkForestGreen
import com.example.shalawat.presentation.theme.EmeraldGreen
import com.example.shalawat.presentation.theme.MintGreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    val context = LocalContext.current

    // Fade-in animation
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 800),
        label = "splashFadeIn"
    )

    // Progress bar animation
    val progress = remember { Animatable(0f) }

    // Remember MediaPlayer to release it properly
    val mediaPlayer = remember {
        try {
            val afd = context.assets.openFd("audio/intro.mp3")
            MediaPlayer().apply {
                setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                prepare()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // Effect to play sound and handle navigation delay
    LaunchedEffect(Unit) {
        startAnimation = true
        mediaPlayer?.start()
        // Animate progress bar
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 2200, easing = FastOutSlowInEasing)
        )
        delay(300) // small pause after progress completes
        onSplashFinished()
    }

    // Cleanup MediaPlayer when leaving the screen
    DisposableEffect(Unit) {
        onDispose {
            try {
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer.stop()
                }
                mediaPlayer?.release()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1B3830),  // slightly lighter top
                        DarkForestGreen,     // deep bottom
                        Color(0xFF0F1F1B)   // very deep bottom
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .alpha(alphaAnim)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.25f))

            // ── Logo ──
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ── App Name (Serif) ──
            Text(
                text = "Shalawat",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 42.sp,
                color = Color(0xFFB7E4C7),  // mint green
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            // ── Subtitle ──
            Text(
                text = "SPIRITUAL COMPANION",
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                color = MintGreen.copy(alpha = 0.7f),
                letterSpacing = 4.sp
            )

            Spacer(modifier = Modifier.weight(0.2f))

            // ── Progress Bar Section ──
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "INITIALIZING HEART...",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = MintGreen.copy(alpha = 0.6f),
                    letterSpacing = 2.sp
                )
                Text(
                    text = "${(progress.value * 100).toInt()}%",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MintGreen.copy(alpha = 0.8f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Progress bar track
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(MintGreen.copy(alpha = 0.15f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = progress.value)
                        .height(3.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(MintGreen, EmeraldGreen)
                            )
                        )
                )
            }

            Spacer(modifier = Modifier.weight(0.15f))

            // ── Quranic Quote ──
            Text(
                text = "\"Indeed, Allah and His angels confer\nblessings upon the Prophet. O you who have\nbelieved, ask [Allah to confer] blessing upon\nhim and ask [Allah to grant him] peace.\"",
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic,
                color = Color(0xFF8FAFA5),  // muted sage
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // ── Dot Indicators ──
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Active dot
                Box(
                    modifier = Modifier
                        .width(20.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(MintGreen)
                )
                Spacer(modifier = Modifier.width(6.dp))
                // Inactive dot
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(MintGreen.copy(alpha = 0.3f))
                )
                Spacer(modifier = Modifier.width(6.dp))
                // Inactive dot
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(MintGreen.copy(alpha = 0.3f))
                )
            }

            Spacer(modifier = Modifier.weight(0.1f))
        }
    }
}
